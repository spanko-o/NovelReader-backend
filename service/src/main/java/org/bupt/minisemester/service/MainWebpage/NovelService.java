package org.bupt.minisemester.service.MainWebpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.bupt.minisemester.dao.mapper.NovelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class NovelService {
    @Autowired
    private NovelMapper novelMapper;

    public List<Map<String,String>> getNovel(){
        try{

            return novelMapper.selectNovelForMain();
        }
        catch(Exception e){

            throw new RuntimeException("Failed to retrieve novel data", e);
        }


    }

}
