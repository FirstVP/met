package grp1;

import com.opensymphony.xwork2.ActionSupport;
import grp1.dao.AccidentDao;
import grp1.dao.CityDao;
import grp1.dao.DisasterDao;
import grp1.model.Accident;
import grp1.model.City;
import grp1.model.Disaster;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class AccidentAction extends ActionSupport {
    private AccidentDao accidentDao = new AccidentDao();
    private DisasterDao disasterDao = new DisasterDao();
    private CityDao cityDao = new CityDao();

    public AccidentAction() throws SQLException {
        cityDao = new CityDao();
        cities = cityDao.getAllCities();
        disasterDao = new DisasterDao();
        disasters = disasterDao.getAllDisasters();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> types) {
        this.cities = types;
    }

    private List<City> cities = new ArrayList<City>();

    public List<Disaster> getDisasters() {
        return disasters;
    }

    public void setDisasters(List<Disaster> disasters) {
        this.disasters = disasters;
    }

    private List<Disaster> disasters = new ArrayList<Disaster>();

    public List<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accident> accidents) {
        this.accidents = accidents;
    }

    private List<Accident> accidents = new ArrayList<Accident>();

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    private Integer accidentId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    private Integer cityId;
    public Accident getAccident() {
        return accident;
    }

    public void setAccident(Accident accident) {
        this.accident = accident;
    }

    private Accident accident;

    public String execute() throws Exception {
        cityDao = new CityDao();
        cities = cityDao.getAllCities();
        disasterDao = new DisasterDao();
        disasters = disasterDao.getAllDisasters();
        accidentDao = new AccidentDao();
        accidents = accidentDao.getAllAccidents();
        for (Accident item: accidents)
        {
            for (Disaster disasterItem: disasters)
            {
                if (item.getDisasterId() == disasterItem.getDisasterId()) {
                    item.setDisaster(disasterItem);
                    break;
                }
            }
            for (City cityItem: cities)
            {
                if (item.getCityId() == cityItem.getCityId()) {
                    item.setCity(cityItem);
                    break;
                }
            }
        }
        return SUCCESS;
    }

    public String save() throws SQLException {
        if (accident.getAccidentId() == null) {
            accidentDao.insert(accident);
        } else {
            accidentDao.update(accident);
        }
        return SUCCESS;
    }

    public String cancel() throws SQLException {
        return SUCCESS;
    }


    public String getUpdatingAccident() throws SQLException {
        cityDao = new CityDao();
        cities = cityDao.getAllCities();
        disasterDao = new DisasterDao();
        disasters = disasterDao.getAllDisasters();
        AccidentDao accidentDao = new AccidentDao();
        accident = accidentDao.getAccidentById(accidentId);
        return SUCCESS;
    }

    @SkipValidation
    public String delete() throws SQLException {
        accidentDao.delete(accident.getAccidentId());
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
        if (accident != null)
        {
            if ( isWrong(accident.getCityId()))
                addFieldError ( "accident.cityId", "City is wrong" );
            if ( isWrong(accident.getDisasterId()))
                addFieldError ( "accident.disasterId", "Disaster is wrong" );
            if ( isWrong(accident.getLevel()))
                addFieldError ( "accident.level", "Level is wrong" );
            if ( isEmptyString(accident.getContent()))
                addFieldError ( "accident.pressure", "Content is wrong" );
            if (accident.getDate() == null)
                addFieldError ( "accident.date", "Date is wrong" );
        }

    }
}
