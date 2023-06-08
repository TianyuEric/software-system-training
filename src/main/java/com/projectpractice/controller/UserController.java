package com.projectpractice.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.projectpractice.common.HttpResponseEntity;
import com.projectpractice.entity.UserEntity;
import com.projectpractice.service.UserService;
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

    /**
     * 用户登录
     * @param userEntity 用户实体
     * @param response HttpServletResponse
     * @return HttpResponseEntity
     */
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

    /**
     * 新增用户
     * @param userEntity 用户实体
     * @return HttpResponseEntity
     */
    @PostMapping("/addUserInfo")
    public HttpResponseEntity addUserinfo(@RequestBody UserEntity userEntity){
        boolean bool = userService.save(userEntity);
        return HttpResponseEntity.response(bool, "创建", null);
    }

    /**
     * 修改用户
     * @param userEntity 用户实体
     * @return HttpResponseEntity
     */
    @PostMapping("/modifyUserInfo")
    public HttpResponseEntity modifyUserinfo(@RequestBody UserEntity userEntity){
        boolean bool = userService.updateById(userEntity);
        return HttpResponseEntity.response(bool, "修改", null);
    }

    /**
     * 删除用户
     * @param userEntity 用户实体
     * @return HttpResponseEntity
     */
    @PostMapping("/deleteUserinfo")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity){
        boolean bool = userService.removeById(userEntity);
        return HttpResponseEntity.response(bool, "删除", null);
    }

    /**
     * 用户列表查询
     * @param map 查询参数
     * @return HttpResponseEntity
     */
    @PostMapping("/queryUserList")
    public HttpResponseEntity queryUserList(@RequestBody Map<String, Object> map){
        Page<UserEntity> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        userService.query().eq("status", "1").page(page);
        List<UserEntity> list = page.getRecords();
        boolean bool = list.isEmpty();
        return HttpResponseEntity.response(!bool,"查询", list);
    }

    /**
     * 用户excel导出
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
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

