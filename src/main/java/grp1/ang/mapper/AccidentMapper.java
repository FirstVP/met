package grp1.ang.mapper;

import grp1.model.Accident;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 30.05.2017.
 */
public class AccidentMapper {
    public static Accident getObject(String filename)
    {
        Accident p = new Accident();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            Object accidentIdObject = json.get("accidentId");
            Integer accidentId = null;
            if (accidentIdObject != null)
            {
                accidentId = Integer.parseInt(String.valueOf(json.get("accidentId")));
            }
            Integer disasterId = Integer.parseInt(String.valueOf(json.get("disasterId")));
            Integer cityId = Integer.parseInt(String.valueOf(json.get("cityId")));
            Integer level = Integer.parseInt(String.valueOf(json.get("level")));
            String content = (String) json.get("content");
            Date date = new Date();
            ZonedDateTime zdt = ZonedDateTime.parse((String)json.get("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), 0, 0);
            date = calendar.getTime();
            p = new Accident(accidentId, disasterId, cityId, level, content, date);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static Integer getId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("accidentId")));
        return id;
    }
}
