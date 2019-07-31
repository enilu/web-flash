package cn.enilu.flash.security;

/**
 * @author ：enilu
 * @date ：Created in 2019/7/30 23:05
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}