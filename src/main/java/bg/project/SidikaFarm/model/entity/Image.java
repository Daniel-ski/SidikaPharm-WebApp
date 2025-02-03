package bg.project.SidikaFarm.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String title;
    @Column(name = "located_on",nullable = false)
    private String locatedOn;

    public Image() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocatedOn() {
        return locatedOn;
    }

    public void setLocatedOn(String locatedOn) {
        this.locatedOn = locatedOn;
    }
}
