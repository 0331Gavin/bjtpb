package com.eastrise.web.bjtpb.controller.admin;

import com.eastrise.base.TSysUser;
import com.eastrise.security.SecurityConstants;
import com.eastrise.security.SecurityContexUtils;
import com.eastrise.utils.DateHelper;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.ApiResponse;
import com.eastrise.web.bjtpb.controller.admin.form.OrgAddData;
import com.eastrise.web.bjtpb.controller.admin.form.UserAddData;
import com.eastrise.web.bjtpb.service.admin.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.net.SocketFlow;
import org.apache.commons.lang.StringUtils;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.DataTruncation;

/**
 * create by gzq on 2018/11/13 15:05
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/findPageData")
    public ApiPageResponse findPageData(@RequestParam(value = "loginName",required = false) String loginName ,@RequestParam(value = "userName",required = false) String userName,
                                        @RequestParam(value = "page")int pageNumber,@RequestParam(value = "rows") int pageSize){
        return userService.findPageData(pageSize,pageNumber,loginName,userName);
    }

    @PostMapping(value = "/save")
    public ApiResponse save(UserAddData userAddData){

        if(checkUserIsExist(userAddData)){
            return ApiResponse.ofMessage(ApiResponse.Status.SAVE_FAILD.getCode(),"该登录名已存在，不能重复添加");
        }
        try{
            if(StringUtils.isNotEmpty(userAddData.getId())){
                TSysUser tSysUser = userService.findUserInfo(userAddData.getId());
                BeanUtils.copyProperties(userAddData,tSysUser);
                tSysUser.setLastUpdateTime(DateHelper.getDateTime());
                userService.save(tSysUser);
            }else{
                TSysUser tSysUser = new TSysUser();
                BeanUtils.copyProperties(userAddData,tSysUser);
                tSysUser.setPwd(passwordEncoder.encode(SecurityConstants.DEFAULT_PASSWORD));
                tSysUser.setCreateTime(DateHelper.getDateTime());
                userService.save(tSysUser);
            }
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.SAVE_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.SAVE_SUCCESS);
    }

    /**
     * 更新密码
     * @param oldmm
     * @param xmm
     * @return
     */
    @PostMapping(value = "/updatemima")
    public ApiResponse mima(String oldmm,String xmm){
        try{
            String loginname=SecurityContexUtils.getLoginName();
            TSysUser user=userService.findUserbyloginname(loginname );
            String PWD= user.getPwd();
            String xmm1=passwordEncoder.encode(xmm);
            if (passwordEncoder.matches(oldmm,PWD)){
                userService.updatemimma(xmm1,loginname);
            }else {
                return ApiResponse.ofStatus(ApiResponse.Status.UPDATA_CHONGFU);
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
            userService.del(id);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse.ofStatus(ApiResponse.Status.DEL_FAILD);
        }
        return ApiResponse.ofStatus(ApiResponse.Status.DEL_SUCCESS);
    }

    private boolean checkUserIsExist(UserAddData userAddData){
        return userService.checkUserIsExist(userAddData);
    }
}
