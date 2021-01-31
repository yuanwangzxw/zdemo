package com.example.security.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * redis
 *
 * @author zxw
 * @date 2021-01-04 12:44
 **/
@Component
public class RedisPersistentTokenRepository implements PersistentTokenRepository {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String tokenKey = "spring:security:rememberMe:token:" + token.getSeries();
        String userNameKey = "spring:security:rememberMe:username:" + token.getUsername();
        Map<String, String> map = new HashMap<>(3);
        map.put("username", token.getUsername());
        map.put("tokenValue", token.getTokenValue());
        map.put("date", new Date().toString());
        redisTemplate.opsForHash().putAll(tokenKey, map);
        redisTemplate.opsForValue().set(userNameKey, token.getSeries());
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String tokenKey = "spring:security:rememberMe:token:" + series;
        Boolean exists = redisTemplate.hasKey(series);
        if (exists == null || !exists) {
            return;
        }
        Map<String, String> map = new HashMap<>(3);
        map.put("tokenValue", tokenValue);
        map.put("date", lastUsed.toString());
        redisTemplate.opsForHash().putAll(tokenKey, map);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String tokenKey = "spring:security:rememberMe:token:" + seriesId;
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(tokenKey);
        if (entries.size() == 0) {
            return null;
        }
        String tokenValue = (String) entries.get("tokenValue");
        String date = (String) entries.get("date");
        String username = (String) entries.get("username");
        return new PersistentRememberMeToken(username, seriesId, tokenValue, new Date(date));
    }

    @Override
    public void removeUserTokens(String username) {
        String userNameKey = "spring:security:rememberMe:username:" + username;
        String seriesId = (String) redisTemplate.opsForValue().get(userNameKey);
        redisTemplate.delete(userNameKey);
        if (StringUtils.isEmpty(seriesId)) {
            return;
        }
        redisTemplate.delete(seriesId);
    }
}
