package org.bupt.minisemester.web;

import org.bupt.minisemester.common.jwt.JwtToken;
import org.bupt.minisemester.common.util.R;
import org.bupt.minisemester.service.ZhipuAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/ai")
public class ZhupuAiController {
    private final ZhipuAiService zhipuAiService;

    @Autowired
    public ZhupuAiController(ZhipuAiService zhipuAiService) {
        this.zhipuAiService = zhipuAiService;
    }

    @JwtToken
    @PostMapping("/generateRoast")
    public R generateRoast(@RequestBody Map<String, Object> params) {
        String input= (String) params.get("input");
        try {
            String output = zhipuAiService.generateRoast(input);
            return R.ok(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
