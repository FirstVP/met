package grp1.ang;

import com.opensymphony.xwork2.Action;
import grp1.ang.mapper.WeatherMapper;
import grp1.dao.TypeDao;
import grp1.dao.WeatherDao;
import grp1.model.Type;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import grp1.model.Weather;
import org.json.simple.parser.ParseException;

/**
 * Created by admin on 29.05.2017.
 */



public class WeatherJSONAction {
    static HttpServletRequest request ;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    private Weather data = new Weather();

    private List<Weather> records = new ArrayList<Weather>();


    public List<Weather> getRecords() {
        return records;
    }

    public void setRecords(List<Weather> records) {
        this.records = records;
    }

    public String create() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Weather weather = WeatherMapper.getObject(filename);
            new WeatherDao().insert(weather);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String read() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = WeatherMapper.getId(filename);
            data = new WeatherDao().getWeatherById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String delete() throws SQLException, ParseException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Integer id = WeatherMapper.getId(filename);
            new WeatherDao().delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public String update() throws SQLException {
        try {
            request  =  ServletActionContext.getRequest();
            String filename = IOUtils.toString(request.getInputStream());
            Weather weather = WeatherMapper.getObject(filename);
            new WeatherDao().update(weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    List<Type> types = new ArrayList<Type>();

    public String execute() throws SQLException, IOException, ParseException {
        types = new TypeDao().getAllTypes();
        request  =  ServletActionContext.getRequest();
        String filename = IOUtils.toString(request.getInputStream());
        Integer id = WeatherMapper.getCityId(filename);
        List<Weather> ps   = new WeatherDao().getAllWeathers();
        records = new ArrayList<Weather>();
        for (Weather weatherItem: ps)
        {
            if (weatherItem.getCityId() == id)
            {
                records.add(weatherItem);

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
        return Action.SUCCESS;
    }
}
