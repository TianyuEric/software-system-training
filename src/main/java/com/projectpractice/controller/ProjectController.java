package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor // lombok will generate a constructor that autowires all final fields

public class ProjectController {

    private final ProjectService projectService;

//查询项目
    @PostMapping("/queryProjectList")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){
        List<ProjectEntity> list = projectService.query()
                .like("id", projectEntity.getId() == null?"": projectEntity.getId())
                .like("project_name", projectEntity.getProjectName()== null?"": projectEntity.getProjectName()).list();
        return HttpResponseEntity.response( !list.isEmpty(), "projectService", list);
    }

//添加项目
    @PostMapping("/addProjectInfo")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity){
        return HttpResponseEntity.response(projectService.save(projectEntity), "addProjectInfo", null);
    }

//修改项目
    @PostMapping("/modifyProjectInfo")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity){

        return HttpResponseEntity.response( projectService.updateById(projectEntity), "modifyProjectInfo", null);
    }

// 删除项目
    @PostMapping("/deleteProjectById")
    public HttpResponseEntity deleteProject(@RequestBody ProjectEntity projectEntity){

        return  HttpResponseEntity.response(projectService.removeById(projectEntity), "deleteProject", null);
    }
    // 删除项目
    @PostMapping("/deleteProjectByName")
    public HttpResponseEntity deleteProjectByName(@RequestBody ProjectEntity projectEntity){
        return  HttpResponseEntity.response(projectService.deleteByName(projectEntity), "deleteProjectByName", null);
    }

}
