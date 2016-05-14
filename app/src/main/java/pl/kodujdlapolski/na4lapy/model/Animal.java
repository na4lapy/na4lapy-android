package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.LocalDate;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Sterilization;
import pl.kodujdlapolski.na4lapy.model.type.Training;
import pl.kodujdlapolski.na4lapy.model.type.Vaccination;
import pl.kodujdlapolski.na4lapy.repository.database.LocalDatePersister;

@DatabaseTable(tableName = "animals")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor //for ormlite
public class Animal extends BaseEntity implements Serializable {

    public final static String COLUMN_NAME_FAVOURITE = "favourite";

    @DatabaseField(foreign = true)
    private Shelter shelter;

    @DatabaseField private String name;
    @DatabaseField private String race;
    @DatabaseField private String description;
    @DatabaseField private String chipId;

    @DatabaseField(persisterClass = LocalDatePersister.class)
    private LocalDate birthDate;

    @DatabaseField(persisterClass = LocalDatePersister.class)
    private LocalDate admittanceDate;

    @DatabaseField private Sterilization sterilization;
    @DatabaseField private Species species;
    @DatabaseField private Gender gender;
    @DatabaseField private Size size;
    @DatabaseField private ActivityAnimal activity;
    @DatabaseField private Vaccination vaccination;
    @DatabaseField private Training training;

    @ForeignCollectionField(eager = false)
    private transient ForeignCollection<Photo> photos;

    @DatabaseField(columnName = Animal.COLUMN_NAME_FAVOURITE)
    private Boolean favourite;
}
