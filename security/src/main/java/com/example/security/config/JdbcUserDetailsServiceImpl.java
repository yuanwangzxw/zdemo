package com.example.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户查询实现
 *
 * @author zxw
 * @date 2020-12-06 10:45
 **/
@Service("userDetailsService")
public class JdbcUserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库取出来的应该是密文
        return User.withUsername("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("vip","ROLE_vip")
                .build();
    }
}
