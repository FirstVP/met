package grp1.model;

import java.util.Date;

/**
 * Created by admin on 22.05.2017.
 */
public class Weather {
    private Integer weatherId;

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getWind() {
        return wind;
    }

    public void setWind(Integer wind) {
        this.wind = wind;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Weather(Integer weatherId, Integer typeId, Integer cityId, Integer temp, Integer wind, Integer pressure, Date date) {
        this.weatherId = weatherId;
        this.typeId = typeId;
        this.cityId = cityId;
        this.temp = temp;
        this.wind = wind;
        this.pressure = pressure;
        this.date = date;
    }

    private Integer typeId;

    public Weather() {
    }

    private Integer cityId;
    private Integer temp;
    private Integer wind;
    private Integer pressure;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Type type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
}
