package com.projectpractice.config;

import com.projectpractice.common.JacksonObjectHandler;
import com.projectpractice.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.config
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  19:40
 * @Description: webMvc配置
 * @Version: 1.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor());
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器
        messageConverter.setObjectMapper(new JacksonObjectHandler());
        //将消息转换器对象加入mvc的转换器集合中
        converters.add(0, messageConverter);
    }
}
