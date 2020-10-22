package com.leo.databaselogin.auth.impl;

import com.leo.databaselogin.auth.domain.Person;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * 方法级别安全控制类
 */
@Service
public class MethodELService {
    /**
     * 在方法执行之前进行权限判断,如果有权限表达式描述的权限,就执行该方法
     * 若没有权限表达式描述的权限,就会抛出异常,不会执行该方法
     * 当登录的角色为admin才能访问该方法，否则抛异常
     * @return
     */
    @PreAuthorize("hasRole('admin')")  //表格中权限表达式在双引号内都是适用的
    public List<String> findAll(){
        System.out.println("进来了");
        return null;
    }

    /**
     * 登录后,若方法的返回值相同则会执行该方法
     * @return
     */
    @PostAuthorize("returnObject == authentication.name")
    public Person findOne(){
        String authName = getContext().getAuthentication().getName();
        System.out.println("authName:"+authName);
        return new Person("admin");
    }

    /**
     * 对参数进行过滤,filterTarget 为对哪一个参数进行过滤,value为过滤规则
     * @param ids
     * @param username
     */
    @PreFilter(filterTarget = "ids",value = "filterObject%2 ==0")
    public void delete(List<Integer> ids,List<String> username){
         //传过来的ids list原本有1和2,调用该方法取余为0才会保留,因此list中为1的元素被过滤掉了
        System.out.println(ids.size());
    }

    /**
     * 对参数进行过滤
     * @return
     */
    @PostFilter("filterObject.name == authentication.name")
    public List<Person> getAlls(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("zhangsan"));
        persons.add(new Person("admin"));
        return persons;
    }
}
