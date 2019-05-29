package cn.enilu.flash.bean.vo.offcialSite;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    private String id;
    private String title;
    private String content;
    private Author author;
    private Date createAt;
    private List<Reply> replies;

    public Article() {
    }

    public Article(String id, String title, String content, Author author, Date createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
    }
}
