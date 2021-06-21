package com.sample;

import java.io.Serializable;
import java.util.Objects;

abstract class SportsClub implements Serializable{
    private String clubName;
    private String location;
    private String clubOwner;
    private int numberStaff;
    private int numberMembers;

    public SportsClub(String clubName,String location,String clubOwner,int numberStaff, int numberMembers) {
        this.clubName = clubName;
        this.location = location;
        this.clubOwner = clubOwner;
        this.numberStaff = numberStaff;
        this.numberMembers = numberMembers;

    }

    protected SportsClub() {
    }


    //Getters & Setters

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getClubOwner() {
        return clubOwner;
    }
    public void setClubOwner(String clubOwner) {
        this.clubOwner = clubOwner;
    }

    public int getNumberStaff() {
        return numberStaff;
    }
    public void setNumberStaff(int numberStaff) {
        this.numberStaff = numberStaff;
    }

    public int getNumberMembers() {
        return numberMembers;
    }
    public void setNumberMembers(int numberMembers) {
        this.numberMembers = numberMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub that = (SportsClub) o;
        return getNumberStaff() == that.getNumberStaff() &&
                getNumberMembers() == that.getNumberMembers() &&
                Objects.equals(getClubName(), that.getClubName()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getClubOwner(), that.getClubOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClubName(), getLocation(), getClubOwner(), getNumberStaff(), getNumberMembers());
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", location='" + location + '\'' +
                ", clubOwner='" + clubOwner + '\'' +
                ", numberStaff=" + numberStaff +
                ", numberMembers=" + numberMembers +
                '}';
    }
}

