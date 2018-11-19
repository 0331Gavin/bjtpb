package com.eastrise.web.base;

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

/**
 * create by gzq on 2018/10/30 15:08
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {

        return "index.jsp";
    }

    @RequestMapping(value = {"/", "/index.html", "/index"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&!authentication.getPrincipal().equals("anonymousUser")&&authentication.isAuthenticated()){
            return "index.jsp";
        }
        return "index.jsp";
    }

    @RequestMapping(value = {"/loginSuccess"}, method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request) {

        return "home.jsp";
    }
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")//这里可以指定特定角色的用户访问权限
    //  @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = {"/adminIndex"}, method = RequestMethod.GET)
    public String adminIndex(HttpServletRequest request) {

        return "admin/index.jsp";
    }


}
