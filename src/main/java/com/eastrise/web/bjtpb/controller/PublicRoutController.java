package com.eastrise.web.bjtpb.controller;
import com.eastrise.base.TSysUser;
import com.eastrise.web.bjtpb.entity.TArticleManage;
import com.eastrise.web.bjtpb.service.admin.ArticleManageService;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import com.eastrise.web.bjtpb.service.admin.UserService;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理 路由控制类
 * create by gzq on 2018/11/13 13:34
 */
@Controller
@RequestMapping("/public")
public class PublicRoutController {

    @Autowired
    private ArticleManageService articleManageService;

    @GetMapping("/bmjj")
    public String bmjj(){
        return "/public/bmjj/bmjj.jsp";
    }



    @GetMapping("/wjdb/{value}")
    public String wjdbmx(@PathVariable String code,HttpServletRequest request){
        TArticleManage articleManage = articleManageService.findArticleByCode(code,"1");
        String parentId = articleManage.getCategoryseq().split("\\.")[0];
        List<TArticleManage> articleManageList = articleManageService.findArticleByParentId(parentId,"1");

        List<Map<String,Object>> result = Lists.newArrayList();
        Map m = new HashMap();
        for(TArticleManage a:articleManageList){
            m = new HashMap();
            m.put("name",a.getCategoryname());
            m.put("value",a.getCategorycode());
            if(articleManage.getCategoryseq().split("\\.")[1].equals(a.getId()+"")){
                m.put("check",true);
            }else{
                m.put("check",false);
            }
            List<TArticleManage> articleManages = articleManageService.findArticleByParentId(a.getId()+"","1");
            m.put("sons",articleManages);
            result.add(m);
        }
        request.setAttribute("id",articleManage.getId());
        request.setAttribute("result",result);
        return "/public/wjdb/wjdb.jsp";
    }
    @GetMapping("/fgzd/{code}")
    public String fgzd(@PathVariable String code,HttpServletRequest request){

        return "/public/fgzd/fgzd.jsp";
    }

}
