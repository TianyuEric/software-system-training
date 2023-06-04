package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.mapper
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-04  20:48
 * @Description: projectMapper
 * @Version: 1.0
 */
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectEntity> {
}
