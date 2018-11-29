package com.eastrise.web.bjtpb.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.utils.DateHelper;
import com.eastrise.utils.OperationFileUtil;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleContentForm;
import com.eastrise.web.bjtpb.controller.admin.form.ArticleTypeForm;
import com.eastrise.web.bjtpb.controller.admin.form.AttachmentForm;
import com.eastrise.web.bjtpb.entity.*;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.service.admin.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private UserService userService;

    @Value("${file-service-path}")
    private String path;

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
            if(!manageService.isArticleCodeExist(articleTypeForm)){
                return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该文章编码已存在，不能重复添加");
            }
            TArticleManage tArticleManage=new TArticleManage();
            BeanUtils.copyProperties(articleTypeForm,tArticleManage);
            tArticleManage= manageService.savearticleManage(tArticleManage);
            if(articleTypeForm.getParentid().equals("0")){
                tArticleManage.setCategoryseq(tArticleManage.getId()+"");
                tArticleManage.setUrl("public/"+articleTypeForm.getCategorycode());
            }else{
                TArticleManage prticleManage = manageService.findArticle(articleTypeForm.getParentid());

                String[] a=prticleManage.getCategoryseq().split("\\.");
                TArticleManage tArticleManage1=manageService.findArticle(a[0]);
                tArticleManage.setUrl("public/"+tArticleManage1.getCategorycode()+"/"+articleTypeForm.getCategorycode());
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

    @PostMapping("/getArticleContent")
    @ResponseBody
    public String getArticleContent(ArticleContentForm articleContentForm)throws Exception{
        return manageService.getArticleContent(articleContentForm).toString();
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
        request.setAttribute("tAttachment",manageService.findTAttachmentByBuzId(id));
        return "/admin/article/articleAdd.jsp";
    }

    @PostMapping(value = "/saveArticleContent",produces = "text/html;charset:utf-8")
    @ResponseBody
    public ApiResponse saveArticleContent(ArticleContentForm articleContentForm, AttachmentForm attachmentForm, @RequestParam(value="file1")MultipartFile file
           , boolean isAttachmentupdate )throws Exception{
        if(StringUtils.isNotEmpty(articleContentForm.getId())){
            //修改
            TArticle tArticle = manageService.findArticleContByid(articleContentForm.getId());
            BeanUtils.copyProperties(articleContentForm,tArticle);
            try{
                tArticle = manageService.saveArticleContent(tArticle);
            }catch (Exception e){
                e.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
            }
            if(isAttachmentupdate){
                manageService.delAttachment(attachmentForm.getAttachmentId());
                OperationFileUtil.deleteFile(attachmentForm.getAttachmentPath());
                if(!uplodFile(file,articleContentForm,tArticle.getId())){
                    return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
                }
            }
        }else{
            //验重
            if(!manageService.isArticleContentExist(articleContentForm)){
                return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该文章已存在，不能重复添加");
            }
            //添加
            TArticle tArticle =new TArticle();
            LocalUserDetails localUserDetails =userService.findUserDetails();
            tArticle.setCreateUserId(localUserDetails.getId());
            tArticle.setCreateUserName(localUserDetails.getUserName());
            BeanUtils.copyProperties(articleContentForm,tArticle);
            try{
                tArticle=  manageService.saveArticleContent(tArticle);
            }catch (Exception e){
                e.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
            }
            if(!uplodFile(file,articleContentForm,tArticle.getId())){
                return ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
            }
        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }

    public Boolean uplodFile(MultipartFile file,ArticleContentForm articleContentForm,String buzid){
        if(!file.isEmpty()){
            TArticleManage tArticleManage=manageService.findArticle(articleContentForm.getArticleTypeId());
            String fileNames = file.getOriginalFilename();
            if(fileNames.contains("\\")){
                String[] Str1Array = fileNames.split("\\\\");
                fileNames = Str1Array[Str1Array.length-1];
            }
            String prefix = fileNames.substring(fileNames.lastIndexOf(".") + 1);
            String filePath=path+articleContentForm.getPublishTime().substring(0,4)+"/"+tArticleManage.getCategoryseq().replace(".","/");
            try {
                OperationFileUtil.uploadFile(file, filePath, fileNames);
            }catch (Exception e){
                System.out.println("文件保存失败！");
                e.printStackTrace();
                return false;
            }
            LocalUserDetails localUserDetails =userService.findUserDetails();
            TAttachment tAttachment =new TAttachment();
            tAttachment.setAttachmentName(fileNames);
            tAttachment.setAttachmentPath(filePath);
            tAttachment.setAttachmentType(prefix);
            tAttachment.setBuzId(buzid);
            tAttachment.setBuzTable("T_ARTICLE");
            tAttachment.setFileType(prefix);
            tAttachment.setUploadDeptId(localUserDetails.getDeptId()+"");
            tAttachment.setUploadDeptName(localUserDetails.getDeptName());
            tAttachment.setUploadTime(DateHelper.getDateTime());
            tAttachment.setUploadUserId(localUserDetails.getId());
            tAttachment.setUploadUserName(localUserDetails.getUserName());
            manageService.saveAttachment(tAttachment);
        }
        return true;
    }

    @PostMapping(value = "/delArticleCont")
    @ResponseBody
    public ApiResponse delArticleCont(String id){
        TArticle tArticle = manageService.findArticleContByid(id);
        if(tArticle.getArticleTag().equals("fj")){
            TAttachment tAttachment =manageService.findTAttachmentByBuzId(id);
            if(tAttachment!=null){
                manageService.delAttachment(tAttachment.getId());
                OperationFileUtil.deleteFile(tAttachment.getAttachmentPath());
            }
        }
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
    @PostMapping(value = "/listSysSjzd",produces = "text/html;charset:utf-8")
    @ResponseBody
    public List listSysSjzd()throws Exception{
        return manageService.findSysSjzdList();
    }
}
