package com.leo.session.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.session.auth.domain.AjaxResponse;
import com.leo.session.auth.enums.CustomExceptionType;
import com.leo.session.auth.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.security.loginType}")
    private String loginType;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        logger.info("登录失败");
        if(loginType.equalsIgnoreCase("JSON")){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.error(new CustomException(
                        CustomExceptionType.USER_INPUT_ERROR,"用户名或密码输入错误!"
                    ))
            ));

        } else {
            //跳转到登录页面
            super.onAuthenticationFailure(request,response,exception);
        }
    }

}
