package com.eastrise.web.bjtpb.controller.admin;

import com.eastrise.security.SecurityConstants;
import com.eastrise.utils.DateHelper;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.OrgAddData;
import com.eastrise.web.bjtpb.controller.admin.form.SjzdAddData;
import com.eastrise.web.bjtpb.entity.TSysOrg;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.service.admin.OrgService;
import com.eastrise.web.bjtpb.service.admin.SjzdService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/14 14:12
 */
@RestController
@RequestMapping("/admin/sjzd")
public class SjzdController {

    @Autowired
    private SjzdService sjzdService;

    /**
     * 数据字典首页列表
     * @param sjbm
     * @param sjmc
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/findPageData")
    public ApiPageResponse findPageData(@RequestParam(value = "sjbm",required = false) String sjbm , @RequestParam(value = "sjmc",required = false) String sjmc,@RequestParam(value = "sjlx",required = false) String sjlx,
                                        @RequestParam(value = "page")int pageNumber, @RequestParam(value = "rows") int pageSize){

        return sjzdService.findPageData(pageSize,pageNumber,sjbm,sjmc,sjlx);
    }

    /**
     * 数据字典保存
     * @param sjzdAddData
     * @return
     */
    @PostMapping(value = "/save")
    public ApiResponse save(SjzdAddData sjzdAddData){


        try{
            if(StringUtils.isNotEmpty(sjzdAddData.getId())){
                TSysSjzd tSysSjzd = new TSysSjzd();
                BeanUtils.copyProperties(sjzdAddData,tSysSjzd);
                sjzdService.save(tSysSjzd);
            }else{
                TSysSjzd tSysSjzd = new TSysSjzd();
                BeanUtils.copyProperties(sjzdAddData,tSysSjzd);
                sjzdService.save(tSysSjzd);
            }
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }

    /**
     * 根据id删除数据字典中某条数据
     * @param id
     * @return
     */
    @PostMapping(value = "/del")
    public ApiResponse del(String id){
        try{
            sjzdService.del(id);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.DEL_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }

}
