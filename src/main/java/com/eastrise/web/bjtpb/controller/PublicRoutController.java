package com.eastrise.web.bjtpb.controller;
import com.alibaba.fastjson.JSONObject;
import com.eastrise.base.TSysUser;
import com.eastrise.web.base.Constants;
import com.eastrise.web.bjtpb.entity.TArticle;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import com.eastrise.web.bjtpb.service.admin.*;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  路由控制类
 * create by gzq on 2018/11/13 13:34
 */
@Controller
@RequestMapping("/public")
public class PublicRoutController {

    @Autowired
    private ArticleManageService articleManageService;
    @Autowired
    private SjzdService sjzdService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{code}")
    public String bmjj(@PathVariable String code,HttpServletRequest request) throws Exception{
        getMenuList(request);
        TArticleManage articleManage = articleManageService.findArticleByCode(code,"1");
        getRoutByArticleTypeId(request,articleManage.getId()+"");
        String parentId = articleManage.getCategoryseq().split("\\.")[0];
        List<TArticleManage> articleManageList = articleManageService.findArticleByParentId(parentId,"1");

        List<Map<String,Object>> result = Lists.newArrayList();
        Map m = new HashMap();
        for(TArticleManage a:articleManageList){
            m = new HashMap();
            m.put("name",a.getCategoryname());
            m.put("value",a.getCategorycode());
            m.put("url",a.getUrl());
            if(articleManage.getCategoryseq().split("\\.").length>1&&articleManage.getCategoryseq().split("\\.")[1].equals(a.getId()+"")){
                m.put("check",true);
            }else{
                m.put("check",false);
            }
            List<TArticleManage> articleManages = articleManageService.findArticleByParentId(a.getId()+"","1");
            m.put("sons",articleManages);
            result.add(m);
        }
        request.setAttribute("sstj",sjzdService.findsstj());
        request.setAttribute("id",articleManage.getId());
        request.setAttribute("result",result);
        if(Constants.CKMS_LBMS.equals(articleManage.getViewModel())){
            return "/public/wjdb/wjdb_lbms.jsp";
        }else if(Constants.CKMS_TWMS.equals(articleManage.getViewModel())){
            List<TArticle> articles = articleService.getArticleListByArticleTypeId(articleManage.getId()+"","tw");
            request.setAttribute("article",articles.size()>0?articles.get(0):new TArticle());
            return "/public/wjdb/wjdb_twms.jsp";
        }else if(Constants.CKMS_SSLB.equals(articleManage.getViewModel())){
            return "/public/wjdb/wjdb_sslb.jsp";
        }else{
            return "/public/wjdb/wjdb_sslb.jsp";
        }
    }




    @GetMapping("/{type}/{code}")
    public String wjdbmx(@PathVariable String type,@PathVariable String code,HttpServletRequest request) throws Exception{
        getMenuList(request);
        TArticleManage articleManage = articleManageService.findArticleByCode(code,"1");
        getRoutByArticleTypeId(request,articleManage.getId()+"");
        String parentId = articleManage.getCategoryseq().split("\\.")[0];
        List<TArticleManage> articleManageList = articleManageService.findArticleByParentId(parentId,"1");

        List<Map<String,Object>> result = Lists.newArrayList();
        Map m = new HashMap();
        for(TArticleManage a:articleManageList){
            m = new HashMap();
            m.put("name",a.getCategoryname());
            m.put("value",a.getCategorycode());
            m.put("url",a.getUrl());
            if(articleManage.getCategoryseq().split("\\.").length>1&&articleManage.getCategoryseq().split("\\.")[1].equals(a.getId()+"")){
                m.put("check",true);
            }else{
                m.put("check",false);
            }
            List<TArticleManage> articleManages = articleManageService.findArticleByParentId(a.getId()+"","1");
            m.put("sons",articleManages);
            result.add(m);
        }
        request.setAttribute("sstj",sjzdService.findsstj());
        request.setAttribute("id",articleManage.getId());
        request.setAttribute("result",result);
        if(Constants.CKMS_LBMS.equals(articleManage.getViewModel())){
            return "/public/wjdb/wjdb_lbms.jsp";
        }else if(Constants.CKMS_TWMS.equals(articleManage.getViewModel())){
            List<TArticle> articles = articleService.getArticleListByArticleTypeId(articleManage.getId()+"","tw");
            request.setAttribute("article",articles.size()>0?articles.get(0):new TArticle());
            return "/public/wjdb/wjdb_twms.jsp";
        }else if(Constants.CKMS_SSLB.equals(articleManage.getViewModel())){
            return "/public/wjdb/wjdb_sslb.jsp";
        }else{
            return "/public/wjdb/wjdb_sslb.jsp";
        }
    }

