package com.eastrise.web.bjtpb.controller.admin;

import com.eastrise.base.TSysUser;
import com.eastrise.security.RoleTypes;
import com.eastrise.web.bjtpb.service.admin.UserService;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by gzq on 2018/11/14 11:07
 */
@RestController
@RequestMapping("/admin/role")
public class UserRoleController {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {


    }
    @PostMapping("/listRoles")
    public List<UserRoles> getRoles(String userId){
        List<UserRoles> resData= Lists.newArrayList();
        TSysUser tSysUser=null;
        if(Strings.isNotEmpty(userId)) tSysUser = userService.findUserInfo(userId);
        RoleTypes[] roleTypes =RoleTypes.values();
        for(RoleTypes roleType:roleTypes){
            UserRoles r = new UserRoles();
            r.setRoleId(roleType.name());
            r.setRoleName(roleType.getLabel());
            if(tSysUser!=null&&tSysUser.getRoleIds().contains(roleType.name()))
                r.setSelected(true);
            resData.add(r);
        }
        return resData;
    }

    public class UserRoles {
        private String roleId;
        private String roleName;
        private boolean selected=false;
        public UserRoles() {
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
