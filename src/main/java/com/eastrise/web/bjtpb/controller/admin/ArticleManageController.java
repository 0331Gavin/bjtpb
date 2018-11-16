package com.eastrise.web.bjtpb.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * create by FQY
 */

@Controller
@RequestMapping("/admin/article")
public class ArticleManageController {
    @Autowired
    private ArticleManageService manageService;

    @RequestMapping("/getArticleCategory")
    @ResponseBody
    public JSONObject getArticleCategory(){
        JSONObject json =new JSONObject();
        try {
            List<Map<String, Object>> arrList = manageService.getArticleGateGory();
            json.put("rows",arrList);
        }catch (Exception e){

        };
        return json;
    }

    @GetMapping("/addChild")
    public String articleaddChild(String id, HttpServletRequest request){
        request.setAttribute("parentid",id);
        return "/admin/articleType/articleTypeChildAdd.jsp";
    }

    @GetMapping("/toupdataArticle")
    public String toupdataArticle(String id, HttpServletRequest request){
        request.setAttribute("id",id);
        TArticleManage tArticleManage =manageService.findArticle(id);
        request.setAttribute("parentid",tArticleManage.getParentid());
        request.setAttribute("articleManage",tArticleManage);
        return "/admin/articleType/articleTypeChildAdd.jsp";
    }


    @PostMapping("/save")
    @ResponseBody
    public String save(ArticleTypeForm articleTypeForm)throws Exception{
        //验重

        //id判断新增   修改
        JSONObject json =new JSONObject();
        if(StringUtils.isNotEmpty(articleTypeForm.getId())){
            TArticleManage tArticleManage = manageService.findArticle(articleTypeForm.getId());
            BeanUtils.copyProperties(articleTypeForm,tArticleManage);
            manageService.save(tArticleManage);
        }else{
            TArticleManage tArticleManage=new TArticleManage();
            BeanUtils.copyProperties(articleTypeForm,tArticleManage);
            manageService.save(tArticleManage);
        }
        json.put("code","saveSuccessCode");
        json.put("message","保存成功");
       return json.toString();
    }

    @PostMapping(value = "/del")
    @ResponseBody
    public String del(String id){
        JSONObject json =new JSONObject();
        manageService.del(id);
        json.put("code","delSuccessCode");
        json.put("message","删除成功");
        return json.toString();
    }
}
