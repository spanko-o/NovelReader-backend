package com.semester.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 添加这个注解，表明这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/User/login", "/User/signup"); ; // 拦截所有请求，通过判断是否有 @JwtToken 注解 决定是否需要登录
    }

}
