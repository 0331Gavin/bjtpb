package com.eastrise.web.bjtpb.controller.admin;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.base.TSysUser;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import com.eastrise.web.bjtpb.service.admin.SjzdService;
import com.eastrise.web.bjtpb.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 后台管理 路由控制类
 * create by gzq on 2018/11/13 13:34
 */
@Controller
@RequestMapping("/admin")
public class AdminRoutController {
    @Autowired
    private UserService userService;
    @Autowired
    private SjzdService sjzdService;
    @Autowired
    private OrgService orgService;
    @GetMapping("/home")
    public String home(){
        return "/admin/adminHome.jsp";
    }
    @GetMapping("/articleAdd")
    public String articleAdd(){
        return "/admin/article/articleAdd.jsp";
    }
    @GetMapping("/articleManage")
    public String articleManage(){
        return "/admin/article/articleManage.jsp";
    }
    @GetMapping("/articleTypeAdd")
    public String articleTypeAdd(){
        return "/admin/articleType/articleTypeAdd.jsp";
    }
    @GetMapping("/articleTypeManage")
    public String articleTypeManage(){
        return "/admin/articleType/articleTypeManage.jsp";
    }
    @GetMapping("/system/userManage")
    public String userManage(){
        return "/admin/system/userManage.jsp";
    }
    @GetMapping("/system/deptManage")
    public String deptManage(){
        return "/admin/system/deptManage.jsp";
    }
    @GetMapping("/system/userEdit")
    public String userEdit(String id, HttpServletRequest request){
        request.setAttribute("id",id);
        TSysUser tSysUser = userService.findUserInfo(id);
        request.setAttribute("user",tSysUser);
        return "/admin/system/userEdit.jsp";
    }
    @GetMapping("/system/deptEdit")
    public String engineeringProjectEdit(String id, HttpServletRequest request) throws Exception {
        if(id!=null){
        Map<String, Object> list =orgService.findById(id).get(0);
        request.setAttribute("dept",list);}
        return "/admin/system/deptEdit.jsp";
    }
    @GetMapping("/system/sjzdEdit")
    public String sjzdEdit(String id, HttpServletRequest request) throws Exception {
        if(id!=null){
            Map<String, Object> list =sjzdService.findById(id).get(0);
            request.setAttribute("sjzd",list);}
        return "/admin/system/sjzdEdit.jsp";
    }
    @GetMapping("/system/xgMa")
    public String xgMa(){
        return "/admin/system/xgMa.jsp";
    }
    @GetMapping("/system/sjZd")
    public String sjZd(HttpServletRequest request) throws Exception {
        List<Map<String, Object>> list =sjzdService.findsjlx();
        request.setAttribute("list",list);
        return "/admin/system/sjZd.jsp";
    }

}
