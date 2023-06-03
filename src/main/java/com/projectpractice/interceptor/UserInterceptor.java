package com.projectpractice.interceptor;

import com.projectpractice.common.UserMessage;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        for (Cookie cookie :cookies) {
            if (cookie.getName().equals("username")){
                UserMessage.setUsername(cookie.getValue());
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        UserMessage.remove();
    }
}
