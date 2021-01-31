//package com.zxw.authentication.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//
//import java.util.Arrays;
//
//@Slf4j
//class CustomerRealm extends AuthorizingRealm {
//
//    //调用subject.hashRole()或subject.isPermitted()方法，或方法上有注解@RequireRoles时，调用此方法
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        log.info("调用授权方法");
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRoles(Arrays.asList("admin","emp"));
//        return simpleAuthorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String principal = (String) token.getPrincipal();
//        log.info("从数据库获取用户admin");
//        if ("admin".equals(principal)) {
//            return new SimpleAuthenticationInfo("admin", "3fe6a4c4a6d407d4da972a3754d6b678", ByteSource.Util.bytes("yan"), this.getName());
//        }
//        //返回null的时候拋出UnknownAccountException
//        return null;
//    }
//}
//
