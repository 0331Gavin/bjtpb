package com.eastrise.security;

import com.eastrise.base.IUserRepository;
import com.eastrise.base.TSysUser;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.ArrayList;

/**
 * 自定义认证实现
 * create by gzq on 2018/6/8 16:34
 */
public class AuthProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    private IUserRepository userRepo;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        //web，取得远程ip
        String remoteAddress = null;
        Object details = authentication.getDetails();
        if (details instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
            remoteAddress = webDetails.getRemoteAddress();
        }
        log.info("Authenticating {},ip {}", authentication.getName(),remoteAddress);
        TSysUser user = userRepo.findByLoginNameAndStatus(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        if(!user.isEnabled()){
            throw new DisabledException("该账号不可用，请联系管理员");
        }
        if (passwordEncoder.matches(inputPassword,"{bcrypt}"+user.getPwd())) {
            user.setAuthorityList(user.getAuthorityList());
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        }

        throw new BadCredentialsException("用户名或密码不正确");
//        UsernameNotFoundException 用户找不到
//    BadCredentialsException 坏的凭据
//    AccountStatusException用户状态异常它包含如下子类
//    AccountExpiredException 账户过期
//    LockedException 账户锁定
//    DisabledException账户不可用
//    CredentialsExpiredException证书过期

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
