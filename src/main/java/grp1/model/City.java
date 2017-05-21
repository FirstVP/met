package grp1.model;

/**
 * Created by admin on 22.05.2017.
 */
public class City {
    private Integer cityId;

    public City(Integer cityId, Integer code, String name, Integer rise, Integer square, Integer population) {
        this.cityId = cityId;
        this.code = code;
        this.name = name;
        this.rise = rise;
        this.square = square;
        this.population = population;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRise() {
        return rise;
    }

    public void setRise(Integer rise) {
        this.rise = rise;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    private Integer code;
    private String name;
    private Integer rise;
    private Integer square;
    private Integer population;
}
