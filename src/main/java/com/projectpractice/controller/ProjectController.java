package com.projectpractice.controller;

import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.controller
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-04  20:51
 * @Description: ProjectController
 * @Version: 1.0
 */
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
        return list.isEmpty()? HttpResponseEntity.error("查询失败"): HttpResponseEntity.success("查询成功", list);
    }

    /**
     * 添加项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/addProjectInfo")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity){
        return projectService.save(projectEntity)? HttpResponseEntity.success("添加成功"): HttpResponseEntity.error("添加失败");
    }

    /**
     * 修改项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/modifyProjectInfo")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity){
        return projectService.updateById(projectEntity)? HttpResponseEntity.success("修改成功"): HttpResponseEntity.error("修改失败");
    }

    /**
     *  删除项目
     * @param projectEntity 项目实体
     * @return HttpResponseEntity
     */
    @PostMapping("/deleteProjectById")
    public HttpResponseEntity deleteProject(@RequestBody ProjectEntity projectEntity){
        return projectService.removeById(projectEntity)? HttpResponseEntity.success("删除成功"): HttpResponseEntity.error("删除失败");
    }
}
