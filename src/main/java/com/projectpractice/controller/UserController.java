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
@RequiredArgsConstructor // Lombok将生成一个构造函数，自动注入所有final字段
public class UserController {
    private final UserService userService;

    @PostMapping("/userLogin")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity, HttpServletResponse response) {
        List<UserEntity> userList = userService.query()
                .eq("username", userEntity.getUsername())
                .eq("password", userEntity.getPassword())
                .eq("status", "1").list();
        if (userList.isEmpty()) {
            return HttpResponseEntity.response(false, "登录", null);
        } else {
            UserEntity loggedInUser = userList.get(0);
            Cookie cookie = new Cookie("username", loggedInUser.getUsername());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "登录", userList);
        }
    }

    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUserinfo(@RequestBody UserEntity userEntity) {
        boolean success = userService.save(userEntity);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyUserInfo")
    public HttpResponseEntity modifyUserinfo(@RequestBody UserEntity userEntity) {
        boolean success = userService.updateById(userEntity);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteUserinfo")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity) {
        boolean success = userService.removeById(userEntity);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<UserEntity> page = new Page<>(pageNum, pageSize);
        userService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<UserEntity> userList = page.getRecords();
        boolean success = !userList.isEmpty();
        return HttpResponseEntity.response(success, "查询", userList);
    }

}
