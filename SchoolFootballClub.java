package com.sample;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;
    private String schoolLocation;
    private String schoolCoach;
    private int studentsVolume;


    public SchoolFootballClub(String schoolName,String schoolLocation,String schoolCoach,String clubName, String location, String clubOwner, int numberStaff, int numberMembers,
                              int numberOfWins, int numberOfDraws,int numberOfDefeats, int numberOfGoals,
                              int numberOfMatchesPlayed,int currentNumberOfPoints) {
        super(clubName, location, clubOwner, numberStaff, numberMembers);
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
        this.schoolCoach = schoolCoach;
    }

    //Getters

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public String getSchoolCoach() {
        return schoolCoach;
    }

    public Integer getStudentsVolume() {
        return studentsVolume;
    }

    //Setters

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public void setSchoolCoach(String schoolCoach) {
        this.schoolCoach = schoolCoach;
    }

    public void setStudentsVolume(Integer studentsVolume) {
        this.studentsVolume = studentsVolume;
    }

}
