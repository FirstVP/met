package grp1.ang.mapper;

import grp1.model.Weather;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 30.05.2017.
 */
public class WeatherMapper {
    public static Weather getObject(String filename)
    {
        Weather p = new Weather();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            Object weatherIdObject = json.get("weatherId");
            Integer weatherId = null;
            if (weatherIdObject != null)
            {
                weatherId = Integer.parseInt(String.valueOf(json.get("weatherId")));
            }
            Integer typeId = Integer.parseInt(String.valueOf(json.get("typeId")));
            Integer cityId = Integer.parseInt(String.valueOf(json.get("cityId")));
            Integer temp = Integer.parseInt(String.valueOf(json.get("temp")));
            Integer wind = Integer.parseInt(String.valueOf(json.get("wind")));
            Integer pressure = Integer.parseInt(String.valueOf(json.get("pressure")));
            Date date = new Date();
            ZonedDateTime zdt = ZonedDateTime.parse((String)json.get("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), 0, 0);
            date = calendar.getTime();
            p = new Weather(weatherId, typeId, cityId, temp, wind, pressure, date);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static Integer getId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("weatherId")));
        return id;
    }

    public static Integer getCityId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("cityId")));
        return id;
    }
}
