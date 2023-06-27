package com.projectpractice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.QuestionDto;
import com.projectpractice.dto.QuestionStatisticDto;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.entity.QuestionBankEntity;
import com.projectpractice.entity.QuestionEntity;
import com.projectpractice.service.OptionService;
import com.projectpractice.service.QuestionBankService;
import com.projectpractice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/question")
@Slf4j
@RequiredArgsConstructor // lombok will generate a constructor that autowires all final fields

public class QuestionController {

    private final QuestionService questionService;
    private final OptionService optionService;
    private final QuestionBankService questionBankService;


    @PostMapping("/update")
    public HttpResponseEntity update(@RequestBody QuestionDto questionDto){
        log.error(questionDto.toString());
        LambdaQueryWrapper<OptionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OptionEntity::getQuestionId, questionDto.getId());
        optionService.remove(queryWrapper);
        QuestionEntity question = QuestionEntity.builder()
                .id(questionDto.getId())
                .name(questionDto.getName())
                .isMust(questionDto.getIsMust())
                .build();
        questionService.updateById(question);
        List<OptionEntity> options = questionDto.getOption();
        for (OptionEntity option : options) {
            option.setId(null);
            option.setQuestionId(questionDto.getId());
        }
        boolean bool = optionService.saveBatch(options);
        return HttpResponseEntity.response(bool, "编辑问题", null);
    }


    @PostMapping("/add")
    public HttpResponseEntity insert(@RequestBody QuestionEntity questionEntity){
        boolean bool = questionService.save(questionEntity);
        return HttpResponseEntity.response(bool, "添加问题", questionEntity.getId());
    }


    @PostMapping("/delete")
    public HttpResponseEntity remove(@RequestBody QuestionEntity questionEntity){
        LambdaQueryWrapper<OptionEntity> queryWrapper = new LambdaQueryWrapper<>();
        optionService.remove(queryWrapper.eq(OptionEntity::getQuestionId, questionEntity.getId()));
        boolean bool = questionService.removeById(questionEntity);
        return HttpResponseEntity.response(bool, "删除问题", null);
    }

    @PostMapping("/bank")
    public HttpResponseEntity listBank(){
        List<QuestionBankEntity> bankEntities = questionBankService.list();
        List<QuestionDto> dtoList = bankEntities.stream().map(e -> {
            List<OptionEntity> optionEntities = optionService.lambdaQuery()
                    .eq(OptionEntity::getQuestionId, e.getId())
                    .list();
            return QuestionDto.builder().type(e.getType())
                    .id(e.getId()).option(optionEntities)
                    .name(e.getName()).isMust("true").build();
        }).collect(Collectors.toList());
        boolean bool = !dtoList.isEmpty();
        return HttpResponseEntity.response(bool, "查询题库", dtoList);
    }

    @PostMapping("/link")
    public HttpResponseEntity getLinkQuestion(@RequestBody QuestionEntity questionEntity){
        QuestionEntity question = questionService.getById(questionEntity.getId());
        List<OptionEntity> optionEntities = optionService.lambdaQuery()
                .eq(OptionEntity::getQuestionId, questionEntity.getId())
                .list();

        QuestionDto questionDto = QuestionDto.builder().isMust(question.getIsMust())
                .name(question.getName()).type(question.getType())
                .option(optionEntities).id(question.getId()).build();
        return HttpResponseEntity.success("查询成功", questionDto);
    }

    @PostMapping("/statistic")
    public HttpResponseEntity getStatistic(@RequestBody QuestionEntity questionEntity){
        QuestionEntity question = questionService.lambdaQuery()
                .eq(QuestionEntity::getId, questionEntity.getId()).one();
        List<OptionEntity> optionEntities = optionService.lambdaQuery()
                .eq(OptionEntity::getQuestionId, question.getId())
                .list();
        QuestionStatisticDto statisticDto = QuestionStatisticDto.builder()
                .answerCount(question.getAnswerCount())
                .id(question.getId())
                .name(question.getName())
                .type(question.getType())
                .option(optionEntities).build();
        return HttpResponseEntity.success("查询成功", statisticDto);
    }


    @PostMapping("/all")
    public HttpResponseEntity getAll(@RequestBody QuestionEntity questionEntity){
        List<QuestionEntity> questionEntities = questionService.lambdaQuery()
                .eq(QuestionEntity::getQuestionnaireId, questionEntity.getQuestionnaireId())
                .list();
        List<QuestionDto> dtoList = questionEntities.stream().map(e -> {
            List<OptionEntity> options = optionService.lambdaQuery()
                    .eq(OptionEntity::getQuestionId, e.getId())
                    .list();
            return QuestionDto.builder().option(options)
                    .id(e.getId()).name(e.getName()).answerCount(e.getAnswerCount()).build();
        }).collect(Collectors.toList());
        boolean bool = !dtoList.isEmpty();
        return HttpResponseEntity.response(bool, "问卷问题查询", dtoList);
    }
}
