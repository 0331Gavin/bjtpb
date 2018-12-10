package com.eastrise.config;

import com.eastrise.security.AuthProvider;
import com.eastrise.security.InMemoryAuthenticationProvider;
import com.eastrise.security.LoginAuthFailHandler;
import com.eastrise.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * create by gzq on 2018/10/30 14:12
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
    @Bean
    public LoginAuthFailHandler loginAuthFailHandler() {
        return new LoginAuthFailHandler();
    }
    @Autowired
    private InMemoryAuthenticationProvider inMemoryAuthenticationProvider;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/","index","index.html").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/uedit1.4.3.3/**","/uploadImg/**").permitAll()
                 .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .failureHandler(loginAuthFailHandler()).permitAll()
                .successHandler(loginSuccessHandler())
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).deleteCookies(new String[0]).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(604800) //记住我功能，cookies有限期是一周
                    .rememberMeParameter("remember-me") //登陆时是否激活记住我功能的参数名字，在登陆页面有展示
                    .rememberMeCookieName("workspace"); //cookies的名字，登陆后可以通过浏览器查看cookies名字;
        //session管理
        //session失效后跳转
        http.sessionManagement().invalidSessionUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    //    auth.authenticationProvider(inMemoryAuthenticationProvider);

    }

//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(authProvider(),inMemoryAuthenticationProvider));
//        authenticationManager.setEraseCredentialsAfterAuthentication(false);
//        return authenticationManager;
//    }


    @Bean
    public AuthProvider authProvider(){
        return new AuthProvider();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置静态资源不要拦截
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
