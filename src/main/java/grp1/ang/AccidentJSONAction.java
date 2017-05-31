package grp1.ang;

import com.opensymphony.xwork2.Action;
import grp1.ang.mapper.AccidentMapper;
import grp1.dao.AccidentDao;
import grp1.dao.CityDao;
import grp1.dao.DisasterDao;
import grp1.model.City;
import grp1.model.Disaster;
import grp1.service.AccidentService;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import grp1.model.Accident;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 29.05.2017.
 */



public class AccidentJSONAction {
    static HttpServletRequest request ;

    public Accident getData() {
        return data;
    }

    public void setData(Accident data) {
        this.data = data;
    }

    private Accident data = new Accident();

    private List<Accident> records = new ArrayList<Accident>();


    public List<Accident> getRecords() {
        return records;
    }

    public void setRecords(List<Accident> records) {
        this.records = records;
    }

    public String create() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Accident accident = AccidentMapper.getObject(filename);
            new AccidentDao().insert(accident);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String read() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = AccidentMapper.getId(filename);
            data = new AccidentDao().getAccidentById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String delete() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = AccidentMapper.getId(filename);
            new AccidentDao().delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String update() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Accident accident = AccidentMapper.getObject(filename);
            new AccidentDao().update(accident);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String execute() throws SQLException {
        List<Accident> ps   = new AccidentDao().getAllAccidents();
        records = new ArrayList<Accident>();
        for(Accident p : ps)
        {
            records.add(p);
        }
        List<City> cities = new CityDao().getAllCities();
        List<Disaster> disasters = new DisasterDao().getAllDisasters();
        AccidentService.setAccidentsChildEntities(records, disasters, cities);
        return Action.SUCCESS;
    }
}
