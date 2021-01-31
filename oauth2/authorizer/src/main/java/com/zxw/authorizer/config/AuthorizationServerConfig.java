package com.zxw.authorizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * 授权配置
 *
 * @author zxw
 * @date 2020-12-07 21:14
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //授权码模式，获取授权码,get请求
        //localhost:53020/oauth/authorize?client_id=client_id&response_type=code&scope=all&redirect_uri=https://www.baidu.com
        //localhost:53020/oauth/token           获取token接口，post请求，form表单:client_id,client_secret,grant_type,code,redirect_uri
        //localhost:53020/oauth/check_token    校验token接口，post请求，form表单:token
        //请求接口,header带上Authorization,token以Bearer空格+token的形式
        clients.inMemory()
                .withClient("client_id")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .resourceIds("resource_server_id")
                //授权类型
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                //限制客户端权限，在换取token的时候会带上scope参数，在scopes定义内才能正常换取token
                .scopes("all")
                //false表示跳转到授权页面
                .autoApprove(false)
                //验证回调地址
                .redirectUris("https://www.baidu.com");
//        clients.withClientDetails();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                //oauth/token_key允许匿名用户访问获取token接口
                .tokenKeyAccess("permitAll()")
                //oauth/check_token允许匿名用户访问校验token接口
                .checkTokenAccess("permitAll()")
                //允许客户端访问授权接口
                .allowFormAuthenticationForClients();
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationServerTokenServices tokenService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //jdbcTokenStore,jwtTokenStore,inMemoryTokenStore
        endpoints
                //当选择密码授权模式，必须要配置认证管理器
                .authenticationManager(authenticationManager)
                //授权码存取方式
                .authorizationCodeServices(authorizationCodeServices)
                //token的存储与配置
                .tokenServices(tokenService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
