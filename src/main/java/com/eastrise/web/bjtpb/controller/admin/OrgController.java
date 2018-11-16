package com.eastrise.web.bjtpb.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.eastrise.base.TSysUser;
import com.eastrise.security.RoleTypes;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by gzq on 2018/11/14 14:12
 */
@RestController
@RequestMapping("/admin/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @PostMapping("/listOrgs")
    public List<TSysOrg> listOrgs(@RequestParam(name="orgCode",required=false,defaultValue = "0") long orgId, boolean isAll){
        List<TSysOrg> orgs = orgService.findChildOrgById(orgId,isAll);
        return orgs;
    }
    @PostMapping(value = "/findPageData")
    public ApiPageResponse findPageData(@RequestParam(value = "orgName",required = false) String orgName , @RequestParam(value = "sjorgname",required = false) String sjorgname,
                                        @RequestParam(value = "page")int pageNumber, @RequestParam(value = "rows") int pageSize){
        return orgService.findPageData(pageSize,pageNumber,orgName,sjorgname);
    }
}
