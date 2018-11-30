package com.eastrise.web.bjtpb.controller;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.service.admin.ArticleService;
import com.eastrise.web.bjtpb.service.admin.YqljService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * create by gzq on 2018/10/30 15:08
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private YqljService yqljService;
    @Autowired
    private ArticleManageService articleManageService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) throws  Exception{
        request.setAttribute("yqlj",yqljService.findAll());
        getArticleList(request,"wjdb");
        getArticleList(request,"fgzd");
        getArticleList(request,"jcapjzj");
        getArticleList(request,"sggzdc");
        getArticleList(request,"jcdy");
        getArticleList(request,"dqgz");



        getMenuList(request);
        return "public/index.jsp";
    }

    @RequestMapping(value = {"/", "/index.html", "/index"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        request.setAttribute("yqlj",yqljService.findAll());
        getArticleList(request,"wjdb");
        getArticleList(request,"fgzd");
        getArticleList(request,"jcapjzj");
        getArticleList(request,"sggzdc");
        getArticleList(request,"jcdy");
        getArticleList(request,"dqgz");
        getMenuList(request);
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&!authentication.getPrincipal().equals("anonymousUser")&&authentication.isAuthenticated()){
            return "public/index.jsp";
        }
        return "public/index.jsp";
    }

    @RequestMapping(value = {"/loginSuccess"}, method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request) {

        return "public/index.jsp";
    }
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")//这里可以指定特定角色的用户访问权限
    //  @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = {"/adminIndex"}, method = RequestMethod.GET)
    public String adminIndex(HttpServletRequest request) {

        return "admin/index.jsp";
    }

    private void getArticleList(HttpServletRequest request,String code){

        TArticleManage articleManage = articleManageService.findArticleByCode(code,"1");
        ApiPageResponse response = articleService.findPublicPageData(10,1,articleManage.getId()+"",null, null);
        request.setAttribute(code+"",response.getRows());
    }

    public void getMenuList(HttpServletRequest request){
        List<TArticleManage> articleManageList = articleManageService.findArticleByParentId("0","1");
        for(TArticleManage articleManage:articleManageList){
            List result = articleManageService.findArticleByParentId(articleManage.getId()+"","1");
            articleManage.setChildren(result);
        }
        System.out.println(JSONObject.toJSONString(articleManageList));
        request.setAttribute("menuList",articleManageList);
    }
}
