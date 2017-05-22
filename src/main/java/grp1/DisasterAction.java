package grp1;

import com.opensymphony.xwork2.ActionSupport;

import grp1.dao.DisasterDao;
import grp1.model.Disaster;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class DisasterAction extends ActionSupport {
    private DisasterDao disasterDao = new DisasterDao();
    private Disaster disaster;
    private List<Disaster> disasters;

    public List<Disaster> getDisasters() {
        return disasters;
    }

    public Integer getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(Integer disasterId) {
        this.disasterId = disasterId;
    }

    private Integer disasterId;
    public Disaster getDisaster() {
        return disaster;
    }

    public void setDisaster(Disaster disaster) {
        this.disaster = disaster;
    }





    public String execute() throws Exception {
        disasters = disasterDao.getAllDisasters();
        return SUCCESS;
    }

    public String save() throws SQLException {
        if (disaster.getDisasterId() == null) {
            disasterDao.insert(disaster);
        } else {
            disasterDao.update(disaster);
        }
        return SUCCESS;
    }

    public String cancel() throws SQLException {
        return SUCCESS;
    }


    public String getUpdatingDisaster() throws SQLException {
        DisasterDao disasterDao = new DisasterDao();
        disaster = disasterDao.getDisasterById(disasterId);
        return SUCCESS;
    }

    public String getViewedDisaster() throws SQLException {
        DisasterDao disasterDao = new DisasterDao();
        disaster = disasterDao.getDisasterById(disasterId);
        return SUCCESS;
    }

    @SkipValidation
    public String delete() throws SQLException {
        disasterDao.delete(disaster.getDisasterId());
        return SUCCESS;
    }

    private boolean isEmptyString ( String value )
    {
        return value == null || "".equals ( value.trim () );
    }

    private boolean isWrong (Integer i)
    {
        if (i == null)
            return  true;
        if (i <= 0)
            return true;
        return false;
    }

    public void validate ()
    {
        if (disaster != null)
        {
            if ( isEmptyString ( disaster.getName() ))
                addFieldError ( "disaster.name", "Name is empty" );
        }

    }
}
