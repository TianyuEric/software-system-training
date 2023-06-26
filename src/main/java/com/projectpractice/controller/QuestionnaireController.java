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
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor // lombok will generate a constructor that autowires all final fields

public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;  // 问卷服务类

    private final QuestionService questionService;  // 问题服务类

    private final OptionService optionService;  // 选项服务类

    // 添加问卷
    @PostMapping("/insert")
    public HttpResponseEntity insert(@RequestBody QuestionnaireEntity questionnaireEntity) {

        return HttpResponseEntity.response( questionnaireService.save(questionnaireEntity),
                "新增问卷", questionnaireEntity.getId());
    }

    // 删除问卷
    @PostMapping("/delete")
    public HttpResponseEntity delete(@RequestBody QuestionnaireEntity questionnaireEntity) {
        LambdaQueryWrapper<QuestionnaireEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(QuestionnaireEntity::getStatus, "1");  // 筛选出状态不为1的问卷
        queryWrapper.eq(QuestionnaireEntity::getId, questionnaireEntity.getId());  // 根据ID进行筛选
        boolean bool = questionnaireService.remove(queryWrapper);  // 调用问卷服务类的删除方法
        return HttpResponseEntity.response(bool, "删除问卷", null);
    }

    // 修改问卷
    @PostMapping("/update")
    public HttpResponseEntity modify(@RequestBody QuestionnaireEntity questionnaireEntity) {
        boolean bool = questionnaireService.updateById(questionnaireEntity);  // 调用问卷服务类的根据ID更新方法
        return HttpResponseEntity.response(bool, "修改问卷", null);
    }

    // 获取当前问卷的所有题目
    @PostMapping("/questions")
    public HttpResponseEntity questions(@RequestBody QuestionnaireEntity questionnaireEntity) {
        List<QuestionEntity> questions = questionService.lambdaQuery()
                .eq(QuestionEntity::getQuestionnaireId, questionnaireEntity.getId())  // 根据问卷ID进行查询
                .ne(QuestionEntity::getIsLink, '1')  // 筛选出非链接题目
                .list();
        List<QuestionDto> dtoList = questions.stream().map(e -> {
            List<OptionEntity> options = optionService.lambdaQuery()
                    .eq(OptionEntity::getQuestionId, e.getId())  // 根据问题ID进行查询选项
                    .list();
            return QuestionDto.builder().option(options).isMust(e.getIsMust()).type(e.getType())
                    .id(e.getId()).name(e.getName()).build();
        }).collect(Collectors.toList());
        boolean bool = !dtoList.isEmpty();
        return HttpResponseEntity.response(bool, "问卷问题查询", dtoList);
    }

    // 根据项目获取问卷列表
    @PostMapping("/list")
    public HttpResponseEntity list(@RequestBody QuestionnaireEntity questionnaireEntity) {
        List<QuestionnaireEntity> list = questionnaireService.query()
                .eq("project_id", questionnaireEntity.getProjectId())  // 根据项目ID进行查询
                .list();
        boolean bool = !list.isEmpty();
        return HttpResponseEntity.response(bool, "查询问卷", list);
    }

    // 发布问卷
    @PostMapping("/release")
    public HttpResponseEntity release(@RequestBody QuestionnaireEntity questionnaireEntity) {
        questionnaireEntity.setStatus("1");  // 设置问卷状态为1（发布）
        questionnaireEntity.setStartTime(LocalDateTime.now());  // 设置问卷开始时间为当前时间
        boolean bool = questionnaireService.updateById(questionnaireEntity);  // 调用问卷服务类的根据ID更新方法
        return HttpResponseEntity.response(bool, "发布问卷", null);
    }

    // 生成问卷链接
    @PostMapping("/link")
    public HttpResponseEntity checkLink(@RequestBody QuestionnaireEntity questionnaireEntity) {
        QuestionnaireEntity questionnaire = questionnaireService.lambdaQuery()
                .eq(QuestionnaireEntity::getStatus, "1")  // 筛选出状态为1的问卷
                .eq(QuestionnaireEntity::getId, questionnaireEntity.getId())  // 根据ID进行筛选
                .one();
        return HttpResponseEntity.response(questionnaire != null, "链接", questionnaire);
    }

    // 关闭问卷
    @PostMapping("/close")
    public HttpResponseEntity close(@RequestBody QuestionnaireEntity questionnaireEntity) {
        questionnaireEntity.setStatus("0");  // 设置问卷状态为0（关闭）
        questionnaireEntity.setStopTime(LocalDateTime.now());  // 设置问卷关闭时间为当前时间
        boolean bool = questionnaireService.updateById(questionnaireEntity);  // 调用问卷服务类的根据ID更新方法
        return HttpResponseEntity.response(bool, "关闭问卷", null);
    }


   }
