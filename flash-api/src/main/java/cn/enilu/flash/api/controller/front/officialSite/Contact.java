package cn.enilu.flash.api.controller.front.officialSite;

import lombok.Data;

import java.util.Date;

@Data
public class Contact {
    public String username;
    private String email;
    private String mobile;
    private String description;
    private Date createAt;
}
