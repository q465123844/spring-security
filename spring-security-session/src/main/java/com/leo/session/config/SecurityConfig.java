package com.leo.session.config;

import com.leo.session.auth.handler.MyAuthenticationFailureHandler;
import com.leo.session.auth.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //关闭跨站攻击
                .csrf().disable().
                formLogin()
                .loginPage("/login.html")
                .usernameParameter("uname")
                .passwordParameter("pwd")
                .loginProcessingUrl("/login")
                /**
                 * defaultSuccessUrl 和 successHandler只能任选其一(failure也是一样)
                 */
                //.defaultSuccessUrl("/index")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                    //所有的请求必须在登录之后才能访问
                    .authorizeRequests()
                    //开放页面和接口,不需要登录认证就可以访问
                    .antMatchers("/login.html","/login").permitAll()
                    //需要对外暴露的资源路径
                    .antMatchers("/businessOne","/businessTwo")
                    /*
                    user角色和admin角色(这两个角色可以访问businessOne和businessTwo 页面)
                    ROLE_ 是spring security对于角色的前缀
                     */
                    .hasAnyAuthority("ROLE_user","ROLE_admin")
                    /*.antMatchers("/syslog","/sysuser")
                    //admin角色才能访问syslog和sysuser
                    .hasAnyRole("admin")*/
                    .antMatchers("/syslog").hasAnyAuthority("sys:log")
                    .antMatchers("/sysuser").hasAnyAuthority("sys:user")
                    .anyRequest().
                    authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //账号user,密码123456有着user的角色
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("user")
                .and()
                //账号admin,密码123456有着admin的角色
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
//                .roles("admin")
                //admin用户只能访问sys:log,sys:user id下的页面
                .authorities("sys:log","sys:user")
                .and()
                //配置BCrypt加密
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 对用户名密码进行加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //将项目中的资源路径开放出来
        web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

}
