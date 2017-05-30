package grp1.ang;

import com.opensymphony.xwork2.Action;
import grp1.ang.mapper.CityMapper;
import grp1.dao.CityDao;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import grp1.model.City;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 29.05.2017.
 */



public class CityJSONAction {
    static HttpServletRequest request ;

    public City getData() {
        return data;
    }

    public void setData(City data) {
        this.data = data;
    }

    private City data = new City();

    private List<City> records = new ArrayList<City>();


    public List<City> getRecords() {
        return records;
    }

    public void setRecords(List<City> records) {
        this.records = records;
    }

    public String create() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            City city = CityMapper.getObject(filename);
            new CityDao().insert(city);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String read() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = CityMapper.getId(filename);
            data = new CityDao().getCityById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String delete() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = CityMapper.getId(filename);
            new CityDao().delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String update() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            City city = CityMapper.getObject(filename);
            new CityDao().update(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String execute() throws SQLException {
        List<City> ps   = new CityDao().getAllCities();
        records = new ArrayList<City>();
        for(City p : ps)
        {
            records.add(p);
        }
        return Action.SUCCESS;
    }
}
