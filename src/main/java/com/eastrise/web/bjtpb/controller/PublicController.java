package com.eastrise.web.bjtpb.controller;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.service.admin.ArticleService;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
    @RequestMapping("/articlefile/{id}")
    public String toWzView(@PathVariable String id, HttpServletResponse response) throws Exception {
        List<Map<String, Object>> filea= articleService.getFilebyId(id);
        String fileName=filea.get(0).get("ATTACHMENT_NAME").toString();
        String filePath=filea.get(0).get("ATTACHMENT_PATH").toString();
        if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(fileName)) {
            response.reset();
            response.setContentType("application/force-download");// 设置强制下载不打开
            //从path中读取文件
            try{
                filePath = URLDecoder.decode( filePath , "UTF-8") ;
                fileName = URLDecoder.decode( fileName , "UTF-8") ;
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));// 设置文件名
            }catch (Exception e){
                e.printStackTrace();
            }
            File file = null;
            try {
                file = new File(filePath);
                if (!file.exists()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return filePath;
        }
        return null;
    }


}
