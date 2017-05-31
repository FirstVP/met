package grp1.ang.mapper;

import grp1.model.Disaster;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 30.05.2017.
 */
public class DisasterMapper {
    public static Disaster getObject(String filename)
    {
        Disaster p = new Disaster();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            Object disasterIdObject = json.get("disasterId");
            Integer disasterId = null;
            if (disasterIdObject != null)
            {
                disasterId = Integer.parseInt(String.valueOf(json.get("disasterId")));
            }
            String name = (String) json.get("name");
            Boolean global = Boolean.valueOf((Boolean) json.get("global"));

            p = new Disaster(disasterId, name, global);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static Integer getId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("disasterId")));
        return id;
    }
}
