package org.bupt.minisemester.web;

import org.bupt.minisemester.common.jwt.JwtToken;
import org.bupt.minisemester.common.jwt.JwtUtil;
import org.bupt.minisemester.common.util.R;
import org.bupt.minisemester.service.NovelServiceGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.bupt.minisemester.dao.entity.Novel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class NovelController {

    @Autowired
    private NovelServiceGlobal novelService;

    @JwtToken
    @GetMapping("/{id}")
    public List<Map<String, String>> getNovel(@PathVariable int id) {
           return novelService.getBookUploaded(id);
    }

    @PostMapping("/import")
    public R importNovel(@RequestParam("file") MultipartFile file) {
        try{
            System.out.println("接收到文件：" + file.getOriginalFilename());
            //以文件名作为title
            String title = file.getOriginalFilename();
            if(title == null || title.isEmpty()){
                return R.failure("文件名不能为空");
            }
            //去除扩展名
            title = title.replaceAll("\\.txt$","");

            //读取内容
            String content = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            System.out.println("开始导入小说：" + title);
            novelService.importNovel(title, content);
            System.out.println("小说导入成功");
            return R.ok("小说导入成功");
        }catch (Exception e){
            return R.failure(e.getMessage());
        }
    }

    @PostMapping("/add")
    public R addBookUploaded(@RequestBody Novel novel) {
        try {
            novelService.addBookUploaded(novel);
        } catch (Exception e) {
            return R.failure(e.getMessage());
        }
        return R.ok("小说信息上传成功");
    }

}
