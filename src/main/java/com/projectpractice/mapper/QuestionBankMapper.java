package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.QuestionBankEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.mapper
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-23  22:00
 * @Description: QuestionBankMapper
 * @Version: 1.0
 */
@Mapper
public interface QuestionBankMapper extends BaseMapper<QuestionBankEntity> {
}
