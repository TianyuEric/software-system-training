package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.AnswerInfoDto;
import com.projectpractice.dto.ChosenAnswerDto;
import com.projectpractice.entity.*;
import com.projectpractice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.controller
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-23  14:33
 * @Description: AnswerController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/answer")
@Slf4j
public class AnswerController {
    private AnswerService answerService;
    private AnswerLinkService answerLinkService;
    private ChosenAnswerService chosenAnswerService;
    private OptionService optionService;
    private QuestionService questionService;
    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }
    @Autowired
    public void setAnswerLinkService(AnswerLinkService answerLinkService) {
        this.answerLinkService = answerLinkService;
    }
    @Autowired
    public void setChosenAnswerService(ChosenAnswerService chosenAnswerService) {
        this.chosenAnswerService = chosenAnswerService;
    }
    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 分页查问卷的回答列表
     * @param map map
     * @return HttpResponseEntity
     */
    @PostMapping("/list")
    public HttpResponseEntity list(@RequestBody Map<String, Object> map){
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        String projectId = (String) map.get("projectId");
        List<AnswerInfoDto> answerInfoList = answerService.getAnswerInfoList(projectId, username ,pageSize, pageNum);
        boolean bool = !answerInfoList.isEmpty();
        return HttpResponseEntity.response(bool, "查询", answerInfoList);
    }

    /**
     * 提交答案
     * @param chosenAnswerDto 选择答案的dto
     * @return HttpResponseEntity
     */
    @PostMapping("/submit")
    public HttpResponseEntity submit(@RequestBody ChosenAnswerDto chosenAnswerDto){
        AnswerEntity answerEntity = AnswerEntity.builder().questionnaireId(chosenAnswerDto.getQuestionnaireId())
                .roleId(chosenAnswerDto.getRoleId()).answerTime(LocalDateTime.now()).build();
        boolean bool = answerService.save(answerEntity);
        List<Map<String, String>> answer = chosenAnswerDto.getAnswer();
        Map<String, Integer> countList = new HashMap<>();
        for (Map<String, String> map : answer) {
            String questionId = map.get("questionId");
            String optionId = map.get("optionId");
            OptionEntity optionEntity = optionService.lambdaQuery()
                    .eq(OptionEntity::getId, optionId).one();
            log.info(optionEntity.toString());
            optionEntity.setPersonCount(optionEntity.getPersonCount() + 1);
            optionService.updateById(optionEntity);
            countList.put(questionId, 1);
            AnswerLinkEntity answerLink = AnswerLinkEntity.builder().questionId(questionId)
                    .userId(chosenAnswerDto.getRoleId()).answerId(answerEntity.getId()).build();
            answerLinkService.save(answerLink);
            ChosenAnswerEntity chosenAnswer = ChosenAnswerEntity.builder().linkId(answerLink.getId())
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

    /**
     * 回看答题结果
     * @param answerEntity 答题实体
     * @return HttpResponseEntity
     */
    @PostMapping("/review")
    public HttpResponseEntity review(@RequestBody AnswerEntity answerEntity){
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
