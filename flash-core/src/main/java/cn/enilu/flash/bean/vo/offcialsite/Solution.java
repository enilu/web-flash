package cn.enilu.flash.bean.vo.offcialsite;

import lombok.Data;

@Data
public class Solution {

    private Long id;
    private String name;
    private String img;
    public Solution(){

    }

    public Solution(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
}
