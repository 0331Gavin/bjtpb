package com.eastrise.web.bjtpb.controller;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.service.admin.ArticleService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
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
    @Autowired
    private ArticleService articleService;

    @PostMapping("/getRoutByArticleTypeId")
    public List getRoutByArticleTypeId(String articleTypeId){
        List result = articleManageService.getCategorySeqListById(articleTypeId);
        return result;
    }
    @PostMapping("/getChannelType")
    public List getChannelType(String articleTypeId){
        return articleManageService.findArticleByParentId(articleTypeId,"1");
    }

    @PostMapping("/articlePageList")
    public JSONObject articlePageList(@RequestParam(value = "page")int pageNumber, @RequestParam(value = "rows") int pageSize,
                    String ChannelType,String KeyWord,String KeyWordType){
        ApiPageResponse response = articleService.findPublicPageData(pageSize,pageNumber,ChannelType,KeyWord, KeyWordType);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", response.getTotal());
        jsonObject.put("data", response.getRows());
        return jsonObject;
    }



}
