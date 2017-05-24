package grp1.service;

import grp1.model.Accident;
import grp1.model.City;
import grp1.model.Disaster;

import java.util.List;

/**
 * Created by admin on 24.05.2017.
 */
public class AccidentService {
    public static void setAccidentsChildEntities(List<Accident> accidents, List<Disaster> disasters, List<City> cities)
    {
        for (Accident item: accidents)
        {
            for (Disaster disasterItem: disasters)
            {
                if (item.getDisasterId() == disasterItem.getDisasterId()) {
                    item.setDisaster(disasterItem);
                    break;
                }
            }
            for (City cityItem: cities)
            {
                if (item.getCityId() == cityItem.getCityId()) {
                    item.setCity(cityItem);
                    break;
                }
            }
        }
    }
}
