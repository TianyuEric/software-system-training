package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.mapper
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:02
 * @Description: QuestionnaireMapper
 * @Version: 1.0
 */
@Mapper
public interface QuestionnaireMapper extends BaseMapper<QuestionnaireEntity> {
}
