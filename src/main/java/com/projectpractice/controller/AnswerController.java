package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.AnswerInfoDto;
import com.projectpractice.dto.ChosenAnswerDto;
import com.projectpractice.entity.*;
import com.projectpractice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
@Slf4j
@RequiredArgsConstructor // lombok will generate a constructor that autowires all final fields
public class AnswerController {

    private final OptionService optionService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final AnswerLinkService answerLinkService;
    private final ChosenAnswerService chosenAnswerService;

    @PostMapping("/list")
    public HttpResponseEntity list(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        String projectId = (String) map.get("projectId");
        List<AnswerInfoDto> answerInfoList = answerService.getAnswerInfoList(projectId, username, pageSize, pageNum);
        boolean success = !answerInfoList.isEmpty();
        return HttpResponseEntity.response(success, "查询", answerInfoList);
    }

    @PostMapping("/submit")
    @Transactional
    public HttpResponseEntity submit(@RequestBody ChosenAnswerDto chosenAnswerDto) {
        AnswerEntity answerEntity = AnswerEntity.builder()
                .questionnaireId(chosenAnswerDto.getQuestionnaireId())
                .roleId(chosenAnswerDto.getRoleId())
                .answerTime(LocalDateTime.now())
                .build();

        boolean success = answerService.save(answerEntity);
        List<Map<String, String>> answers = chosenAnswerDto.getAnswer();
        Map<String, Integer> countList = new HashMap<>();

        for (Map<String, String> answer : answers) {
            String questionId = answer.get("questionId");
            String optionId = answer.get("optionId");
            OptionEntity optionEntity = optionService.getById(optionId);
            log.info(optionEntity.toString());
            optionEntity.setPersonCount(optionEntity.getPersonCount() + 1);
            optionService.updateById(optionEntity);
            countList.put(questionId, 1);

            AnswerLinkEntity answerLink = AnswerLinkEntity.builder()
                    .questionId(questionId)
                    .userId(chosenAnswerDto.getRoleId())
                    .answerId(answerEntity.getId())
                    .build();
            answerLinkService.save(answerLink);

            ChosenAnswerEntity chosenAnswer = ChosenAnswerEntity.builder()
                    .linkId(answerLink.getId())
                    .optionId(optionId)
                    .build();
            chosenAnswerService.save(chosenAnswer);
        }

        for (String questionId : countList.keySet()) {
            QuestionEntity question = questionService.getById(questionId);
            question.setAnswerCount(question.getAnswerCount() + 1);
            questionService.updateById(question);
        }

        return HttpResponseEntity.response(success, "提交", null);
    }


    @PostMapping("/review")
    public HttpResponseEntity review(@RequestBody AnswerEntity answerEntity) {
        AnswerEntity answer = answerService.lambdaQuery()
                .eq(AnswerEntity::getId, answerEntity.getId()).one();
        List<AnswerLinkEntity> linkEntities = answerLinkService.lambdaQuery()
                .eq(AnswerLinkEntity::getAnswerId, answer.getId()).list();
        List<Map<String, Object>> maps = linkEntities.stream().map(e -> {
            List<ChosenAnswerEntity> chosenAnswerEntities = chosenAnswerService.lambdaQuery()
                    .eq(ChosenAnswerEntity::getLinkId, e.getId()).list();
            Map<String, Object> map = new HashMap<>();
            for (ChosenAnswerEntity chosenAnswerEntity : chosenAnswerEntities) {
                map.put("questionId", e.getQuestionId());
                map.put("optionId", chosenAnswerEntity.getOptionId());
            }
            return map;
        }).collect(Collectors.toList());
        boolean bool = !maps.isEmpty();
        return HttpResponseEntity.response(bool, "回看", maps);
    }
}
