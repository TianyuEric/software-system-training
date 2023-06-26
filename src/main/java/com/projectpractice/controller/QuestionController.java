package com.projectpractice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.dto.QuestionDto;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.entity.QuestionBank;
import com.projectpractice.entity.QuestionEntity;
import com.projectpractice.service.OptionService;
import com.projectpractice.service.QuestionRepoService;
import com.projectpractice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final QuestionRepoService questionRepoService;

    
    // 添加问题接口
    @PostMapping("/add")
    public HttpResponseEntity addQuestion(@RequestBody QuestionEntity questionEntity){
        boolean bool = questionService.save(questionEntity);  // 保存问题
        return HttpResponseEntity.response(bool, "添加问题", questionEntity.getId());
    }

    // 删除问题接口
    @PostMapping("/delete")
    public HttpResponseEntity deleteQuestion(@RequestBody QuestionEntity questionEntity){
        // 构建查询条件，查询问题对应的选项
        LambdaQueryWrapper<OptionEntity> queryWrapper = new LambdaQueryWrapper<>();
        optionService.remove(queryWrapper.eq(OptionEntity::getQuestionId, questionEntity.getId()));

        boolean bool = questionService.removeById(questionEntity);  // 删除问题
        return HttpResponseEntity.response(bool, "删除问题", null);
    }
    
    // 编辑题目
    @PostMapping("/update")
    public HttpResponseEntity updateQuestion(@RequestBody QuestionDto questionDto) {
        log.error(questionDto.toString());
        LambdaQueryWrapper<OptionEntity> queryWrapper = new LambdaQueryWrapper<>();  //构建查询条件
        queryWrapper.eq(OptionEntity::getQuestionId, questionDto.getId());
        // 设置查询条件，要求 OptionEntity 实体的 questionId 属性等于 questionDto 对象的 id 属性
        optionService.remove(queryWrapper);  // 根据查询条件删除符合条件的选项实体

        QuestionEntity question = QuestionEntity.builder()
                .id(questionDto.getId())  // 设置问题实体的 id 属性为 questionDto 对象的 id 属性
                .name(questionDto.getName())  // 设置问题实体的 name 属性为 questionDto 对象的 name 属性
                .isMust(questionDto.getIsMust())  // 设置问题实体的 isMust 属性为 questionDto 对象的 isMust 属性
                .build();
        questionService.updateById(question);  // 根据题目的 id 属性更新题目实体

        List<OptionEntity> dtoOption = questionDto.getOption();  // 获取 questionDto 对象中的选项列表

        for (OptionEntity optionEntity : dtoOption) {  // 遍历选项列表
            optionEntity.setId(null);  // 将选项实体的 id 属性设置为 null，以确保在保存时生成新的 id
            optionEntity.setQuestionId(questionDto.getId());  // 将选项实体的 questionId 属性设置为 questionDto 对象的 id 属性
        }

        boolean bool = optionService.saveBatch(dtoOption);
        return HttpResponseEntity.response(bool, "编辑问题", null);  // 根据保存结果创建 HttpResponseEntity 对象作为响应，包含保存是否成功的信息和相应的消息
    }

    // 查询题库接口
    @PostMapping("/bank")
    public HttpResponseEntity getQuestionBank(){
        List<QuestionBank> bankEntities = questionRepoService.list();  // 获取题库列表
        // 为每个题目获取其选项，构建包含题目和选项的DTO列表
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

    // 获取关联问题接口
    @PostMapping("/link")
    public HttpResponseEntity getLinkQuestion(@RequestBody QuestionEntity questionEntity){
        QuestionEntity question = questionService.getById(questionEntity.getId());  // 获取问题实体
        // 获取问题对应的选项
        List<OptionEntity> optionEntities = optionService.lambdaQuery()
                .eq(OptionEntity::getQuestionId, questionEntity.getId())
                .list();

        // 构建包含题目和选项的DTO
        QuestionDto questionDto = QuestionDto.builder().isMust(question.getIsMust())
                .name(question.getName()).type(question.getType())
                .option(optionEntities).id(question.getId()).build();
        return HttpResponseEntity.success("查询成功", questionDto);
    }
}
