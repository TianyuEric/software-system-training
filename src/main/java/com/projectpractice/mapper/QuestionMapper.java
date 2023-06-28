package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface QuestionMapper extends BaseMapper<QuestionEntity> {
}
