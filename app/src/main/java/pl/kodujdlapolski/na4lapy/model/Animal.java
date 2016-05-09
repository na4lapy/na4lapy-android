package pl.kodujdlapolski.na4lapy.model;

import android.support.annotation.Nullable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.LocalDate;

import java.io.Serializable;

import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Sterilization;
import pl.kodujdlapolski.na4lapy.model.type.Training;
import pl.kodujdlapolski.na4lapy.model.type.Vaccination;
import pl.kodujdlapolski.na4lapy.repository.database.LocalDatePersister;

@DatabaseTable(tableName = "animals")
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

    public Animal() {
        // needed by ormlite
    }

    public @Nullable Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public @Nullable String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Nullable String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public @Nullable String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @Nullable LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @Nullable LocalDate getAdmittanceDate() {
        return admittanceDate;
    }

    public void setAdmittanceDate(LocalDate admittanceDate) {
        this.admittanceDate = admittanceDate;
    }

    public @Nullable  String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    public @Nullable Sterilization getSterilization() {
        return sterilization;
    }

    public void setSterilization(Sterilization sterilization) {
        this.sterilization = sterilization;
    }

    public @Nullable Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public @Nullable Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public @Nullable Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public @Nullable ActivityAnimal getActivity() {
        return activity;
    }

    public void setActivity(ActivityAnimal activity) {
        this.activity = activity;
    }

    public @Nullable Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    public @Nullable Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public @Nullable ForeignCollection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ForeignCollection<Photo> photos) {
        this.photos = photos;
    }

    public @Nullable Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "shelter=" + shelter +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", description='" + description + '\'' +
                ", birthDate=" + birthDate +
                ", admittanceDate=" + admittanceDate +
                ", chipId='" + chipId + '\'' +
                ", sterilization=" + sterilization +
                ", species=" + species +
                ", gender=" + gender +
                ", size=" + size +
                ", activity=" + activity +
                ", vaccination=" + vaccination +
                ", training=" + training +
                ", photos=" + photos +
                ", favourite=" + favourite +
                '}';
    }
}
