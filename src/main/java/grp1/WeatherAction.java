package grp1;

import com.opensymphony.xwork2.ActionSupport;
import grp1.dao.TypeDao;
import grp1.dao.WeatherDao;
import grp1.model.Type;
import grp1.model.Weather;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class WeatherAction extends ActionSupport {
    private WeatherDao weatherDao = new WeatherDao();
    private TypeDao typeDao = new TypeDao();

    public WeatherAction() throws SQLException {
        typeDao = new TypeDao();
        types = typeDao.getAllTypes();
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    private List<Type> types = new ArrayList<Type>();

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    private Integer weatherId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    private Integer cityId;
    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private Weather weather;


    public String save() throws SQLException {
        if (weather.getWeatherId() == null) {
            weatherDao.insert(weather);
        } else {
            weatherDao.update(weather);
        }
        return SUCCESS;
    }

    public String cancel() throws SQLException {
        return SUCCESS;
    }


    public String getUpdatingWeather() throws SQLException {
        typeDao = new TypeDao();
        types = typeDao.getAllTypes();
        WeatherDao weatherDao = new WeatherDao();
        weather = weatherDao.getWeatherById(weatherId);
        return SUCCESS;
    }

    @SkipValidation
    public String delete() throws SQLException {
        weatherDao.delete(weather.getWeatherId());
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
        if (weather != null)
        {
            if ( isWrong(weather.getTemp()))
                addFieldError ( "weather.temp", "Temperature is wrong" );
            if ( isWrong(weather.getTypeId()))
                addFieldError ( "weather.typeId", "Condition is wrong" );
            if ( isWrong(weather.getWind()))
                addFieldError ( "weather.wind", "Wind is wrong" );
            if ( isWrong(weather.getPressure()))
                addFieldError ( "weather.pressure", "Pressure is wrong" );
        }

    }
}