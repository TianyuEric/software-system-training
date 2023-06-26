package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.AnswerDto;
import com.projectpractice.dto.ChosenAnswerDto;
import com.projectpractice.entity.*;
import com.projectpractice.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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

//分页查问卷的回答列表
    @PostMapping("/list")
    public HttpResponseEntity list(@RequestBody Map<String, Object> map){
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        String projectId = (String) map.get("projectId");
        List<AnswerDto> answerInfoList = answerService.getAnswerInfoList(projectId, username ,pageSize, pageNum);
        return HttpResponseEntity.response(!answerInfoList.isEmpty(), "查询", answerInfoList);
    }

//提交答案
    @PostMapping("/submit")
    @Transactional
    public HttpResponseEntity submit(@RequestBody ChosenAnswerDto chosenAnswerDto){
        AnswerInfo answerInfo = AnswerInfo.builder().questionnaireId(chosenAnswerDto.getQuestionnaireId())
                .roleId(chosenAnswerDto.getRoleId()).answerTime(LocalDateTime.now()).build();
        boolean bool = answerService.save(answerInfo);
        List<Map<String, String>> answer = chosenAnswerDto.getAnswer();
        Map<String, Integer> countList = new HashMap<>();
        for (Map<String, String> map : answer) {
            String questionId = map.get("questionId");
            String optionId = map.get("optionId");
            OptionEntity optionEntity = optionService.lambdaQuery()
                    .eq(OptionEntity::getId, optionId).one();
            log.info(optionEntity.toString());
            optionEntity.setPersonNumCount(optionEntity.getPersonNumCount() + 1);
            optionService.updateById(optionEntity);
            countList.put(questionId, 1);
            AnswerLink answerLink = AnswerLink.builder().questionId(questionId)
                    .userId(chosenAnswerDto.getRoleId()).answerId(answerInfo.getId()).build();
            answerLinkService.save(answerLink);
            ChosenAnswer chosenAnswer = ChosenAnswer.builder().linkId(answerLink.getId())
                    .optionId(optionId).build();
            chosenAnswerService.save(chosenAnswer);
        }
        for (String key : countList.keySet()){
            QuestionEntity question = questionService.lambdaQuery()
                    .eq(QuestionEntity::getId, key).one();
            question.setAnswerCount(question.getAnswerCount() + 1);
            questionService.updateById(question);
        }
        return HttpResponseEntity.response(bool, "提交", null);
    }

// 回看答题结果
    @PostMapping("/review")
    public HttpResponseEntity review(@RequestBody AnswerInfo answerInfo){
        AnswerInfo answer = answerService.lambdaQuery()
                .eq(AnswerInfo::getId, answerInfo.getId()).one();
        List<AnswerLink> linkEntities = answerLinkService.lambdaQuery()
                .eq(AnswerLink::getAnswerId, answer.getId()).list();
        List<Map<String, Object>> maps = linkEntities.stream().map(e -> {
            List<ChosenAnswer> chosenAnswerEntities = chosenAnswerService.lambdaQuery()
                    .eq(ChosenAnswer::getLinkId, e.getId()).list();
            Map<String, Object> map = new HashMap<>();
            for (ChosenAnswer chosenAnswerEntity : chosenAnswerEntities) {
                map.put("questionId", e.getQuestionId());
                map.put("optionId", chosenAnswerEntity.getOptionId());
            }
            return map;
        }).collect(Collectors.toList());
        boolean bool = !maps.isEmpty();
       return HttpResponseEntity.response(bool, "回看", maps);
    }
}
