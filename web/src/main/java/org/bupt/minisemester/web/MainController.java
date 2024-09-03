package org.bupt.minisemester.web;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.bupt.minisemester.common.jwt.JwtToken;
import org.bupt.minisemester.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bupt.minisemester.service.MainWebpage.NovelService;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bupt.minisemester.service.MainWebpage.searchService;
import org.bupt.minisemester.service.MainWebpage.indexService;
@RestController
public class MainController {

    @Autowired
    private NovelService novelService;

    private Cache<String, List<Map<String, String>>> cache;

    @Autowired
    private indexService indexService;
    @Autowired
    private searchService searchService;



    @PostConstruct
    public void init() {
        indexService.createIndex();

        cache = Caffeine.newBuilder()
                .maximumSize(20)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @JwtToken
    @GetMapping("/Main")
    public R NovelList(@RequestParam(value = "page", defaultValue = "1") int page) {
        try {
            int pageSize = 4; // 每页显示4条数据
            int maxPages = 3; // 最大页数为3页
            int offset = (page - 1) * pageSize;

            // 获取所有小说数据
            List<Map<String, String>> novellist = novelService.getNovel();

            // 如果总数据量大于12条，截取前12条
            if (novellist.size() > pageSize * maxPages) {
                novellist = novellist.subList(0, pageSize * maxPages);
            }

            // 计算当前页的分页数据
            List<Map<String, String>> paginatedList = novellist.subList(
                    Math.min(offset, novellist.size()),
                    Math.min(offset + pageSize, novellist.size())
            );

            // 仅返回当前页的数据列表
            return R.ok(paginatedList);
        } catch (Exception e) {
            return R.failure("Failed to get novel data: " + e.getMessage());
        }
    }

    @JwtToken
    @GetMapping("/search")
    public R search(@RequestParam("query") String query) {
        try{

        List<Map<String, Object>> resultlist=searchService.searchIndex(query);
        return R.ok(resultlist);
    }
        catch(Exception e){
        e.printStackTrace();
        return R.failure(e.getMessage());}
    }
}
