package cn.enilu.flash.bean.enumeration;

/**
 * redis队列名称
 * Created by zt on 2017/8/25 0015.
 */
public enum RedisQueueName {
    CREDIT_LIMIT("credit_limit"),COLLECTION_TAG("collection_tag");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    RedisQueueName(String value) {
        this.value = value;
    }
}
