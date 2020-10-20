package com.leo.databaselogin.auth.impl;

import com.leo.databaselogin.auth.domain.MyUserDetails;
import com.leo.databaselogin.auth.mappper.MyUsersDetailsServiceMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private MyUsersDetailsServiceMapper myUsersDetailsServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //加载基础用户信息
        MyUserDetails myUserDetails = myUsersDetailsServiceMapper.findByUserName(username);
        //加载用户角色列表
        List<String> roleCodes = myUsersDetailsServiceMapper.findRoleByUserName(username);
        //通过用户角色列表加载用户的资源权限列表
        List<String> authorities = myUsersDetailsServiceMapper.findAuthorityByRoleCodes(roleCodes);
        //角色是一个特使的权限ROLE_,遍历为集合中的元素添加ROLE_
        roleCodes = roleCodes.stream().map(e-> "ROLE_"+e).collect(Collectors.toList());
        authorities.addAll(roleCodes);
        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        //authorities中的每个元素添加逗号
                        String.join(",",authorities)
                        )
        );
        return myUserDetails;
    }
}
