package com.zxw.authentication.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截控制器
 *
 * @author zxw
 * @date 2020-11-07 13:13
 **/
@RequestMapping
@RestController
public class FilterController {

    @GetMapping("/")
    public String success() {
        String one = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter("one");
        return "success";
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name","admin");
        String token = generateToken(claims);
        Claims result = parseToken(token);
        System.out.println("result = " + result);
    }

    private static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
    }

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis()+5000))
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }
}
