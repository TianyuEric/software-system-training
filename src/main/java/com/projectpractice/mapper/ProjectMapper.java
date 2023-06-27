package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.ProjectEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<ProjectEntity> {
    @Delete("DELETE FROM project_info WHERE project_name=#{projectName}")
    int deleteByName(String projectName);
}
