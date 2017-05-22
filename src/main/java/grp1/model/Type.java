package grp1.model;

/**
 * Created by admin on 22.05.2017.
 */
public class Type {
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type() {
    }

    public Type(Integer typeId, String name, String image) {
        this.typeId = typeId;
        this.name = name;
        this.image = image;

    }

    private Integer typeId;
    private String name;
    private String image;
}
