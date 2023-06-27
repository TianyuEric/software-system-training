package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/queryProjectList")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity) {
        List<ProjectEntity> list = projectService.query()
                .like("id", projectEntity.getId() == null ? "" : projectEntity.getId())
                .like("project_name", projectEntity.getProjectName() == null ? "" : projectEntity.getProjectName()).list();
        return HttpResponseEntity.response(!list.isEmpty(), "查询", list);
    }

    @PostMapping("/addProjectInfo")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity) {
        return HttpResponseEntity.response(projectService.save(projectEntity), "添加", null);
    }

    @PostMapping("/modifyProjectInfo")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity) {
        return HttpResponseEntity.response(projectService.updateById(projectEntity), "修改", null);
    }

    @PostMapping("/deleteProjectById")
    public HttpResponseEntity deleteProject(@RequestBody ProjectEntity projectEntity) {
        return HttpResponseEntity.response(projectService.removeById(projectEntity), "删除", null);
    }
}
