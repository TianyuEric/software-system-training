package com.projectpractice.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.UserEntity;
import com.projectpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor // lombok will generate a constructor that autowires all final fields

public class UserController {

    private final  UserService userService;

    //用户登录
    @PostMapping("/userLogin")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity, HttpServletResponse response){
        List<UserEntity> list = userService.query()
                .eq("username", userEntity.getUsername())
                .eq("password", userEntity.getPassword())
                .eq("status", "1").list();
        if (list.isEmpty()){
            return HttpResponseEntity.response(false,"userLogin", null);
        } else {
            Cookie cookie = new Cookie("username", list.get(0).getUsername());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "userLogin", list);
        }
    }

    //新增用户
    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUserinfo(@RequestBody UserEntity userEntity){

        return HttpResponseEntity.response(userService.save(userEntity), "addUserinfo", null);
    }

    //删除用户
    @PostMapping("/deleteUserinfo")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity){
        return HttpResponseEntity.response(userService.removeById(userEntity), "deleteUserById", null);
    }

    //修改用户
    @PostMapping("/modifyUserInfo")
    public HttpResponseEntity modifyUserinfo(@RequestBody UserEntity userEntity){
        return HttpResponseEntity.response(userService.updateById(userEntity), "modifyUserinfo", null);
    }

    //用户列表查询
    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(@RequestBody Map<String, Object> pageMap){
        Page<UserEntity> userEntityPage = new Page<>((Integer) pageMap.get("pageNum"), (Integer) pageMap.get("pageSize"));
        userService.query().eq("status", "1")
                .like("username", pageMap.get("username")).page(userEntityPage);
        List<UserEntity> list = userEntityPage.getRecords();
        return HttpResponseEntity.response(!list.isEmpty(),"queryUserList", list);
    }

}