    @GetMapping("/more/{code}")
    public String more(@PathVariable String code,HttpServletRequest request){
        getMenuList(request);
        TArticleManage articleManage = articleManageService.findArticleByCode(code,"1");
        getRoutByArticleTypeId(request,articleManage.getId()+"");
        request.setAttribute("id",articleManage.getId());
        return "/public/more/more.jsp";
    }

    @GetMapping("/content/{id}")
    public String toWzView(@PathVariable String id, HttpServletRequest request) throws Exception {
        getMenuList(request);
        List<Map<String, Object>> list =articleService.findById(id);
        if(list.size()==0){
            return "/public/wznr/wznr_404.jsp";
        }
        Map<String, Object> map = list.get(0);
        getRoutByArticleTypeId(request,map.get("ARTICLE_TYPE_ID")==null?"":map.get("ARTICLE_TYPE_ID").toString());
        request.setAttribute("wz",map);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&!authentication.getPrincipal().equals("anonymousUser")&&authentication.isAuthenticated()){

        }else{
            if("0".equals(map.get("IS_OPEN").toString())){
                return "/public/wznr/wznr_401.jsp";
            }
        }

        articleService.upLookTotal(id);
        return "/public/wznr/wznr.jsp";
    }

    @RequestMapping("/articlefile/{id}")
    public String toWzView(@PathVariable String id, HttpServletResponse response, HttpServletRequest request) throws Exception {

        getMenuList(request);
        List<Map<String, Object>> list =articleService.findById(id);
        if(list.size()==0){
            return "/public/wznr/wznr_404.jsp";
        }
        Map<String, Object> map = list.get(0);
        getRoutByArticleTypeId(request,map.get("ARTICLE_TYPE_ID")==null?"":map.get("ARTICLE_TYPE_ID").toString());
        request.setAttribute("wz",map);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&!authentication.getPrincipal().equals("anonymousUser")&&authentication.isAuthenticated()){

        }else{
                if("0".equals(map.get("IS_OPEN").toString())){

                    return "/public/wznr/wznr_401.jsp";
                }
        }

        articleService.upLookTotal(id);
        List<Map<String, Object>> filea= articleService.getFilebyId(id);
        String fileName=filea.get(0).get("ATTACHMENT_NAME").toString();
        String filePath=filea.get(0).get("ATTACHMENT_PATH").toString();
        String fileId=filea.get(0).get("ID").toString();
        String fileType=filea.get(0).get("ATTACHMENT_TYPE").toString();
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
                file = new File(filePath,fileId+"."+fileType);
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

    public void getMenuList(HttpServletRequest request){
        List<TArticleManage> articleManageList = articleManageService.findArticleByParentId("0","1");
        for(TArticleManage articleManage:articleManageList){
            List result = articleManageService.findArticleByParentId(articleManage.getId()+"","1");
            articleManage.setChildren(result);
        }
        request.setAttribute("menuList",articleManageList);
    }

    public void getRoutByArticleTypeId(HttpServletRequest request,String articleTypeId){
        List<TArticleManage> result = articleManageService.getCategorySeqListById(articleTypeId);
        String rout="当前位置：";
        for(int i=0;i<result.size();i++){
            rout+="<a href=\""+((result.get(i).getUrl()==null||articleTypeId.equals(new String(""+result.get(i).getId())))?"#":("../"+result.get(i).getUrl()))+"\"  ";
            if(result.get(i).getUrl()==null||articleTypeId.equals(""+result.get(i).getId())){
            }else{
              //  rout+="  target=\"_blank\" ";
            }
            rout+="title=\""+result.get(i).getCategoryname()+"\">"+result.get(i).getCategoryname()+"</a>";
            if(i!=result.size()-1){
                rout+=" >> ";
            }else{

            }
        }
        request.setAttribute("routList",result);

    }

}
