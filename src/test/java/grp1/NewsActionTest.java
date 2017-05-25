package grp1;

import com.opensymphony.xwork2.Action;
import com.sun.org.apache.xpath.internal.operations.Bool;
import grp1.model.News;
import junit.framework.TestCase;

import java.util.Date;

public class NewsActionTest extends TestCase {
    public void testIndex() throws Exception {
        NewsAction action = new NewsAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testCancel() throws Exception {
        NewsAction action = new NewsAction();
        String result = action.cancel();
        assertEquals(Action.SUCCESS, result);
    }

    public void testDelete() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News());
        action.setNewsId(-3);
        String result = action.delete();
        assertEquals(Action.SUCCESS, result);
    }

    public void testView() throws Exception {
        NewsAction action = new NewsAction();
        action.setNewsId(1);
        String result = action.getViewedNewsItem();
        assertEquals(Action.SUCCESS, result);
    }

    public void testViewNull() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(null);
        action.setNewsId(-5);
        String result = action.getViewedNewsItem();
        assertEquals(null, action.getNews());
    }

    public void testUpdate() throws Exception {
        NewsAction action = new NewsAction();
        action.setNewsId(1);
        String result = action.getUpdatingNewsItem();
        assertEquals(Action.SUCCESS, result);
    }

    public void testUpdateNull() throws Exception {
        NewsAction action = new NewsAction();
        action.setNewsId(-1);
        String result = action.getUpdatingNewsItem();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveNull() throws Exception {
        NewsAction action = new NewsAction();
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveAdd() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (null, "ExampleNews", "Brief", "Content", new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveUpdate() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (3, "ExampleNews", "Brief", "Content", new Date()));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIsTitleFalse() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (-1, null, "Brief", "Content", new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsBriefFalse() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (-1, "Title", null, "Content", new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsContentFalse() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (-1, "Title", "Brief", null, new Date()));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDateFalse() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (-1, "Title", "Brief", "Content", null));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsDisasterTrue() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(new News (-1, "Title", "Brief", "Content", new Date()));
        action.validate();
        assertTrue(action.errorCount == 0);
    }

    public void testNullValidation() throws Exception {
        NewsAction action = new NewsAction();
        action.setNews(null);
        action.validate();
    }
}
