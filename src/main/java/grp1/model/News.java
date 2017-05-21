package grp1.model;

import java.util.Date;

/**
 * Created by admin on 19.02.2017.
 */
public class News {


    private Integer newsId;
    private String title;
    private String brief;
    private String content;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public News() {
    }

    public News(Integer newsId, String title, String brief, String content, Date date) {
        this.newsId = newsId;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
