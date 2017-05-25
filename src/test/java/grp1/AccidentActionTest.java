package grp1;


import com.sun.org.apache.xpath.internal.operations.Bool;
import grp1.model.Accident;
import junit.framework.TestCase;

import com.opensymphony.xwork2.Action;

import java.util.Date;

public class AccidentActionTest extends TestCase {
    
    public void testIndex() throws Exception {
        AccidentAction action = new AccidentAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testCancel() throws Exception {
        AccidentAction action = new AccidentAction();
        String result = action.cancel();
        assertEquals(Action.SUCCESS, result);
    }

    public void testDelete() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident());
        action.setAccidentId(-3);
        String result = action.delete();
        assertEquals(Action.SUCCESS, result);
    }

    public void testView() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccidentId(1);
        String result = action.getViewedAccident();
        assertEquals(Action.SUCCESS, result);
    }

    public void testViewNull() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(null);
        action.setAccidentId(-5);
        String result = action.getViewedAccident();
        assertEquals(null, action.getAccident());
    }

    public void testUpdate() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccidentId(1);
        String result = action.getUpdatingAccident();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveNull() throws Exception {
        AccidentAction action = new AccidentAction();
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveAdd() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (null, 1, 1, 1, "ExampleAccident", new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveUpdate() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (1, 2, 1, 2, "Example description", new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIsDisasterIdFalse() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, -2, 1, 2, "Example description", new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsCityIdFalse() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, 2, -1, 2, "Example description", new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsLevelFalse() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, 2, 1, -2, "Example description", new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsContentFalse() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, 2, 1, 2, null, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDateFalse() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, 2, 1, 2, "Example description", null));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsCityTrue() throws Exception {
        AccidentAction action = new AccidentAction();
        action.setAccident(new Accident (-1, 2, 1, 2, "Example description", new Date()));
        action.validate();
        assertTrue(action.errorCount == 0);
    }
}
