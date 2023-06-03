package com.projectpractice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.UserEntity;
import com.projectpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.controller
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  19:01
 * @Description: UserController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/userLogin")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity, HttpServletResponse response){
        List<UserEntity> list = userService.query().eq("username", userEntity.getUsername())
                .eq("password", userEntity.getPassword())
                .eq("status", "1").list();
        if (list.isEmpty()){
            return HttpResponseEntity.error("登录失败");
        } else {
            Cookie cookie = new Cookie("username", list.get(0).getUsername());
            response.addCookie(cookie);
            return HttpResponseEntity.success("登录成功", list);
        }
    }

    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUserinfo(@RequestBody UserEntity userEntity){
        return userService.save(userEntity)? HttpResponseEntity.success("创建成功"): HttpResponseEntity.error("创建失败");
    }

    @PostMapping("/modifyUserInfo")
    public HttpResponseEntity modifyUserinfo(@RequestBody UserEntity userEntity){
        return userService.updateById(userEntity)? HttpResponseEntity.success("修改成功"): HttpResponseEntity.error("修改失败");
    }

    @PostMapping("/deleteUserinfo")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity){
        return userService.removeById(userEntity)? HttpResponseEntity.success("删除成功"): HttpResponseEntity.error("删除失败");
    }

    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(@RequestBody Map<String, Object> map){
        Page<UserEntity> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        userService.query().eq("status", "1").page(page);
        List<UserEntity> list = page.getRecords();
        return HttpResponseEntity.success("查询成功", list);
    }
}

