package com.leo.databaselogin.auth.impl;

import com.leo.databaselogin.auth.mappper.MyRBACServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Component("rbacService")
public class MyRBACServiceImpl {
    @Resource
    private MyRBACServiceMapper myRBACServiceMapper;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 判断某用户是否具有该request资源访问权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            //Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            List<String> urls = myRBACServiceMapper.findUrlsByUserName(username);
            return urls.stream().anyMatch(
                    url -> antPathMatcher.match(url,request.getRequestURI())
            );
        }
        return false;
    }

}
