//package com.zxw.authentication.config;
//
//import com.alibaba.excel.util.StringUtils;
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.crypto.hash.Md5Hash;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import java.util.HashMap;
//
///**
// * @author zxw
// */
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//
//
//        HashMap<String, String> map = new HashMap<>();
//        /*
//          anon      anonymousFilter 可以匿名访问
//          authc     FormAuthenticationFilter 需要认证访问，默认以form表单形式从请求中获取username，password，rememberMe
//          roles     RolesAuthorizationFilter    包含角色才能访问
//          logout    LogoutFilter
//          port      PortFilter      指定端口才能访问
//         */
////        map.put("/auth/dologin","anon");
////        map.put("/auth/logout","logout");
////        //swagger
////        map.put("/swagger-ui.html", "anon");
////        map.put("/webjars/**", "anon");
////        map.put("/swagger-resources/**", "anon");
////        map.put("/v2/api-docs", "anon");
////
////        map.put("/auth/t*", "roles[admin]");
////        map.put("/auth/test*/**", "roles[admin]");
////        map.put("/**","authc");
//
////        map.put("/**", "anon");
////        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//
//        //后台登录接口
////        shiroFilterFactoryBean.setLoginUrl("/auth/login");
////        //认证成功跳转页面
////        shiroFilterFactoryBean.setSuccessUrl("auth/index");
////        //认证失败跳转页面
////        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/login");
//
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        defaultWebSecurityManager.setRealm(realm);
//        defaultWebSecurityManager.setCacheManager(cacheManager());
//        return defaultWebSecurityManager;
//    }
//
//    @Bean
//    public CredentialsMatcher credentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        hashedCredentialsMatcher.setHashIterations(1024);
//        return hashedCredentialsMatcher;
//    }
//
//    @Bean
//    @Primary
//    public Realm customerRealm(CredentialsMatcher credentialsMatcher) {
//        CustomerRealm customerRealm = new CustomerRealm();
//        customerRealm.setCredentialsMatcher(credentialsMatcher);
//        return customerRealm;
//    }
//
//    /**
//     * ServletContainerSessionManager默认会话在内存，这里用DefaultWebSessionManager存到redis
//     * @return
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager(){
//            @Override
//            protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
//                String token = ((HttpServletRequest) request).getHeader("userLoginToken");
//                if (StringUtils.isEmpty(token)) {
//                    return super.getSessionId(request, response);
//                }
//                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,"header");
//                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
//                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,true);
//                return token;
//            }
//        };
//
//        sessionManager.setSessionDAO(redisSessionDAO());
//        return sessionManager;
//    }
//
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }
//
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    public RedisManager redisManager() {
//        return new RedisManager();
//    }
//
//
//
//
//    public static void main(String[] args) {
//        Md5Hash yan = new Md5Hash("123456", "yan", 1024);
//        System.out.println("yan.toHex() = " + yan.toHex());
//    }
//}
