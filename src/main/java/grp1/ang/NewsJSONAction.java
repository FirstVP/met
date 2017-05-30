package grp1.ang;

import com.opensymphony.xwork2.Action;
import grp1.ang.mapper.NewsMapper;
import grp1.dao.NewsDao;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import grp1.model.News;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 29.05.2017.
 */



public class NewsJSONAction {
    static HttpServletRequest request ;

    public News getData() {
        return data;
    }

    public void setData(News data) {
        this.data = data;
    }

    private News data = new News();

    private List<News> records = new ArrayList<News>();


    public List<News> getRecords() {
        return records;
    }

    public void setRecords(List<News> records) {
        this.records = records;
    }

    public String create() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            News news = NewsMapper.getObject(filename);
            new NewsDao().insert(news);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String read() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = NewsMapper.getId(filename);
            data = new NewsDao().getNewsById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String delete() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = NewsMapper.getId(filename);
            new NewsDao().delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String update() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            News news = NewsMapper.getObject(filename);
            new NewsDao().update(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String execute() throws SQLException {
        List<News> ps   = new NewsDao().getAllNews();
        records = new ArrayList<News>();
        for(News p : ps)
        {
            records.add(p);
        }
        return Action.SUCCESS;
    }
}
