package cn.enilu.flash.bean.vo.offcialsite;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleVo {
    private String id;
    private String title;
    private String content;
    private Author author;
    private Date createAt;
    private List<Reply> replies;

    public ArticleVo() {
    }

    public ArticleVo(String id, String title, String content, Author author, Date createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
    }
}
