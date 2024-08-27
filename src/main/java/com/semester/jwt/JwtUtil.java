package com.semester.jwt;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.semester.service.UserService;
@Component
public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 60 * 1000; // 30分钟过期时间
    private static final String SECRET = "buptnovel"; //密钥字符串

    public static String sign(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkSign(String token, UserService userService) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            String username = claims.get("username").asString();
            // 在这里验证token中的信息是否与数据库中的信息匹配，例如username
            if (username == null || !userService.usernameExists(username)) {
                throw new RuntimeException("Token无效，请重新登录");
            }
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("无效token，请重新获取");
        }
    }

    public static String getTokenClaims(String token, String name) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return claims.get(name).asString();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
