package grp1;

import com.opensymphony.xwork2.Action;
import com.sun.org.apache.xpath.internal.operations.Bool;
import grp1.model.Weather;
import junit.framework.TestCase;

import java.util.Date;

public class WeatherActionTest extends TestCase {
    public void testIndex() throws Exception {
        WeatherAction action = new WeatherAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testCancel() throws Exception {
        WeatherAction action = new WeatherAction();
        String result = action.cancel();
        assertEquals(Action.SUCCESS, result);
    }

    public void testDelete() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather());
        action.setWeatherId(-3);
        String result = action.delete();
        assertEquals(Action.SUCCESS, result);
    }

    public void testUpdate() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeatherId(1);
        String result = action.getUpdatingWeather();
        assertEquals(Action.SUCCESS, result);
    }

    public void testUpdateNull() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeatherId(-1);
        String result = action.getUpdatingWeather();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveNull() throws Exception {
        WeatherAction action = new WeatherAction();
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveAdd() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (null, 1, 1, 3, 3, 3, new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveUpdate() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, 3, 3, 3, new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIsTypeIdFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, -1, 1, 3, 3, 3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsCityIdFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, -1, 3, 3, 3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsTempFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, -3, 3, 3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsWindFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, 3, -3, 3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsPressureFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, 3, 3, -3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDateFalse() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, 3, 3, 3, null));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDisasterTrue() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(new Weather (5, 1, 1, 3, 3, 3, new Date()));
        action.validate();
        assertTrue(action.errorCount == 0);
    }

    public void testNullValidation() throws Exception {
        WeatherAction action = new WeatherAction();
        action.setWeather(null);
        action.validate();
    }
}
