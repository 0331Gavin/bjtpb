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
import java.util.Map;

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

    /**
     * 保存部门
     * @param orgAddData
     * @return
     */
    @PostMapping(value = "/savea")
    public ApiResponse savea(OrgAddData orgAddData){
        System.out.println(orgAddData);
        String parentid=orgAddData.getParentId();


        try {
            if (parentid.equals("0")){
                orgAddData.setOrgSeq("1");
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
                long l1 =Long.valueOf(orgAddData.getParentId());

                BeanUtils.copyProperties(orgAddData,tSysOrg);
                tSysOrg.setParentId(l1);
                TSysOrg i=  orgService.save(tSysOrg);
                String seq=i.getOrgSeq()+ i.getId();
                orgService.updateSeq(i.getId(),seq);

            }

        }
        catch(Exception e){

        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }
    private boolean checkOrgmcIsExist(OrgAddData orgAddData){
        return orgService.checkOrgIsExist(orgAddData);
    }

    /**
     * 删除部门
     * @param seq
     * @return
     */
    @PostMapping(value = "/del")
    public ApiResponse del(String id){
        try{
            List<Map<String, Object>> mapList= orgService.findallOrgid(id);
            for (Map<String,Object> map:mapList) {
                for (String s:map.keySet() ) {
                    String deptid=map.get("ID").toString();
                    System.out.println  (deptid);
                    if(orgService.findCountyh(deptid)){
                        return ApiResponse.ofStatus(ApiResponse.Status.DELORG_FAILD);
                    };

                }
            }
            orgService.del(id);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.DEL_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }
}
