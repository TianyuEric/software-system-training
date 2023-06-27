package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.mapper.ProjectMapper;
import com.projectpractice.service.ProjectService;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceDefault
        extends ServiceImpl<ProjectMapper, ProjectEntity>
        implements ProjectService {


    @Override
    public boolean deleteByName(ProjectEntity projectEntity) {
        //这里需要写一个方法，来删除表中的数据，而且是按照项目名称来删除的，不是按照id
        if (projectEntity.getProjectName() != null) {
            return baseMapper.deleteByName(projectEntity.getProjectName()) > 0;
        }
        return false;
    }

}