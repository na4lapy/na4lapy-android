package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "photos")
public class Photo extends BaseEntity implements Serializable{

    @DatabaseField private String url;

    @DatabaseField(foreign = true)
    private Animal animal;

    public Photo() {
        // needed by ormlite
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
