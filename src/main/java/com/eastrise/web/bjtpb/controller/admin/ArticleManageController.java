package com.eastrise.web.bjtpb.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleContentForm;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.entity.TArticle;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import org.bouncycastle.crypto.tls.ContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/toArticleaddChild")
    public String toArticleaddChild(String id, HttpServletRequest request){
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


    @PostMapping(value = "/saveArticle",produces = "text/html;charset:utf-8")
    @ResponseBody
    public ApiResponse saveArticle(ArticleTypeForm articleTypeForm)throws Exception{
        //id判断新增   修改
        if(StringUtils.isNotEmpty(articleTypeForm.getId())){
            TArticleManage tArticleManage = manageService.findArticle(articleTypeForm.getId());
            BeanUtils.copyProperties(articleTypeForm,tArticleManage);
            manageService.savearticleManage(tArticleManage);
        }else{
            //验重
            /*if(!manageService.isArticleExist(articleTypeForm)){
                return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该文章类别已存在，不能重复添加");
            }*/
            TArticleManage tArticleManage=new TArticleManage();
            BeanUtils.copyProperties(articleTypeForm,tArticleManage);
            tArticleManage= manageService.savearticleManage(tArticleManage);
            if(articleTypeForm.getParentid().equals("0")){
                tArticleManage.setCategoryseq(tArticleManage.getId()+"");
            }else{
                TArticleManage prticleManage = manageService.findArticle(articleTypeForm.getParentid());
                tArticleManage.setCategoryseq(prticleManage.getCategoryseq()+"."+tArticleManage.getId());
            }
            manageService.savearticleManage(tArticleManage);
        }
      return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }

    @PostMapping(value = "/delArticle")
    @ResponseBody
    public ApiResponse delArticle(String id){
        manageService.delArticle(id);
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }

    @GetMapping("/getArticleContent")
    @ResponseBody
    public String getArticleContent()throws Exception{
        return manageService.getArticleContent().toString();
    }

    @GetMapping("/toAddArticleContent")
    public String toAddArticleContent(String articleTag,HttpServletRequest request){
        request.setAttribute("articleTag",articleTag);
        return "/admin/article/articleAdd.jsp";
    }

    @GetMapping("/toUpdateArticleCont")
    public String toUpdateArticleCont(String id,HttpServletRequest request){
        request.setAttribute("id",id);
        TArticle tArticle = manageService.findArticleContByid(id);
        request.setAttribute("articles",tArticle);
        request.setAttribute("articleTag",tArticle.getArticleTag());
        return "/admin/article/articleAdd.jsp";
    }
    @PostMapping(value = "/saveArticleContent",produces = "text/html;charset:utf-8")
    @ResponseBody
    public ApiResponse saveArticleContent(HttpServletRequest request,ArticleContentForm articleContentForm,@RequestParam(value="file1")MultipartFile
            file1)throws Exception{

        if(StringUtils.isNotEmpty(articleContentForm.getId())){
            //修改
            TArticle tArticle = manageService.findArticleContByid(articleContentForm.getId());
            BeanUtils.copyProperties(articleContentForm,tArticle);
            try{
                manageService.saveArticleContent(tArticle);
            }catch (Exception e){
                e.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
            }
        }else{
            //验重
            if(!manageService.isArticleContentExist(articleContentForm)){
                return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该文章已存在，不能重复添加");
            }
            //添加
            TArticle tArticle =new TArticle();
            BeanUtils.copyProperties(articleContentForm,tArticle);
            try{
                manageService.saveArticleContent(tArticle);
            }catch (Exception e){
                e.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
            }
        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }

    @PostMapping(value = "/delArticleCont")
    @ResponseBody
    public ApiResponse delArticleCont(String id){
        manageService.delArticleCont(id);
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }
    @PostMapping("/listArticles")
    @ResponseBody
    public List<TArticleManage> listArticles(){
        List<TArticleManage> articles = manageService.findChildArticleById();
        System.out.println(JSONObject.toJSONString(articles));
        return articles;
    }


}
