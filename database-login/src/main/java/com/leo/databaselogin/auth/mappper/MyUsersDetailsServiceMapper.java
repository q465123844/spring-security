package com.leo.databaselogin.auth.mappper;

import com.leo.databaselogin.auth.domain.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MyUsersDetailsServiceMapper {
    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    MyUserDetails findByUserName(String userId);

    /**
     * 根据userId查询用户角色列表
     * @param userId
     * @return
     */
    List<String> findRoleByUserName(String userId);

    /**
     * 根据用户角色查询用户权限
     * @param roleCodes
     * @return
     */
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);


}
