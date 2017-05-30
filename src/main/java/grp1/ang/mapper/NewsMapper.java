package grp1.ang.mapper;

import grp1.model.News;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 30.05.2017.
 */
public class NewsMapper {
    public static News getObject(String filename)
    {
        News p = new News();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            Object newsIdObject = json.get("newsId");
            Integer newsId = null;
            if (newsIdObject != null)
            {
                newsId = Integer.parseInt(String.valueOf(json.get("newsId")));
            }
            String title = (String) json.get("title");
            String brief = (String) json.get("brief");
            String content = (String) json.get("content");
            Date date = new Date();
            ZonedDateTime zdt = ZonedDateTime.parse((String)json.get("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), 0, 0);
            date = calendar.getTime();
            p.setNewsId(newsId);
            p.setTitle(title);
            p.setBrief(brief);
            p.setContent(content);
            p.setDate(date);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static Integer getId(String filename) throws ParseException {
        Integer id = null;
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(filename);
        JSONObject json = (JSONObject) obj;
        id = Integer.parseInt(String.valueOf(json.get("newsId")));
        return id;
    }
}
