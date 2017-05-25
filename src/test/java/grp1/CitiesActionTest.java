/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grp1;

import com.sun.org.apache.xpath.internal.operations.Bool;
import grp1.model.City;
import junit.framework.TestCase;

import com.opensymphony.xwork2.Action;

/**
 *
 */
public class CitiesActionTest extends TestCase {

    public void testIndex() throws Exception {
        CityAction action = new CityAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIndexList() throws Exception {
        CityAction action = new CityAction();
        String result = action.execute();
        assertTrue(action.getCities() != null);
    }

    public void testCancel() throws Exception {
        CityAction action = new CityAction();
        String result = action.cancel();
        assertEquals(Action.SUCCESS, result);
    }

    public void testDelete() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City());
        action.setCityId(-3);
        String result = action.delete();
        assertEquals(Action.SUCCESS, result);
    }

    public void testView() throws Exception {
        CityAction action = new CityAction();
        action.setCityId(1);
        String result = action.getViewedCity();
        assertEquals(Action.SUCCESS, result);
    }

    public void testViewNull() throws Exception {
        CityAction action = new CityAction();
        action.setCity(null);
        action.setCityId(-5);
        String result = action.getViewedCity();
        assertEquals(null, action.getCity());
    }

    public void testUpdate() throws Exception {
        CityAction action = new CityAction();
        action.setCityId(1);
        String result = action.getUpdatingCity();
        assertEquals(Action.SUCCESS, result);
    }

    public void testUpdateNull() throws Exception {
        CityAction action = new CityAction();
        action.setCityId(-1);
        String result = action.getUpdatingCity();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveNull() throws Exception {
        CityAction action = new CityAction();
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveAdd() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (null, 1, "Example", 1, 1, 1));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testSaveUpdate() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (12, 1, "Example", 10, 10, 10));
        String result = action.save();
        assertEquals(Action.SUCCESS, result);
    }

    public void testIsNameFalse() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, 1, null, 1, 1, 1));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsCodeFalse() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, -1, "Test", 1, 1, 1));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsRiseFalse() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, 1, "Test", -1, 1, 1));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsSquareFalse() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, 1, "Test", 1, -1, 1));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsPopulationFalse() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, 1, "Test", 1, 1, -100));
        action.validate();
        assertTrue(action.errorCount == 1);
    }

    public void testIsCityTrue() throws Exception {
        CityAction action = new CityAction();
        action.setCity(new City (1, 1, "Test", 1, 1, 100));
        action.validate();
        assertTrue(action.errorCount == 0);
    }

    public void testNullValidation() throws Exception {
        CityAction action = new CityAction();
        action.setCity(null);
        action.validate();
    }


}
