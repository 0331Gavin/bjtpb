package com.eastrise.security;

import com.eastrise.base.IUserRepository;
import com.eastrise.base.TSysUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stzhang on 2016/9/21.
 */
//@Component("userDetailsService")
@Service(value = "userDetailService")
public class AuthUserDetailService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(AuthUserDetailService.class);

    @Autowired
    private IUserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authenticating {}", username);
         TSysUser user = userRepo.findByLoginNameAndStatus(username);
//        if(user == null){
//            throw new UsernameNotFoundException("not found user: username="+username);
//        }
        if (user == null) {
            throw new BadCredentialsException("用户名或密码不正确");
        }
        if(!user.isEnabled()){
            throw new DisabledException("您的账号已被停用");
        }
        return new User(user.getLoginName(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorityList());
    }
}
