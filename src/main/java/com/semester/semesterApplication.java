package com.semester;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.semester.jwt.JwtInterceptor;
@SpringBootApplication(scanBasePackages = "com.semester")
@MapperScan("com.semester.mapper")
public class semesterApplication {
    public static void main(String[] args) {
        SpringApplication.run(semesterApplication.class, args);
    }
    @Autowired
    public void setInterceptor(JwtInterceptor interceptor) {
        System.out.println("JwtInterceptor Bean: " + interceptor);
    }
}
