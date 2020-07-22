package cn.enilu.flash.security;

import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.utils.HttpUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * @author ：enilu
 * @date ：Created in 2019/7/30 22:56
 */
public class JwtUtil {

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param password 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String password) {
        JWTVerifier verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Long getUserId() {
        return getUserId(HttpUtil.getToken());
    }

    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param user       用户
     * @param expireTime 毫秒数
     * @return 加密的token
     */
    public static String sign(User user, long expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireTime);
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            // 附带username信息
            return JWT.create()
                    .withClaim("username", user.getAccount())
                    .withClaim("userId", user.getId())
                    .withClaim("uuid", UUID.randomUUID().toString())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
