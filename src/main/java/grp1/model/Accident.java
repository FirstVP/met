package grp1.model;

import java.util.Date;

/**
 * Created by admin on 22.05.2017.
 */
public class Accident {
    private Integer accidentId;

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public Integer getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(Integer disasterId) {
        this.disasterId = disasterId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Accident(Integer accidentId, Integer disasterId, Integer cityId, Integer level, String content, Date date) {
        this.accidentId = accidentId;
        this.disasterId = disasterId;
        this.cityId = cityId;
        this.level = level;
        this.content = content;
        this.date = date;
    }

    public Accident() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;

    }

    private Integer disasterId;
    private Integer cityId;
    private Integer level;
    private String content;
    private Date date;

    private Disaster disaster;
    private City city;

    public Disaster getDisaster() {
        return disaster;
    }

    public void setDisaster(Disaster disaster) {
        this.disaster = disaster;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
