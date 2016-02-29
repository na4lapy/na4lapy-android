package pl.kodujdlapolski.na4lapy.model;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Attitude;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Training;

public class Animal extends BaseEntity {

    private Shelter shelter;
    private Integer homelessnessnessDuration;

    private String name;
    private Integer age;

    private Species species;
    private Gender gender;
    private Size size;
    private String race;
    private ActivityAnimal activity;
    private Training training;

    private Boolean sterilization;
    private Boolean chip;
    private Boolean vaccination;

    private Attitude attitudeTowardsPeople;
    private Attitude attitudeTowardsChildren;
    private Attitude attitudeTowardsDogs;
    private Attitude attitudeTowardsCats;

    private List<Photo> photos;

    private Boolean favourite;

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Integer getHomelessnessnessDuration() {
        return homelessnessnessDuration;
    }

    public void setHomelessnessnessDuration(Integer homelessnessnessDuration) {
        this.homelessnessnessDuration = homelessnessnessDuration;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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
                "shelter=" + shelter +
                ", homelessnessnessDuration=" + homelessnessnessDuration +
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
