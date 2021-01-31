package com.example.security.smscode;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 短信登录过滤器
 *
 * @author zxw
 * @date 2020-12-25 14:39
 **/
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * form表单中手机号码的字段name
     */
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "phone";

    public SmsCodeAuthenticationFilter(){
        super(new AntPathRequestMatcher("/sms/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //获取传参mobile
        String phone = Optional.ofNullable(request.getParameter(SPRING_SECURITY_FORM_MOBILE_KEY))
                .map(String::trim)
                .orElse("");
        //创建token,设置用户
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone);
        //设置ip与session
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
