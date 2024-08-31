package org.bupt.minisemester.web;

import org.bupt.minisemester.service.ZhipuAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZhupuAiController {
    private final ZhipuAiService zhipuAiService;

    @Autowired
    public ZhupuAiController(ZhipuAiService zhipuAiService) {
        this.zhipuAiService = zhipuAiService;
    }

    @PostMapping("ai/generateRoast")
    public String generateRoast(@RequestParam("input") String input) {
        return zhipuAiService.generateRoast(input);
    }
}
