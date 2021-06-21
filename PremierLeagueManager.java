package com.sample;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.List;

public class PremierLeagueManager extends Application implements LeagueManager  {
    private List<FootballClub> clubList = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    @Override
    public void addClub(String clubName, String location, String clubOwner, int numberStaff, int numberMembers) {
        FootballClub add = new FootballClub(clubName, location, clubOwner, numberStaff, numberMembers);
        clubList.add(add);
    }

    @Override
    public void deleteClub(String clubName) {
        for (FootballClub footballClub : clubList) {
            if (footballClub.getClubName().equalsIgnoreCase(clubName)) {
                clubList.remove(footballClub);
                System.out.println("Club " + clubName + " Has been Removed.");
                System.out.println("\n--------------------------------\n");
                System.out.println("No. of league clubs: " + clubList.size());
                System.out.println("No. of vacant slots: " + (20 - clubList.size()));
                return;
            } else {
                System.out.println("Please Enter a valid Club Name !");
            }
        }
    }

    @Override
    public void displayStatistics(String clubName) {
        for (FootballClub footballClub : clubList) {
            System.out.println("=================================");
            System.out.println(footballClub.toString());
            System.out.println("=================================");
        }
    }

    @Override
    public void displayTable() {
        System.out.printf("%-12s%-12s%-10s%-8s%-10s%-15s%-17s%-17s%20s%-23s\n", "Club Name|", "Location|", "Owner|", "No.Wins|", "No.Draws|", "No.Defeats|", "No.Scored Goals|", "No.ReceivedGoals|",
                "No.Matches Played|", "No.Current Points");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        clubList.sort(Collections.reverseOrder());
        for (FootballClub footballClub : clubList) {
            String clubName = footballClub.getClubName();
            String clubLocation = footballClub.getLocation();
            String clubOwner = footballClub.getClubOwner();
            System.out.printf("%-12s%-12s%-12s%-10d%-10d%-15d%-20d%-21s%-19d%-16d\n", clubName, clubLocation, clubOwner, footballClub.getNumberOfWins()
                    , footballClub.getNumberOfDraws(), footballClub.getNumberOfDefeats(), footballClub.getNumberOfScoredGoals(), footballClub.getNumberOfGoalsReceived(), footballClub.getNumberOfMatchesPlayed(), footballClub.getCurrentNumberOfPoints());
        }
    }

    @Override
    public void addPlayedMatch(Date date, String teamAName, int teamAScore, String teamBName, int teamBScore) {
        FootballClub teamA = extractFootballClub(teamAName);
        FootballClub teamB = extractFootballClub(teamBName);

        Match match = new Match(date,teamA,teamAScore,teamB,teamBScore);
        updateMatchScore(match);
        matches.add(match);
        updateMatchScore(match);
        clubList.sort(Collections.reverseOrder());
        System.out.println("The Match Played "+ date + " Has been Updated.");

    }

    public void updateMatchScore(Match match) {
        match.getTeamA().setNumberOfMatchesPlayed(match.getTeamA().getNumberOfMatchesPlayed() + 1);
        match.getTeamB().setNumberOfMatchesPlayed(match.getTeamB().getNumberOfMatchesPlayed() + 1);

        match.getTeamA().setNumberOfScoredGoals(match.getTeamA().getNumberOfScoredGoals() + match.getTeamAScore());
        match.getTeamB().setNumberOfScoredGoals(match.getTeamB().getNumberOfScoredGoals() + match.getTeamBScore());

        match.getTeamA().setNumberOfGoalsReceived(match.getTeamA().getNumberOfGoalsReceived() + match.getTeamAScore());
        match.getTeamB().setNumberOfGoalsReceived(match.getTeamB().getNumberOfGoalsReceived() + match.getTeamBScore());

        if (match.getTeamAScore() > match.getTeamBScore()) {
            match.getTeamA().setNumberOfWins(match.getTeamA().getNumberOfWins() + 1);
            match.getTeamB().setNumberOfDefeats(match.getTeamB().getNumberOfDefeats() + 1);
            match.getTeamA().setCurrentNumberOfPoints(match.getTeamA().getCurrentNumberOfPoints() + 3);
        } else if (match.getTeamAScore() < match.getTeamBScore()) {
            match.getTeamB().setNumberOfWins(match.getTeamB().getNumberOfWins() + 1);
            match.getTeamA().setNumberOfDefeats(match.getTeamA().getNumberOfDefeats() + 1);
            match.getTeamB().setCurrentNumberOfPoints(match.getTeamB().getCurrentNumberOfPoints() + 3);
        } else if (match.getTeamAScore() == match.getTeamBScore()){
            match.getTeamA().setNumberOfDraws(match.getTeamA().getNumberOfDraws() + 1);
            match.getTeamB().setNumberOfDraws(match.getTeamB().getNumberOfDraws() + 1);
            match.getTeamA().setCurrentNumberOfPoints(match.getTeamA().getCurrentNumberOfPoints() + 1);
            match.getTeamB().setCurrentNumberOfPoints(match.getTeamB().getCurrentNumberOfPoints() + 1);
        }
    }

    @Override
    public void recoverSaved(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object clubListArrayObject = objectInputStream.readObject();
            clubList = (ArrayList<FootballClub>) clubListArrayObject;
            Object matchesArrayObject = objectInputStream.readObject();
            matches = (ArrayList<Match>) matchesArrayObject;
        } catch (FileNotFoundException | EOFException e) {
            File file = new File(fileName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(clubList);
        outputStream.writeObject(matches);
        outputStream.close();
        fileOutputStream.close();
    }

    private FootballClub extractFootballClub(String clubName) {
        for (FootballClub club : clubList) {
            if (club.getClubName().equals(clubName)) {
                return club;
            }
        }
        return null;
    }

    @Override
    public void displayUserInterface(String fileName) throws IOException {
        this.save(fileName);
        Application.launch();
    }

    public List<FootballClub> getClubList() {
        return clubList;
    }

    public void setClubList(List<FootballClub> clubList) {
        this.clubList = clubList;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserInterface.display();
    }
}

