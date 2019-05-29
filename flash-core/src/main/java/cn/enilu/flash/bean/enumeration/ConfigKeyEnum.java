package cn.enilu.flash.bean.enumeration;

public enum ConfigKeyEnum {

    SYSTEM_FILE_UPLOAD_PATH("system.file.upload.path"),
    SYSTEM_APP_NAME("system.app.name");

    private String value;

    ConfigKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
