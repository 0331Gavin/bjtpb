package com.eastrise.web.bjtpb.service.admin;

import com.eastrise.base.IUserRepository;
import com.eastrise.base.TSysUser;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.bjtpb.controller.admin.form.UserAddData;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by gzq on 2018/11/13 15:26
 */
@Service
public class UserService {

    @Autowired
    private CommonQueryRepository commonQueryRepository;
    @Autowired
    private IUserRepository userRepository;
    /**
     * 用户分页查询
     * @param pageSize
     * @param pageNumber
     * @param loginName
     * @param userName
     * @return
     */
    public ApiPageResponse findPageData(int pageSize, int pageNumber, String loginName, String userName) {
        StringBuilder sql = new StringBuilder("select t.* from t_sys_user t where 1=1 ");
        if(Strings.isNotEmpty(loginName)) sql.append(" and t.login_name like '%"+loginName+"%'");
        if(Strings.isNotEmpty(userName)) sql.append(" and t.user_name like '%"+userName+"%'");
        return commonQueryRepository.findPageBySqlQuery(pageSize,pageNumber,sql.toString());
    }

    public TSysUser findUserInfo(String userId){
        TSysUser tSysUser = userRepository.findByIdAndStatus(userId);
        return tSysUser;
    }

    /**
     * 新增用户
     * @param tSysUser
     * @return
     */
    public TSysUser save(TSysUser tSysUser) throws Exception {
        return userRepository.save(tSysUser);
    }

    /**
     * 删除用户
     * @param id
     */
    public void del(String id) throws Exception{
        userRepository.deleteById(id);
    }

    /**
     * 判断登录名是否已存在
     * @param userAddData
     * @return
     */
    public boolean checkUserIsExist(UserAddData userAddData) {
        StringBuilder sql = new StringBuilder("select t.id from T_SYS_USER t where t.login_name = '"+userAddData.getLoginName()+"'");
        if(StringUtils.isNotEmpty(userAddData.getId())){
            sql.append(" t.id !='"+userAddData.getId()+"'");
        }
        int i = commonQueryRepository.findCountBySqlQuery(sql.toString());
        return i>0?true:false;
    }
}
