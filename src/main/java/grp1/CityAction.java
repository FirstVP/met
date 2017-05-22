package grp1;

import com.opensymphony.xwork2.ActionSupport;
import grp1.dao.CityDao;
import grp1.dao.TypeDao;
import grp1.dao.WeatherDao;
import grp1.model.City;
import grp1.model.Type;
import grp1.model.Weather;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class CityAction extends ActionSupport {
    private CityDao cityDao = new CityDao();
    private WeatherDao weatherDao = new WeatherDao();
    private TypeDao typeDao = new TypeDao();
    public List<City> getCities() {
        return cities;
    }

    private List<City> cities;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    private Integer cityId;
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    private City city;

    public List<Weather> getWeathers() {
        return weathers;
    }

    private List<Weather> weathers;
    private List<Type> types;

    public String execute() throws Exception {
        cities = cityDao.getAllCities();
        List <Weather> list = weatherDao.getAllWeathers();
        types = typeDao.getAllTypes();
        weathers = new ArrayList<Weather>();
        for (Weather weatherItem: list)
        {
            if (weatherItem.getCityId() == cities.get(1).getCityId())
            {
                weathers.add(weatherItem);

                for (Type typeItem: types)
                {
                    if (weatherItem.getTypeId() == typeItem.getTypeId())
                    {
                        weatherItem.setType(typeItem);
                        break;
                    }
                }

            }

        }
        return SUCCESS;
    }

    public String save() throws SQLException {
        if (city.getCityId() == null) {
            cityDao.insert(city);
        } else {
            cityDao.update(city);
        }
        return SUCCESS;
    }

    public String cancel() throws SQLException {
        return SUCCESS;
    }


    public String getUpdatingCity() throws SQLException {
        CityDao newsDao = new CityDao();
        city = newsDao.getCityById(cityId);
        return SUCCESS;
    }

    public String getViewedCity() throws SQLException {
        CityDao newsDao = new CityDao();
        city = newsDao.getCityById(cityId);
        return SUCCESS;
    }

    @SkipValidation
    public String delete() throws SQLException {
        cityDao.delete(city.getCityId());
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
        if (city != null)
        {
            if ( isEmptyString ( city.getName() ))
                addFieldError ( "city.name", "Name is empty" );
            if ( isWrong(city.getCode()))
                addFieldError ( "city.code", "Code is wrong" );
            if ( isWrong(city.getRise()))
                addFieldError ( "city.rise", "Rise is wrong" );
            if ( isWrong(city.getSquare()))
                addFieldError ( "city.square", "Square is wrong" );
            if ( isWrong(city.getPopulation()))
                addFieldError ( "city.population", "Population is wrong" );
        }

    }
}
