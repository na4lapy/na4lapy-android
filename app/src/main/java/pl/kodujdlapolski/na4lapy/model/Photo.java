package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@DatabaseTable(tableName = "photos")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor //for ormlite
public class Photo extends BaseEntity implements Serializable{

    @DatabaseField
    private String url;

    @DatabaseField
    private String author;

    @DatabaseField(foreign = true)
    private Animal animal;
}
