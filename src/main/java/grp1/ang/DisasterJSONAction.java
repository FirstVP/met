package grp1.ang;

import com.opensymphony.xwork2.Action;
import grp1.ang.mapper.DisasterMapper;
import grp1.dao.DisasterDao;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import grp1.model.Disaster;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 29.05.2017.
 */



public class DisasterJSONAction {
    static HttpServletRequest request ;

    public Disaster getData() {
        return data;
    }

    public void setData(Disaster data) {
        this.data = data;
    }

    private Disaster data = new Disaster();

    private List<Disaster> records = new ArrayList<Disaster>();


    public List<Disaster> getRecords() {
        return records;
    }

    public void setRecords(List<Disaster> records) {
        this.records = records;
    }

    public String create() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Disaster disaster = DisasterMapper.getObject(filename);
            new DisasterDao().insert(disaster);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String read() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = DisasterMapper.getId(filename);
            data = new DisasterDao().getDisasterById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String delete() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = DisasterMapper.getId(filename);
            new DisasterDao().delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String update() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Disaster disaster = DisasterMapper.getObject(filename);
            new DisasterDao().update(disaster);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String execute() throws SQLException {
        List<Disaster> ps   = new DisasterDao().getAllDisasters();
        records = new ArrayList<Disaster>();
        for(Disaster p : ps)
        {
            records.add(p);
        }
        return Action.SUCCESS;
    }
}
