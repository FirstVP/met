package grp1.ang;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import grp1.dao.NewsDao;
import grp1.model.News;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 30.05.2017.
 */
public class ProductSingleFetch implements Action {
    static HttpServletRequest request ;

    private News newsData = new News();

    public String execute() throws IOException, ParseException, SQLException {

        request  =  ServletActionContext.getRequest();
        String filename = IOUtils.toString(request.getInputStream());
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        Integer id = Integer.parseInt(String.valueOf(json.get("newsId")));
        newsData = new NewsDao().getNewsById(id);
        return SUCCESS;
    }


    public String updateProductData() throws IOException
    {
        request  =  ServletActionContext.getRequest();
        String filename = IOUtils.toString(request.getInputStream());
        System.out.println("fileee1 "+filename);
        News	p = ProductDB.jsonDataForUpdate(filename);

        return "success";
    }


    public News getNewsData() {
        return newsData;
    }

    public void setNewsData(News personData) {
        this.newsData = personData;
    }

}
