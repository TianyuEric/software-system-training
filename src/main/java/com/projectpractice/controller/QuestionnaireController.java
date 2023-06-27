package com.projectpractice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.QuestionDto;
import com.projectpractice.dto.QuestionStatisticDto;
import com.projectpractice.dto.SameQuestionDto;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.entity.QuestionEntity;
import com.projectpractice.entity.QuestionnaireEntity;
import com.projectpractice.service.OptionService;
import com.projectpractice.service.QuestionService;
import com.projectpractice.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.controller
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:11
 * @Description: QuestionnaireController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    private QuestionnaireService questionnaireService;

    private QuestionService questionService;

    private OptionService optionService;
    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * 添加问卷
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/insert")
    public HttpResponseEntity insert(@RequestBody QuestionnaireEntity questionnaireEntity){
        boolean bool = questionnaireService.save(questionnaireEntity);
        return HttpResponseEntity.response(bool, "新增问卷", questionnaireEntity.getId());
    }

    /**
     * 修改问卷
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/update")
    public HttpResponseEntity modify(@RequestBody QuestionnaireEntity questionnaireEntity){
        boolean bool =  questionnaireService.updateById(questionnaireEntity);
        return HttpResponseEntity.response(bool, "修改问卷", null);
    }

    /**
     * 根据项目获取问卷列表
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/list")
    public HttpResponseEntity list(@RequestBody QuestionnaireEntity questionnaireEntity){
        List<QuestionnaireEntity> list = questionnaireService.query()
                .eq("project_id", questionnaireEntity.getProjectId())
                .list();
        boolean bool = !list.isEmpty();
        return HttpResponseEntity.response(bool, "查询问卷", list);
    }

    /**
     * 删除问卷
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/delete")
    public HttpResponseEntity delete(@RequestBody QuestionnaireEntity questionnaireEntity){
        LambdaQueryWrapper<QuestionnaireEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(QuestionnaireEntity::getStatus, "1");
        queryWrapper.eq(QuestionnaireEntity::getId, questionnaireEntity.getId());
        boolean bool = questionnaireService.remove(queryWrapper);
        return HttpResponseEntity.response(bool, "删除问卷", null);
    }

    /**
     * 发布问卷
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/release")
    public HttpResponseEntity release(@RequestBody QuestionnaireEntity questionnaireEntity){
        questionnaireEntity.setStatus("1");
        questionnaireEntity.setStartTime(LocalDateTime.now());
        boolean bool = questionnaireService.updateById(questionnaireEntity);
        return HttpResponseEntity.response(bool, "发布问卷", null);

    }

    /**
     * 关闭问卷
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/close")
    public HttpResponseEntity close(@RequestBody QuestionnaireEntity questionnaireEntity){
        questionnaireEntity.setStatus("0");
        questionnaireEntity.setStopTime(LocalDateTime.now());
        boolean bool = questionnaireService.updateById(questionnaireEntity);
        return HttpResponseEntity.response(bool, "关闭问卷", null);

    }

    /**
     * 获取当前问卷的所有题目
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/questions")
    public HttpResponseEntity questions(@RequestBody QuestionnaireEntity questionnaireEntity){
        List<QuestionEntity> questions = questionService.lambdaQuery()
                .eq(QuestionEntity::getQuestionnaireId, questionnaireEntity.getId())
                .ne(QuestionEntity::getIsLink, '1')
                .list();
        List<QuestionDto> dtoList = questions.stream().map(e -> {
            List<OptionEntity> options = optionService.lambdaQuery()
                    .eq(OptionEntity::getQuestionId, e.getId())
                    .list();
            return QuestionDto.builder().option(options).isMust(e.getIsMust()).type(e.getType())
                    .id(e.getId()).name(e.getName()).build();
        }).collect(Collectors.toList());
        boolean bool = !dtoList.isEmpty();
        return HttpResponseEntity.response(bool, "问卷问题查询", dtoList);
    }

    /**
     * 生成问卷链接
     * @param questionnaireEntity 问卷实体
     * @return HttpResponseEntity
     */
    @PostMapping("/link")
    public HttpResponseEntity checkLink(@RequestBody QuestionnaireEntity questionnaireEntity){
        QuestionnaireEntity questionnaire = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getStatus, "1")
                .eq(QuestionnaireEntity::getId, questionnaireEntity.getId())
                .one();
        return HttpResponseEntity.response(questionnaire != null, "链接", questionnaire);
    }

    @PostMapping("/same")
    public HttpResponseEntity querySameQuestion(@RequestBody QuestionEntity questionEntity){
        QuestionnaireEntity questionnaire = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getId, questionEntity.getQuestionnaireId())
                .one();
        List<QuestionnaireEntity> questionnaireEntities = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getProjectId, questionnaire.getProjectId())
                .ne(QuestionnaireEntity::getId, questionnaire.getId())
                .list();
        List<SameQuestionDto> arrayList = new ArrayList<>();
        for (QuestionnaireEntity questionnaireEntity :questionnaireEntities) {
            List<QuestionEntity> questionEntities = questionService.lambdaQuery()
                    .eq(QuestionEntity::getQuestionnaireId, questionnaireEntity.getId())
                    .list();
            List<QuestionEntity> collect = questionEntities.stream().filter(e -> {
                        if (e.getName() != null){
                            return e.getName().equals(questionEntity.getName());
                        } else {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
            if (!collect.isEmpty()){
                List<SameQuestionDto> questionDtos = collect.stream().map(e -> SameQuestionDto.builder()
                        .questionnaireName(questionnaireEntity.getName())
                        .questionId(e.getId())
                        .count(e.getAnswerCount())
                        .build()).collect(Collectors.toList());
                arrayList.addAll(questionDtos);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", arrayList);
        map.put("total", arrayList.size());

        return HttpResponseEntity.success("相同问题", map);
    }

    @PostMapping("/relate")
    public HttpResponseEntity relate(@RequestBody QuestionEntity questionEntity){
        QuestionEntity question = questionService.lambdaQuery()
                .eq(QuestionEntity::getId, questionEntity.getId())
                .one();
        QuestionnaireEntity questionnaire = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getId, question.getQuestionnaireId())
                .one();
        List<QuestionnaireEntity> list = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getProjectId, questionnaire.getProjectId())
                .list();
        int answerCount= 0;
        List<OptionEntity> optionEntityList = optionService.lambdaQuery()
                .eq(OptionEntity::getQuestionId, question.getId())
                .list();
        for (QuestionnaireEntity questionnaireEntity : list) {
            List<QuestionEntity> questionEntities = questionService.lambdaQuery()
                    .eq(QuestionEntity::getName, question.getName())
                    .eq(QuestionEntity::getQuestionnaireId, questionnaireEntity.getId())
                    .list();
            for (QuestionEntity question1 : questionEntities) {
                List<OptionEntity> optionEntities = optionService.lambdaQuery()
                        .eq(OptionEntity::getQuestionId, question1.getId())
                        .list();
                answerCount += question1.getAnswerCount();
                for (OptionEntity entity : optionEntityList) {
                    int count = entity.getPersonCount();
                    for (OptionEntity optionEntity : optionEntities) {
                        if (optionEntity.getChooseTerm().equals(entity.getChooseTerm())) {
                            count += optionEntity.getPersonCount();
                        }
                        entity.setPersonCount(count);
                    }

                }

            }
        }
        QuestionStatisticDto statisticDto = QuestionStatisticDto.builder()
                .id(question.getId())
                .answerCount(answerCount)
                .name(question.getName())
                .option(optionEntityList)
                .type(question.getType())
                .build();
        return HttpResponseEntity.success("相同问题统计", statisticDto);

    }
}
