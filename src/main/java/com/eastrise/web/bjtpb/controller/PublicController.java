package com.eastrise.web.bjtpb.controller;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/22 10:17
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private ArticleManageService articleManageService;

    @PostMapping("/getRoutByArticleTypeId")
    public List getRoutByArticleTypeId(String articleTypeId){
        List result = articleManageService.getCategorySeqListById(articleTypeId);
        return result;
    }

    @PostMapping("/articlePageList")
    public JSONObject articlePageList(@RequestParam(value = "page")int pageNumber, @RequestParam(value = "rows") int pageSize){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", 50);
        List<Map> data = Lists.newArrayList();
        Map m= new HashMap();
        m.put("id","aaaaaaaaa");
        m.put("title","复兴号“京湘专用车厢”精彩亮相,复兴号“京湘专用车厢”精彩亮相，复兴号“京湘专用车厢”精彩亮相");
        m.put("deptName","安监局");
        m.put("time","2018-09-30");
        for(int i=0;i<pageSize;i++){
            data.add(m);
        }
        jsonObject.put("data", data);

        return jsonObject;
    }


}
