package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseEntity {

    @DatabaseField(id = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
