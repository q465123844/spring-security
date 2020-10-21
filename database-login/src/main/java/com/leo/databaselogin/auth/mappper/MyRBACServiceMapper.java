package com.leo.databaselogin.auth.mappper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyRBACServiceMapper {
    /**
     * 通过用户名查询urls
     * @param userId
     * @return
     */
    List<String> findUrlsByUserName(String userId);
}
