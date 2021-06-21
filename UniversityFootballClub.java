package com.sample;

public class UniversityFootballClub extends FootballClub {

    private String universityName;
    private String universityLocation;
    private String universityCoach;
    private int studentVolume;

    //Constructor
    public UniversityFootballClub(String universityName,String universityLocation,String universityCoach,String clubName, String location, String clubOwner, int numberStaff, int numberMembers,
                                  int numberOfWins, int numberOfDraws,int numberOfDefeats, int numberOfGoals,
                                  int numberOfMatchesPlayed,int currentNumberOfPoints) {
        super(clubName, location, clubOwner, numberStaff, numberMembers);
        this.universityName = universityName;
        this.universityLocation = universityLocation;
        this.universityCoach = universityCoach;
    }

    //Getters & Setters
    public String getUniversityName() {
        return universityName;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }
    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public String getUniversityCoach() {
        return universityCoach;
    }
    public void setUniversityCoach(String universityCoach) {
        this.universityCoach = universityCoach;
    }

    public int getStudentVolume() {
        return studentVolume;
    }
    public void setStudentVolume(int studentVolume) {
        this.studentVolume = studentVolume;
    }

}
