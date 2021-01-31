package com.example.security.smscode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 短信登录token
 *
 * @author zxw
 * @date 2020-12-25 14:35
 **/
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;

    public SmsCodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
