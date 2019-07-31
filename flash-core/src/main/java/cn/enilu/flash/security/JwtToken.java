package cn.enilu.flash.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ：enilu
 * @date ：Created in 2019/7/30 22:58
 */
public class JwtToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}