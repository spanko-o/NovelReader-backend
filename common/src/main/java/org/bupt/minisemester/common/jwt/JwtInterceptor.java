package org.bupt.minisemester.common.jwt;

import org.bupt.minisemester.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtInterceptor(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);

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

                token = token.substring(7); // 移除 "Bearer " 前缀

                if (jwtUtil.checkSign(token, userService)) {
                    return true;
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token validation failed");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 可以根据需要实现 postHandle 逻辑
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可以根据需要实现 afterCompletion 逻辑
    }
}
