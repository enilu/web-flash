package cn.enilu.flash.bean.enumeration.cms;

public enum ChannelEnum {

    NEWS(1L,"news"),
    PRODUCT(2L,"product"),
    SOLUTION(3L,"solution"),
    CASE(4L,"case");


    private String value;
    private Long id;
    ChannelEnum(Long id,String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public Long getId(){
        return id;
    }
}
