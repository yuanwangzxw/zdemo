package com.example.security.config;

import com.example.security.smscode.SmsCodeAuthenticationSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * security配置类
 *
 * @author zxw
 * @date 2020-11-19 21:59
 **/
//开启方法权限注解
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .authorities("vip");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/js/**", "/img/**", "/webjars/**", "**/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自带表单登录认证
//        http.formLogin().and().httpBasic();
        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("msg", "登录成功")));
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("msg", "登录失败")));
                    }
                })
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("msg", "未登录")));
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("msg", "已登录，无权访问")));
                    }
                });
        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                //相关实现
                //CookieClearingLogoutHandler 清除cookie
                //CsrfLogoutHandler 移除csrfToken
                //TokenBasedRememberMeService 基于token的RememberMe功能清理
                //securityContextLogoutHandler  清理securityContext相关
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("msg", "退出登录")));
                    }
                });
        http.authorizeRequests()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/delay").hasAnyAuthority("vip")
                .antMatchers("/hello").authenticated()
                .antMatchers("/resources/**", "/mapper/**", "/login","/logout","/sms/login").permitAll()
                .antMatchers("/permit").permitAll()
                .anyRequest().authenticated();
        http.cors().and().csrf().disable();
        http.rememberMe()
                .tokenRepository(persistentTokenRepository)
//                .tokenRepository(new InMemoryTokenRepositoryImpl())
                .tokenValiditySeconds(7200)
                .userDetailsService(userDetailsService);
        http.apply(smsCodeAuthenticationSecurityConfig);
        //在usernameFilter之前添加一个filter
//        http.addFilterBefore(new GenericFilterBean() {
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//            }
//        }, UsernamePasswordAuthenticationFilter.class);
    }
}
