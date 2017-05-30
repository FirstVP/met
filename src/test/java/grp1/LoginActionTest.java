package grp1;

import com.opensymphony.xwork2.Action;
import junit.framework.TestCase;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * Created by admin on 25.05.2017.
 */
public class LoginActionTest extends TestCase {
    public void testIndex() throws Exception {
        Login action = new Login();
        String result = action.execute();
        assertEquals(Action.ERROR, result);
    }

    public void testLogin() throws Exception {
        Login action = new Login();
        action.setUsername("Test");
        action.setUserpass("Test");
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testLogout() throws Exception {
        Login action = new Login();
        String result = action.logout();
        assertEquals(Action.SUCCESS, result);
    }

    public void testLoginLogout() throws Exception {
        Login action = new Login();
        action.setUsername("Test");
        action.setUserpass("Test");
        String resultLogin = action.execute();
        String resultLogout = action.execute();
        assertEquals(Action.SUCCESS, resultLogin);
        assertEquals(Action.SUCCESS, resultLogout);
    }

    public  void testSetSession() throws Exception {
        Login action = new Login();
        assertTrue(action.getSessionMap() == null);
    }
}
