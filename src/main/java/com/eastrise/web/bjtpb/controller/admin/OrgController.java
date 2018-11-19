package com.eastrise.web.bjtpb.controller.admin;
import com.eastrise.security.SecurityConstants;
import com.eastrise.utils.DateHelper;
import com.eastrise.web.bjtpb.controller.admin.form.OrgAddData;
import com.alibaba.fastjson.JSONObject;
import com.eastrise.base.TSysUser;
import com.eastrise.security.RoleTypes;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastrise.web.bjtpb.controller.admin.form.UserAddData;
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

    @PostMapping(value = "/savea")
    public ApiResponse savea(OrgAddData orgAddData){
        System.out.println(orgAddData);
        if(checkOrgmcIsExist(orgAddData)){
            return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该登录名已存在，不能重复添加");
        }
        String parentid=orgAddData.getParentId();
        try {
            if (parentid.equals("0")){
                orgAddData.setOrgSeq("ORG");
            }else{
                String parentorgseq=orgService.findParentorgseqbyID(parentid).get(0).get("org_seq").toString();
                orgAddData.setOrgSeq(parentorgseq+"."+orgAddData.getId());
                System.out.println(orgAddData);
            }
            if(StringUtils.isNotEmpty(orgAddData.getId())){
                TSysOrg tSysOrg = orgService.findOrgInfo(orgAddData.getId());
                BeanUtils.copyProperties(orgAddData,tSysOrg);

            }else{
                TSysOrg tSysOrg = new TSysOrg();
                BeanUtils.copyProperties(orgAddData,tSysOrg);
                tSysOrg.setParentId(Long.parseLong("parentid"));
                orgService.save(tSysOrg);
                TSysOrg i=orgService.save(tSysOrg);
                String seq=i.getOrgSeq()+i.getId();
                orgService.updateSeq(i.getId(),seq);

            }

        }
        catch(Exception e){}
        return null;
    }
    private boolean checkOrgmcIsExist(OrgAddData orgAddData){
        return orgService.checkOrgIsExist(orgAddData);
    }
}
