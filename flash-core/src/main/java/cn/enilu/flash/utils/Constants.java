package cn.enilu.flash.utils;

public interface Constants {

    long SYSTEM_USER_ID=-1;

    /**
     * 用户密码加密key
     */
    String CRYPT_DES_KEY = "sc123456";


    /**
     * 微信获取token url
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 微信获取JsapiTicket url
     */
    String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    /**
     * 微信下载多媒体文件 url
     */
    String MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    /**
     * 微信群发信息 url
     */
    String WX_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";




    /**
     * ===================================================================JSON
     * 返回JSON结果的前后缀,jquery1.4x以上版本的json校验比较严格,不能使用单引号,但是可以将jquery的json解析器还原为eval
     * ("("+xx+")")
     */
    String JSON_PRE = "{'state':'", JSON_END = "'}";

    /**
     * 定义JSON形式的业务处理结果
     */
    String FAIL = JSON_PRE + "fail" + JSON_END, ERROR = JSON_PRE + "error" + JSON_END, SUCCESS = JSON_PRE + "success" + JSON_END,
            TRUE = JSON_PRE + "true" + JSON_END, FALSE = JSON_PRE + "false" + JSON_END, LOCKED = JSON_PRE + "locked" + JSON_END, EXIST = JSON_PRE + "exist" + JSON_END;

    /**
     * 定义JSON形式的业务处理结果----附加上信息
     */
    static String msg(String state, Object info) {
        return state.replace("}", ", 'info': '" + info + "'}");
    }

    /**
     * ===================================================================日志类型
     * 访问日志、操作日志
     */
    int ACCESS = 0, OPERATION = 1;

    String MAIL_ACTION_KEY = "fqufyasiduvqeouirtfu";
}
