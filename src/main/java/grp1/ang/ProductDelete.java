package grp1.ang;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import grp1.ang.ProductDB;
import grp1.model.News;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class ProductDelete implements Action{
    static HttpServletRequest request ;
    private News newsData = new News();

    public String execute() throws IOException {

        request  =  ServletActionContext.getRequest();
        String filename = IOUtils.toString(request.getInputStream());
        System.out.println("fileee1 "+filename);
        News	p = ProductDB.jsonDataForDelete(filename);


        return SUCCESS;
    }





    public News getPersonData() {
        return newsData;
    }

    public void setPersonData(News newsData) {
        this.newsData = newsData;
    }


}