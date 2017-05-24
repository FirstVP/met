package grp1.docs;

import grp1.docs.model.StatisticsDocumentModel;
import grp1.docs.model.WeatherDocumentModel;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 24.05.2017.
 */
public class DocumentHelper {
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean isDateInCurrentWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static DocumentModel getDocumentModel(String type, ServletContext context) {
        DocumentModel model = null;
        if (type == null)
            type = "";
        String[] splited = type.split("\\s+");
        if (splited.length > 1)
        {
            if (splited[0].equals("CityWeather"))
            {
                model = new WeatherDocumentModel(DocumentHelper.tryParse(splited[1]), context);
            }
            else
            if (splited[0].equals("CityStats"))
            {
                model = new StatisticsDocumentModel(DocumentHelper.tryParse(splited[1]));
            }
            type = splited[0];
        }
        else
        {
            model = DocumentTypes.getTypes().get(type);
        }
        return model;
    }
}
