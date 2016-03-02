package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Attitude;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Training;

@DatabaseTable(tableName = "animals")
public class Animal extends BaseEntity {

    public final static String COLUMN_NAME_FAVOURITE = "favourite";

    @DatabaseField(foreign = true)
    private Shelter shelter;

    @DatabaseField private Integer homelessnessDuration;

    @DatabaseField private String name;
    @DatabaseField private Integer age;

    @DatabaseField private Species species;
    @DatabaseField private Gender gender;
    @DatabaseField private Size size;
    @DatabaseField private String race;
    @DatabaseField private ActivityAnimal activity;
    @DatabaseField private Training training;

    @DatabaseField private Boolean sterilization;
    @DatabaseField private Boolean chip;
    @DatabaseField private Boolean vaccination;

    @DatabaseField private Attitude attitudeTowardsPeople;
    @DatabaseField private Attitude attitudeTowardsChildren;
    @DatabaseField private Attitude attitudeTowardsDogs;
    @DatabaseField private Attitude attitudeTowardsCats;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Photo> photos;

    @DatabaseField(columnName = Animal.COLUMN_NAME_FAVOURITE)
    private Boolean favourite;

    public Animal() {
        // needed by ormlite
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Integer getHomelessnessDuration() {
        return homelessnessDuration;
    }

    public void setHomelessnessDuration(Integer homelessnessDuration) {
        this.homelessnessDuration = homelessnessDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public ActivityAnimal getActivity() {
        return activity;
    }

    public void setActivity(ActivityAnimal activity) {
        this.activity = activity;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Boolean getSterilization() {
        return sterilization;
    }

    public void setSterilization(Boolean sterilization) {
        this.sterilization = sterilization;
    }

    public Boolean getChip() {
        return chip;
    }

    public void setChip(Boolean chip) {
        this.chip = chip;
    }

    public Boolean getVaccination() {
        return vaccination;
    }

    public void setVaccination(Boolean vaccination) {
        this.vaccination = vaccination;
    }

    public Attitude getAttitudeTowardsPeople() {
        return attitudeTowardsPeople;
    }

    public void setAttitudeTowardsPeople(Attitude attitudeTowardsPeople) {
        this.attitudeTowardsPeople = attitudeTowardsPeople;
    }

    public Attitude getAttitudeTowardsChildren() {
        return attitudeTowardsChildren;
    }

    public void setAttitudeTowardsChildren(Attitude attitudeTowardsChildren) {
        this.attitudeTowardsChildren = attitudeTowardsChildren;
    }

    public Attitude getAttitudeTowardsDogs() {
        return attitudeTowardsDogs;
    }

    public void setAttitudeTowardsDogs(Attitude attitudeTowardsDogs) {
        this.attitudeTowardsDogs = attitudeTowardsDogs;
    }

    public Attitude getAttitudeTowardsCats() {
        return attitudeTowardsCats;
    }

    public void setAttitudeTowardsCats(Attitude attitudeTowardsCats) {
        this.attitudeTowardsCats = attitudeTowardsCats;
    }

    public ForeignCollection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ForeignCollection<Photo> photos) {
        this.photos = photos;
    }

    public Boolean isFavourite() {
        return favourite != null && favourite;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + getId() +
                ", shelter=" + shelter +
                ", homelessnessDuration=" + homelessnessDuration +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species=" + species +
                ", gender=" + gender +
                ", size=" + size +
                ", race='" + race + '\'' +
                ", activity=" + activity +
                ", training=" + training +
                ", sterilization=" + sterilization +
                ", chip=" + chip +
                ", vaccination=" + vaccination +
                ", attitudeTowardsPeople=" + attitudeTowardsPeople +
                ", attitudeTowardsChildren=" + attitudeTowardsChildren +
                ", attitudeTowardsDogs=" + attitudeTowardsDogs +
                ", attitudeTowardsCats=" + attitudeTowardsCats +
                ", photos=" + photos +
                ", favourite=" + favourite +
                '}';
    }
}
