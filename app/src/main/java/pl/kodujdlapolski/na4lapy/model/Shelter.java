package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@DatabaseTable(tableName = "shelters")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor //for ormlite
public class Shelter extends BaseEntity implements Serializable {

    @DatabaseField
    private String name;

    @DatabaseField
    private String street;

    @DatabaseField
    private String buildingNumber;

    @DatabaseField
    private String city;

    @DatabaseField
    private String postalCode;

    @DatabaseField
    private String email;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField
    private String website;

    @DatabaseField
    private String accountNumber;

    @DatabaseField
    private String adoptionRules;
}
