package com.leo.databaselogin.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.databaselogin.auth.domain.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.security.loginType}")
    private String loginType;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        logger.info("登录成功");
        if(loginType.equalsIgnoreCase("JSON")){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.success()));
            //response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.success("/index")));

        } else {
            //跳转到登录之前请求的页面
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }

}
