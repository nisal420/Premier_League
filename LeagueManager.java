package com.sample;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface LeagueManager {
    void addClub(String clubName, String location, String clubOwner, int numberStaff, int numberMembers);

    void deleteClub(String clubName);

    void displayStatistics(String clubName);

    void displayTable();

    void addPlayedMatch(Date date,String teamA,int teamAScore,String teamB,int teamBScore);

    void recoverSaved(String filename);

    void save(String filename) throws IOException;

    void displayUserInterface(String fileName) throws IOException;
}
