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

    /**
     * 查询项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/queryProjectList")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){
        List<ProjectEntity> list = projectService.query()
                .like("id", projectEntity.getId() == null?"": projectEntity.getId())
                .like("project_name", projectEntity.getProjectName()== null?"": projectEntity.getProjectName()).list();
        boolean bool = !list.isEmpty();
        return HttpResponseEntity.response(bool, "查询", list);
    }

    /**
     * 添加项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/addProjectInfo")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity){
        boolean bool = projectService.save(projectEntity);
        return HttpResponseEntity.response(bool, "添加", null);
    }

    /**
     * 修改项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/modifyProjectInfo")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity){
        boolean bool = projectService.updateById(projectEntity);
        return HttpResponseEntity.response(bool, "修改", null);
    }

    /**
     *  删除项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/deleteProjectById")
    public HttpResponseEntity deleteProject(@RequestBody ProjectEntity projectEntity){
        boolean bool = projectService.removeById(projectEntity);
        return  HttpResponseEntity.response(bool, "删除", null);
    }
}
