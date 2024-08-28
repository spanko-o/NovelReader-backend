package org.bupt.minisemester.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*测试*/
@RestController
public class UserController {
    @GetMapping("/user/{name}")
    public String queryUserByName(@PathVariable String name) {
        return "查询到用户 " + name;
    }
}
