package grp1;

import com.opensymphony.xwork2.Action;
import junit.framework.TestCase;

/**
 * Created by admin on 25.05.2017.
 */
public class RegisterActionTest extends TestCase {
    public void testIndex() throws Exception {
        RegisterAction action = new RegisterAction();
        String result = action.execute();
        assertEquals(Action.ERROR, result);
    }

    public void testIndexRegisterFailedNameEmpty() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName(null);
        action.setEmail("Tst");
        action.setPassword("Tst");
        String result = action.execute();
        assertEquals(Action.ERROR, result);
    }

    public void testIndexRegisterFailedEmailEmpty() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail(null);
        action.setPassword("Tst");
        String result = action.execute();
        assertEquals(Action.ERROR, result);
    }

    public void testIndexRegisterFailedPasswordEmpty() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail("Tst");
        action.setPassword(null);
        String result = action.execute();
        assertEquals(Action.ERROR, result);
    }

    public void testIndexSuccess() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail("Tst");
        action.setPassword("Tst");
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testNameIsWrong() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName(null);
        action.setEmail("Tst");
        action.setPassword("Tst");
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testEmailIsWrong() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail(null);
        action.setPassword("Tst");
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testPasswordIsWrong() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail("Tst1");
        action.setPassword(null);
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testUserTrue() throws Exception {
        RegisterAction action = new RegisterAction();
        action.setName("Tst");
        action.setEmail("Tst");
        action.setPassword("Tst");
        action.validate();
        assertTrue(action.errorCount == 0);
    }

    public void testInput() throws Exception {
        RegisterAction action = new RegisterAction();
        String result = action.input();
        assertEquals(Action.INPUT, result);
    }
}
