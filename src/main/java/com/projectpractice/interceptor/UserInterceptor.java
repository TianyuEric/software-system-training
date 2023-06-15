package com.projectpractice.interceptor;

import com.projectpractice.common.UserMessage;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.interceptor
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  19:30
 * @Description: 拦截器获取cookie
 * @Version: 1.0
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Cookie[] cookies = request.getCookies();
        Arrays.stream(Optional.ofNullable(cookies).orElse(new Cookie[0])).filter(e -> e.getName().equals("username"))
                .findFirst().ifPresent(e-> UserMessage.setUsername(e.getValue()));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        UserMessage.remove();
    }
}
