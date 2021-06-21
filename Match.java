package com.sample;

import java.io.Serializable;


public class Match implements Serializable, Comparable<Match>  {
    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;

    public Match(Date date,FootballClub teamA, int teamAScore,FootballClub teamB,int teamBScore){
        this.date = date;
        this.teamA = teamA;
        this.teamAScore = teamAScore;
        this.teamB = teamB;
        this.teamBScore = teamBScore;

    }

    public Match() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FootballClub getTeamA() {
        return teamA;
    }

    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }

    public FootballClub getTeamB() {
        return teamB;
    }

    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    @Override
    public int compareTo(Match m) {
        return getDate().compareTo(m.getDate());
    }

}
