package com.eastrise.web.bjtpb.service.admin;

import com.eastrise.base.IUserRepository;
import com.eastrise.base.TSysUser;
import com.eastrise.base.exception.NRAPException;
import com.eastrise.security.SecurityContexUtils;
import com.eastrise.utils.ChangeNameForm;
import com.eastrise.web.base.ApiPageResponse;
import com.eastrise.web.base.CommonQueryRepository;
import com.eastrise.web.base.SystemErrorCodeType;
import com.eastrise.web.bjtpb.controller.admin.form.UserAddData;
import com.eastrise.web.bjtpb.entity.LocalUserDetails;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/13 15:26
 */
@Service
public class UserService {

    @Autowired
    private CommonQueryRepository commonQueryRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
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


    public LocalUserDetails findUserDetails(){
        String loginName = SecurityContexUtils.getLoginName();
        String sql = "select t.id,t.login_name,t.user_name,t.dept_Id,o.org_name from t_sys_user t ,t_sys_org o where t.dept_id = o.id and t.login_name = '"+loginName+"'";
        List<Map<String, Object>> result = ChangeNameForm.changeSqlResult(jdbcTemplate.queryForList(sql));
        if (result.size() <= 0) {
            throw new NRAPException(SystemErrorCodeType.E_NOT_EXIST, "用户");
        }
        LocalUserDetails localUserDetails = new LocalUserDetails();
        localUserDetails.setId(result.get(0).get("id")+"");
        localUserDetails.setLoginName(loginName);
        localUserDetails.setDeptName(result.get(0).get("orgName")+"");
        localUserDetails.setDeptId(Long.parseLong(result.get(0).get("deptId")+""));
        return localUserDetails;
    }


}
