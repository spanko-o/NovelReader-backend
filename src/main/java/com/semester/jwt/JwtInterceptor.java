package com.semester.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.auth0.jwt.interfaces.Claim;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import com.semester.service.UserService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    private static final String SECRET = "buptnovel";

    public boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            // 验证token

            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("无效token，请重新获取");
        }


    }




    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 仅在方法或类上有 @JwtToken 注解时才进行验证
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);

            // 检查类上是否有 JwtToken 注解
            if (jwtToken == null) {
                jwtToken = handlerMethod.getBeanType().getAnnotation(JwtToken.class);
            }

            if (jwtToken != null && jwtToken.required()) {
                String token = request.getHeader("Authorization");

                if (token == null || !token.startsWith("Bearer ")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token is missing or invalid");
                    return false;
                }

                try {
                    // 移除 "Bearer " 前缀
                    token = token.substring(7);

                    if(checkSign(token)){

                        return true;
                    };

                    System.out.println(token);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token validation failed");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
