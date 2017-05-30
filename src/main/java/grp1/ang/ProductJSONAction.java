package grp1.ang;

import com.opensymphony.xwork2.Action;
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

/**
 * Created by admin on 29.05.2017.
 */



public class ProductJSONAction {
    static HttpServletRequest request ;
    private Map<String,String> map =  new HashMap<String,String>();

    private News pd = new News();

    private List<News> records = new ArrayList<News>();

    public ProductJSONAction() throws SQLException {
       // records.clear();
       // records.add(new News(1, "w", "d", "d", "d"));
        List<News> ps   = new NewsDao().getAllNews();

        for(News p : ps)
        {
            pd.setNewsId(p.getNewsId());
            pd.setTitle(p.getTitle());
            pd.setBrief(p.getBrief());
            pd.setContent(p.getContent());
            pd.setDate(p.getDate());

            records.add(p);
        }

    }


    public List<News> getRecords() {
        return records;
    }

    public void setRecords(List<News> records) {
        this.records = records;
    }

    public String getFileName()
    {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            System.out.println("fileee "+filename);
            ProductDB.fileToString(filename);




        } catch (IOException e) {

            e.printStackTrace();
        }
        return "success";
    }



    public String execute() {
        return Action.SUCCESS;
    }
}
