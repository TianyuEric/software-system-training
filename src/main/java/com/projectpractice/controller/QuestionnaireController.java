package com.projectpractice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.QuestionDto;
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
import java.util.List;
import java.util.stream.Collectors;


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

}
