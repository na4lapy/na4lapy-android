package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseEntity implements Serializable{

    public final static String COLUMN_NAME_ID = "id";

    @DatabaseField(id = true)
    private Long id;
}
