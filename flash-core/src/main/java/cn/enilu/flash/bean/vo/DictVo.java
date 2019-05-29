package cn.enilu.flash.bean.vo;

/**
 * DictVo
 *
 * @author enilu
 * @version 2018/8/14 0014
 */
public class DictVo {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DictVo() {
    }

    public DictVo(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
