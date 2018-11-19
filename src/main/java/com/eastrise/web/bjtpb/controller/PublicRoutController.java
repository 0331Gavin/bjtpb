package com.eastrise.web.bjtpb.controller;
import com.eastrise.base.TSysUser;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import com.eastrise.web.bjtpb.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 后台管理 路由控制类
 * create by gzq on 2018/11/13 13:34
 */
@Controller
@RequestMapping("/public")
public class PublicRoutController {

    @GetMapping("/deptInfo")
    public String deptInfo(){
        return "/public/deptInfo.jsp";
    }
    @GetMapping("/wjdb/{value}")
    public String wjdb(@PathVariable String value){
        return "/public/wjdb/"+value+".jsp";
    }

}
