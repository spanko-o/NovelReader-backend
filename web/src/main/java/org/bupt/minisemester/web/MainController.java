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
    @GetMapping("/Main/p={page}")
    public R NovelList(@PathVariable("page") String p) {
        try {
        int  page;
        if(p.isEmpty())
        {
          page=1;
        }else{
        page = Integer.parseInt(p);
        }
        int pageSize = 4;
        int offset = (page - 1) * pageSize;
        String cacheKey = "novelPage_" + page;

        List<Map<String, String>> paginatedList = cache.getIfPresent(cacheKey);

        if (paginatedList == null) {
            List<Map<String, String>> novellist = novelService.getNovel();
            paginatedList = novellist.subList(Math.min(offset, novellist.size()),
                    Math.min(offset + pageSize, novellist.size()));//计算从哪里开始截取页面
            cache.put(cacheKey, paginatedList);
        }

        return R.ok(paginatedList);
    }
    catch(Exception e){
        throw new RuntimeException("Failed to get novel data", e);
        }
    }
    @JwtToken
    @PostMapping("/search")
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
