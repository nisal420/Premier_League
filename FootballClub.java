package com.sample;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable,Comparable<FootballClub>{
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfDefeats;
    private int numberOfScoredGoals;
    private int numberOfGoalsReceived;
    private int numberOfMatchesPlayed;
    private int currentNumberOfPoints;

    //Constructor
    public FootballClub(String clubName, String location, String clubOwner, int numberStaff, int numberMembers,
                        int numberOfWins, int numberOfDraws,int numberOfDefeats, int numberOfScoredGoals, int numberOfGoalsReceived,
                        int numberOfMatchesPlayed,int currentNumberOfPoints) {
        super(clubName,location,clubOwner,numberStaff,numberMembers);
        this.numberOfWins = numberOfWins;
        this.numberOfDraws = numberOfDraws;
        this.numberOfDefeats = numberOfDefeats;
        this.numberOfScoredGoals = numberOfScoredGoals;
        this.numberOfGoalsReceived = numberOfGoalsReceived;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    public FootballClub(String clubName, String location, String clubOwner, int numberStaff, int numberMembers){
        super(clubName,location,clubOwner,numberStaff,numberMembers);
    }

    public FootballClub(String clubName, int noOfMatchesPlayed, int clubPoints, int noOfWins, int noOfDraws, int noOfDefeats,
                        int goalsScored, int goalsReceived,  int goalDifference) {

        setClubName(clubName);
        this.numberOfMatchesPlayed = noOfMatchesPlayed;
        this.currentNumberOfPoints = clubPoints;
        this.numberOfWins = noOfWins;
        this.numberOfDefeats = noOfDefeats;
        this.numberOfDraws = noOfDraws;
        this.numberOfGoalsReceived = goalsReceived;
        this.numberOfScoredGoals = goalsScored;
    }


    //Getters & Setters
    public int getCurrentNumberOfPoints() {
        return currentNumberOfPoints;
    }
    public void setCurrentNumberOfPoints(int currentNumberOfPoints) {
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }
    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    public int getNumberOfScoredGoals() {
        return numberOfScoredGoals;
    }
    public void setNumberOfScoredGoals(int numberOfScoredGoals) {
        this.numberOfScoredGoals = numberOfScoredGoals;
    }

    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }
    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }
    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfGoalsReceived() {
        return numberOfGoalsReceived;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived) {
        this.numberOfGoalsReceived = numberOfGoalsReceived;
    }

    public int getGoalDifference() { return this.getNumberOfScoredGoals() - this.getNumberOfGoalsReceived(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballClub)) return false;
        FootballClub that = (FootballClub) o;
        return getNumberOfWins() == that.getNumberOfWins() &&
                getNumberOfDraws() == that.getNumberOfDraws() &&
                getNumberOfDefeats() == that.getNumberOfDefeats() &&
                getNumberOfScoredGoals() == that.getNumberOfScoredGoals() &&
                getNumberOfGoalsReceived() == that.getNumberOfGoalsReceived() &&
                getNumberOfMatchesPlayed() == that.getNumberOfMatchesPlayed() &&
                getCurrentNumberOfPoints() == that.getCurrentNumberOfPoints();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfWins(), getNumberOfDraws(), getNumberOfDefeats(), getNumberOfScoredGoals(), getNumberOfMatchesPlayed(), getCurrentNumberOfPoints());
    }

    @Override
    public String toString() {
        return "FootballClub "+ getClubName() +" in "+ getLocation() +" Owned by "+ getClubOwner() +"\n" +
                " number Of Wins = " + numberOfWins +
                "\n number Of Draws = " + numberOfDraws +
                "\n number Of Defeats = " + numberOfDefeats +
                "\n number Of Goals = " + numberOfScoredGoals +
                "\n numberOf Goals Received = " + numberOfGoalsReceived +
                "\n number Of Matches Played = " + numberOfMatchesPlayed +
                "\n current Number Of Points = " + currentNumberOfPoints
                ;
    }

    @Override
    public int compareTo(FootballClub o) {
        int foot = this.currentNumberOfPoints - o.getCurrentNumberOfPoints();
        if (foot == 0){
            foot = (this.numberOfScoredGoals-this.numberOfGoalsReceived) - (o.getNumberOfScoredGoals()-o.getNumberOfGoalsReceived());
            return foot;
        }
        return foot;
    }
}
