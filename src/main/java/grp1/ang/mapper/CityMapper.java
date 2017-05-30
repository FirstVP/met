package grp1.ang.mapper;

import grp1.model.City;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 30.05.2017.
 */
public class CityMapper {
    public static City getObject(String filename)
    {
        City p = new City();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            Object cityIdObject = json.get("cityId");
            Integer cityId = null;
            if (cityIdObject != null)
            {
                cityId = Integer.parseInt(String.valueOf(json.get("cityId")));
            }
            Integer code = Integer.parseInt(String.valueOf(json.get("code")));
            String name = (String) json.get("name");
            Integer rise = Integer.parseInt(String.valueOf(json.get("rise")));
            Integer square = Integer.parseInt(String.valueOf(json.get("square")));
            Integer population = Integer.parseInt(String.valueOf(json.get("population")));
            p = new City(cityId, code, name, rise, square, population);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static Integer getId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("cityId")));
        return id;
    }
}
