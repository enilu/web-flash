package cn.enilu.flash.bean.enumeration;

/**
 * Created by deanyule on 17/8/16.
 */
public enum ProjectEnum {

    SCORE_CARD("ScoreCard"),
    DOLPHIN_PROD("dolphin"),
    SNOW_PRODUCT("PRJ_SNOW_PRODUCT");

    private String value;

    ProjectEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
