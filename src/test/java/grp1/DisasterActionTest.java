package grp1;

import com.sun.org.apache.xpath.internal.operations.Bool;
import grp1.model.Disaster;
import junit.framework.TestCase;

import com.opensymphony.xwork2.Action;


public class DisasterActionTest extends TestCase {

    public void testIndex() throws Exception {
        DisasterAction action = new DisasterAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testCancel() throws Exception {
        DisasterAction action = new DisasterAction();
        String result = action.cancel();
        assertEquals(Action.SUCCESS, result);
    }

    public void testDelete() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(new Disaster());
        action.setDisasterId(-3);
        String result = action.delete();
        assertEquals(Action.SUCCESS, result);
    }

    public void testView() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisasterId(1);
        String result = action.getViewedDisaster();
        assertEquals(Action.SUCCESS, result);
    }

    public void testViewNull() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(null);
        action.setDisasterId(-5);
        String result = action.getViewedDisaster();
        assertEquals(null, action.getDisaster());
    }

    public void testUpdate() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisasterId(1);
        String result = action.getUpdatingDisaster();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveNull() throws Exception {
        DisasterAction action = new DisasterAction();
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveAdd() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(new Disaster (null, "ExampleDisaster", false));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveUpdate() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(new Disaster (1, "Flood", false));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIsNameFalse() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(new Disaster (1, null, false));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDisasterTrue() throws Exception {
        DisasterAction action = new DisasterAction();
        action.setDisaster(new Disaster (1, "ExampleDisaster", false));
        action.validate();
        assertTrue(action.errorCount == 0);
    }
}
