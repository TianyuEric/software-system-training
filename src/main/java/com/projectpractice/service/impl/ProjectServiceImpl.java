package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.mapper.ProjectMapper;
import com.projectpractice.service.ProjectService;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {
}
