package com.gahui.ghmall.server.service.token.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gahui.ghmall.server.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.gahui.ghmall.server.constant.TokenConstant.ACCOUNT_ID;

/**
 * @description: token业务逻辑类
 * @author: Gahui
 * @since: 2021/3/12
 **/
@Slf4j
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.timeout}")
    private Long timeout;

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Override
    public String encode(Long accountId) {
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + timeout);
        // 秘钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        String token;
        try {
            token = JWT.create()
                    .withHeader(header)
                    .withClaim(ACCOUNT_ID, accountId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("生成token异常，accountId ===> {}", accountId);
            e.printStackTrace();
            return null;
        }
        return token;
    }

    @Override
    public Long decode(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Long accountId = jwt.getClaims().get(ACCOUNT_ID).asLong();
            log.info("解析token成功，accountId ===> {}", accountId);
            return accountId;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解析token错误，token ===> {}", token);
            return null;
        }
    }
}
