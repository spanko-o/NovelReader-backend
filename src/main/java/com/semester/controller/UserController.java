package com.semester.controller;

import com.semester.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.semester.utils.R;
import com.semester.jwt.*;
import com.semester.mapper.usernameMapper;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String storedPassword = userService.getPassword(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            String token = JwtUtil.sign(username, password);
            return R.ok("登录成功", token);
        } else {
            return R.failure("用户名或密码错误");
        }
    }

    @PostMapping("/signup")
    public R signup(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name) {
        if (username != null && password != null && name != null) {
            boolean success = userService.insert(username, password, name);
            if (success) {
                return R.ok("注册成功");
            }
        }
        return R.failure("注册失败");
    }


    @GetMapping("/test")
    public R test() {
        return R.ok("成功");
    }
}
