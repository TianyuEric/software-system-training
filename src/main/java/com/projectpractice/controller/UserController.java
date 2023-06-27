package com.projectpractice.controller;

import com.alibaba.excel.EasyExcelFactory;
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

    @PostMapping("/userLogin")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity, HttpServletResponse response){
        List<UserEntity> list = userService.query()
                .eq("username", userEntity.getUsername())
                .eq("password", userEntity.getPassword())
                .eq("status", "1").list();
        if (list.isEmpty()){
            return HttpResponseEntity.response(false,"登录", null);
        } else {
            Cookie cookie = new Cookie("username", list.get(0).getUsername());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "登录", list);
        }
    }


    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUserinfo(@RequestBody UserEntity userEntity){
        boolean bool = userService.save(userEntity);
        return HttpResponseEntity.response(bool, "创建", null);
    }


    @PostMapping("/modifyUserInfo")
    public HttpResponseEntity modifyUserinfo(@RequestBody UserEntity userEntity){
        boolean bool = userService.updateById(userEntity);
        return HttpResponseEntity.response(bool, "修改", null);
    }


    @PostMapping("/deleteUserinfo")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity){
        boolean bool = userService.removeById(userEntity);
        return HttpResponseEntity.response(bool, "删除", null);
    }


    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(@RequestBody Map<String, Object> map){
        Page<UserEntity> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        userService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<UserEntity> list = page.getRecords();
        boolean bool = !list.isEmpty();
        return HttpResponseEntity.response(bool,"查询", list);
    }


    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<UserEntity> users = userService.query().eq("status", "1").list();
        // 设置响应内容
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        Set<String> includeColumnFiledNames = new HashSet<>();
        includeColumnFiledNames.add("username");
        includeColumnFiledNames.add("startTime");
        includeColumnFiledNames.add("stopTime");
        includeColumnFiledNames.add("createdBy");
        includeColumnFiledNames.add("creationDate");
        EasyExcelFactory.write(response.getOutputStream(), UserEntity.class)
                .includeColumnFieldNames(includeColumnFiledNames)
                .sheet("用户记录")
                .doWrite(users);
    }


}

