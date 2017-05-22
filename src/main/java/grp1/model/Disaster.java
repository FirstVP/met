package grp1.model;

/**
 * Created by admin on 22.05.2017.
 */
public class Disaster {
    private Integer disasterId;

    public Disaster(Integer disasterId, String name, Boolean global) {
        this.disasterId = disasterId;
        this.name = name;
        this.global = global;
    }

    private String name;

    public Integer getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(Integer disasterId) {
        this.disasterId = disasterId;
    }

    public Disaster() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    private Boolean global;
}
