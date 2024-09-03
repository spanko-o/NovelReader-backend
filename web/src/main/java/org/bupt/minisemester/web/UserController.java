package org.bupt.minisemester.web;

import org.bupt.minisemester.common.jwt.JwtToken;
import org.bupt.minisemester.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bupt.minisemester.common.util.R;
import org.bupt.minisemester.common.jwt.*;
import java.util.UUID;

import org.bupt.minisemester.common.jwt.JwtUtil;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;  // 通过依赖注入获取 JwtUtil 实例


    @PostMapping("/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String storedPassword = userService.getPassword(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            String uid = UserService.getUidByUsername(username);
            if(uid == null) {
                return R.failure("用户id未找到");
            }
            String token = jwtUtil.sign(username, password, uid);  // 使用实例方法而非静态方法
            return R.ok("登录成功", token);
        } else {
            return R.failure("用户名或密码错误");
        }
    }

    @PostMapping("/signup")
    public R signup(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("re_password") String re_password) {


        String userId = UUID.randomUUID().toString();
        if (username != null && password != null) {
            boolean success = userService.insert(userId,username, password);
            if (success) {
                return R.ok("注册成功");
            }
        }
        return R.failure("注册失败");
    }

    @JwtToken
    @GetMapping("/test")
    public R test() {
        return R.ok("成功");
    }
}
