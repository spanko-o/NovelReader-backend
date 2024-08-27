package com.semester;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.semester.mapper")
public class semesterApplication {
    public static void main(String[] args) {
        SpringApplication.run(semesterApplication.class, args);
    }

}
