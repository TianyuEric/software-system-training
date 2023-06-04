package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.mapper.ProjectMapper;
import com.projectpractice.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-04  20:50
 * @Description: ProjectServiceImpl
 * @Version: 1.0
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {
}
