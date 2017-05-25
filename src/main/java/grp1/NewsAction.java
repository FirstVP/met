package grp1;

import com.opensymphony.xwork2.ActionSupport;
import grp1.dao.NewsDao;
import grp1.model.News;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 09.05.2017.
 */
public class NewsAction extends ActionSupport {
    private NewsDao newsDao = new NewsDao();
    private List newsList;
    public static Integer errorCount = 0;
    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    private Integer newsId;
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    private News news;

    public String execute()
    {
        return SUCCESS;
    }

    public String save() throws SQLException {
        if (news != null)
        {
            if (news.getNewsId() == null) {
                newsDao.insert(news);
            } else {
                newsDao.update(news);
            }
        }
        return SUCCESS;
    }

    public String cancel() throws SQLException {
        return SUCCESS;
    }


    public String getUpdatingNewsItem() throws SQLException {
        NewsDao newsDao = new NewsDao();
        news = newsDao.getNewsById(newsId);
        return SUCCESS;
    }

    public String getViewedNewsItem() throws SQLException {
        NewsDao newsDao = new NewsDao();
        news = newsDao.getNewsById(newsId);
        return SUCCESS;
    }

    @SkipValidation
    public String delete() throws SQLException {
        newsDao.delete(news.getNewsId());
        return SUCCESS;
    }

    private boolean isEmptyString ( String value )
    {
        return value == null || "".equals ( value.trim () );
    }

    public void validate ()
    {
        errorCount = 0;
        if (news != null)
        {
            if ( isEmptyString ( news.getTitle() ))
            {
                addFieldError ( "news.title", "Title is empty" );
                errorCount++;
            }
            if ( isEmptyString ( news.getBrief() ))
            {
                addFieldError ( "news.brief", "Brief is empty" );
                errorCount++;
            }

            if ( isEmptyString ( news.getContent() ))
            {
                addFieldError ( "news.content", "Content is empty" );
                errorCount++;
            }

            if (news.getDate() == null)
            {
                addFieldError ( "weather.date", "Date is wrong" );
                errorCount++;
            }

        }

    }
}
