package com.eastrise.web.bjtpb.controller.admin;

import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.SjzdAddData;
import com.eastrise.web.bjtpb.controller.admin.form.YqljAddData;
import com.eastrise.web.bjtpb.entity.TSysSjzd;
import com.eastrise.web.bjtpb.entity.TSysYqlj;
import com.eastrise.web.bjtpb.service.admin.SjzdService;
import com.eastrise.web.bjtpb.service.admin.YqljService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**友情链接
 * create by  lh on 2018/11/14 14:12
 */
@RestController
@RequestMapping("/admin/yqlj")
public class YqljController {
    @Autowired
    private YqljService yqljService;
    /**
     *友情链接分页
     * @param ljmc
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/findPageData")
    public ApiPageResponse findPageData(@RequestParam(value = "ljmc",required = false) String ljmc ,
                                        @RequestParam(value = "page")int pageNumber, @RequestParam(value = "rows") int pageSize){

        return yqljService.findPageData(pageSize,pageNumber,ljmc);
    }

    /**
     * 友情链接修改or保存
     * @param yqljAddData
     * @return
     */
    @PostMapping(value = "/save")
    public ApiResponse save(YqljAddData yqljAddData){
        try{
            if(StringUtils.isNotEmpty(yqljAddData.getId())){
                TSysYqlj tSysYqlj = new TSysYqlj();
                BeanUtils.copyProperties(yqljAddData,tSysYqlj);
                yqljService.save(tSysYqlj);
            }else{
                TSysYqlj tSysYqlj = new TSysYqlj();
                BeanUtils.copyProperties(yqljAddData,tSysYqlj);
                yqljService.save(tSysYqlj);
            }
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }
    @PostMapping(value = "/del")
    public ApiResponse del(String id){
        try{
            yqljService.del(id);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.DEL_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }

}
