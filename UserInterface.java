package com.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UserInterface {

    public static void display() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.recoverSaved("footballClub.ser");

        Stage window = new Stage();
        window.setTitle("Premiere League Manager");

        GridPane mainLayout = new GridPane();
        mainLayout.setPadding(new Insets(5, 20, 20, 20)); // Padding from the border
        mainLayout.setVgap(5);
        mainLayout.setHgap(15);

//        BackgroundImage myBI= new BackgroundImage(new Image("test.jpg",32,32,false,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        mainLayout.setBackground(new Background(myBI));

        // ------------------------------------------------ League Table -----------------------------------------------
        Label lblLeagueTable = createLabel("Premiere League Standings", "component-heading");
        mainLayout.add(lblLeagueTable, 0, 1, 2, 1);

        TableView<FootballClub> standingsTable = new TableView<>();
        standingsTable.getStyleClass().add("table");

        TableColumn<FootballClub, String> clubNameColumn = createStandingsTableColumn("Club", 144, 144, "clubName");
        TableColumn<FootballClub, String> playedColumn = createStandingsTableColumn("Played", 60, 60, "noOfMatchesPlayed");
        TableColumn<FootballClub, String> winsColumn = createStandingsTableColumn("Wins", 50, 50,"noOfWins");
        TableColumn<FootballClub, String> drawsColumn = createStandingsTableColumn("Draws", 50, 50, "noOfDraws");
        TableColumn<FootballClub, String> lossesColumn = createStandingsTableColumn("Losses", 50, 50,"noOfLosses");
        TableColumn<FootballClub, String> GSColumn = createStandingsTableColumn("GS", 50, 50, "goalsScored");
        TableColumn<FootballClub, String> GRColumn = createStandingsTableColumn("GR", 50, 50, "goalsReceived");
        TableColumn<FootballClub, String> GDColumn = createStandingsTableColumn("GD", 50, 50, "goalDifference");
        TableColumn<FootballClub, String> pointsColumn = createStandingsTableColumn("Pts", 50, 50, "clubPoints");

        standingsTable.setItems(getPremierLeagueTableData((ArrayList<FootballClub>) premierLeagueManager.getClubList()));
        standingsTable.getColumns().addAll(clubNameColumn, playedColumn, winsColumn, drawsColumn, lossesColumn, GSColumn, GRColumn, GDColumn, pointsColumn);

        mainLayout.add(standingsTable, 0, 2, 2, 15);

        // -------------------------------------------------------------------------------------------------------------
        // --------------------------------------------- Football Club adding Function--------------------------------------------
        Label lblAddClub = createLabel("Add New Club", "component-heading");
        mainLayout.add(lblAddClub, 2, 1, 5, 1);

        GridPane addClubLayout = new GridPane();
        addClubLayout.setPadding(new Insets(15, 15, 15, 15)); // Padding from the border
        addClubLayout.setVgap(6);
        addClubLayout.setHgap(10);
        addClubLayout.getStyleClass().add("component");

        // Club Name
        Label lblClubName = createLabel("Club Name:", "form-label-heading");
        TextField txtClubName = new TextField();
        addClubLayout.add(lblClubName, 0, 0, 1, 1);
        addClubLayout.add(txtClubName, 1, 0, 3, 1);

        // Club Location
        Label lblClubLocation = createLabel("Club Location:", "form-label-heading");
        TextField txtClubLocation = new TextField();
        addClubLayout.add(lblClubLocation, 0, 2, 1, 1);
        addClubLayout.add(txtClubLocation, 1, 2, 3, 1);

        // Club Owner
        Label lblClubOwner = createLabel("Club Owner:", "form-label-heading");
        TextField txtClubOwner = new TextField();
        addClubLayout.add(lblClubOwner, 0, 4, 1, 1);
        addClubLayout.add(txtClubOwner, 1, 4, 3, 1);

        //Staff and Members
        Label lblstaff= createLabel("Number of Staff:", "form-label-heading");
        TextField txtClubnumberOfStaff = new TextField(); txtClubnumberOfStaff.setPromptText("No.Staff");
        txtClubnumberOfStaff.getStyleClass().add("txt-small");
        Label lblmembers= createLabel("Number of Members:", "form-label-heading");
        TextField txtClubMembers = new TextField(); txtClubMembers.setPromptText("No.Members");
        txtClubMembers.getStyleClass().add("txt-small");
        addClubLayout.add(lblstaff, 0, 5, 1, 1);
        addClubLayout.add(txtClubnumberOfStaff, 1, 5, 1, 1);
        addClubLayout.add(lblmembers, 2, 5, 1, 1);
        addClubLayout.add(txtClubMembers, 3, 5, 1, 1);


        Button btnAddClub = new Button("Add Club");
        btnAddClub.getStyleClass().add("button");
        addClubLayout.add(btnAddClub, 0,7, 3, 1);
        btnAddClub.setOnAction(event -> {
            // Validation: Show alert if TextFields are empty
            if (checkForEmptyTextFields(txtClubName, txtClubLocation, txtClubOwner, txtClubnumberOfStaff, txtClubMembers)) {
                displayAlert("ERROR", "Empty Text Fields !", "One or more text fields are empty.");
            } else if (clubExists(premierLeagueManager, txtClubName.getText().toLowerCase())) {
                displayAlert("INFORMATION", "Club Already Exists !", "A club with the name '" +
                        txtClubName.getText() + "' already exists.");
            } else {
                String clubName = txtClubName.getText();
                String clubLocation = txtClubLocation.getText();
                String clubOwner = txtClubOwner.getText();
                int staff = Integer.parseInt(txtClubnumberOfStaff.getText());
                int members = Integer.parseInt(txtClubMembers.getText());
                premierLeagueManager.addClub(clubName, clubLocation, clubOwner, staff, members);
                emptyTextFields(txtClubName, txtClubLocation, txtClubOwner, txtClubnumberOfStaff, txtClubMembers);
                standingsTable.setItems(getPremierLeagueTableData((ArrayList<FootballClub>) premierLeagueManager.getClubList())); // Update standings table
            }
        });

        mainLayout.add(addClubLayout, 2, 2, 5, 6);
        // -------------------------------------------------------------------------------------------------------------
        // ------------------------------------------- Football Club Delete Component  -------------------------------------------
        Label lblDeleteClub = createLabel("Delete Existing Club", "component-heading");
        mainLayout.add(lblDeleteClub, 2, 12, 5, 1);

        GridPane deleteClubLayout = new GridPane();
        deleteClubLayout.getStyleClass().add("component");
        deleteClubLayout.setPadding(new Insets(15, 15, 15, 15)); // Padding from the border
        deleteClubLayout.setVgap(6);
        deleteClubLayout.setHgap(10);

        // Club Name
        Label lblDeleteClubName = createLabel("Club Name:", "form-label-heading");
        TextField txtDeleteClubName = new TextField();
        deleteClubLayout.add(lblDeleteClubName, 0, 0, 1, 1);
        deleteClubLayout.add(txtDeleteClubName, 1, 0, 3, 1);

        Button btnDeleteClub = new Button("Delete Club");
        btnDeleteClub.getStyleClass().add("button");
        btnDeleteClub.setOnAction(event -> {
            if (checkForEmptyTextFields(txtDeleteClubName)) {
                displayAlert("ERROR", "Empty Text Field !", "Enter the name of the club " +
                        "that needs to be deleted.");
            }
            String deletedClub = txtDeleteClubName.getText();
            premierLeagueManager.deleteClub(deletedClub);
            try {
                premierLeagueManager.save("footballClub.ser");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            standingsTable.setItems(getPremierLeagueTableData((ArrayList<FootballClub>) premierLeagueManager.getClubList()));
            emptyTextFields(txtDeleteClubName);
        });
        deleteClubLayout.add(btnDeleteClub, 0,3, 5, 1);

        mainLayout.add(deleteClubLayout, 2, 13, 5, 4);
        // -------------------------------------------------------------------------------------------------------------
        // --------------------------------------------- Matches Table -------------------------------------------------
        Label lblMatchesTable = new Label("Matches Played");
        lblMatchesTable.getStyleClass().add("component-heading");
        mainLayout.add(lblMatchesTable, 0, 19,1,1);

        TableView<Match> matchesTable = new TableView<>();
        matchesTable.getStyleClass().add("table");

        TableColumn<Match, String> datePlayedColumn = createMatchesTableColumn("Date Played", 114, 114, "datePlayed");
        TableColumn<Match, String> teamAColumn = createMatchesTableColumn("Team A", 150, 150, "teamA");
        TableColumn<Match, String> teamAScoreColumn = createMatchesTableColumn("Score", 70, 70, "teamAScore");
        TableColumn<Match, String> teamBColumn = createMatchesTableColumn("Team B", 150, 150, "teamB");
        TableColumn<Match, String> teamBScoreColumn = createMatchesTableColumn("Score", 70, 70, "teamBScore");

        matchesTable.setItems(getMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches()));
        matchesTable.getColumns().addAll(datePlayedColumn, teamAColumn, teamAScoreColumn, teamBColumn, teamBScoreColumn);

        Button btnSortMatchesPlayed = new Button("Sort By Date (Ascending)");
        btnSortMatchesPlayed.getStyleClass().add("button-sort-table");
        btnSortMatchesPlayed.setOnAction(event -> {
            // Sort array using Comparable
            Collections.sort(premierLeagueManager.getMatches());
            // Update matches table
            matchesTable.setItems(getMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches()));
        });
        mainLayout.add(btnSortMatchesPlayed, 1, 19, 1, 1);
        GridPane.setHalignment(btnSortMatchesPlayed, HPos.RIGHT);


        mainLayout.add(matchesTable, 0, 20, 2, 5);
        // -------------------------------------------------------------------------------------------------------------
        // --------------------------------------------- Add/ Sort Matches Played --------------------------------------
        Label lblAddMatch = new Label("Add New Match / Search Matches");
        lblAddMatch.getStyleClass().add("component-heading");
        mainLayout.add(lblAddMatch, 2, 19, 5, 1);

        GridPane addMatchLayout = new GridPane();
        addMatchLayout.setPadding(new Insets(15));
        addMatchLayout.setVgap(6);
        addMatchLayout.setHgap(10);
        addMatchLayout.getStyleClass().add("component");

        // Date Played
        Label lblDatePlayed = createLabel("Date Played:", "form-label-heading");
        TextField txtDatePlayedDay = new TextField(); txtDatePlayedDay.setPromptText("DD");
        txtDatePlayedDay.getStyleClass().add("txt-small");
        TextField txtDatePlayedMonth = new TextField(); txtDatePlayedMonth.setPromptText("MM");
        txtDatePlayedMonth.getStyleClass().add("txt-small");
        TextField txtDatePlayedYear = new TextField(); txtDatePlayedYear.setPromptText("YYYY");
        txtDatePlayedYear.getStyleClass().add("txt-medium");
        addMatchLayout.add(lblDatePlayed, 0, 0, 1, 1);
        addMatchLayout.add(txtDatePlayedDay, 1, 0, 1, 1);
        addMatchLayout.add(txtDatePlayedMonth, 2, 0, 1, 1);
        addMatchLayout.add(txtDatePlayedYear, 3, 0, 1, 1);

        // Team A
        Label lblTeamAName = createLabel("Team A: ", "form-label-heading");
        TextField txtTeamA = new TextField(); txtTeamA.setPromptText("Club Name");
        TextField txtTeamAScore = new TextField(); txtTeamAScore.setPromptText("Score");
        txtTeamAScore.getStyleClass().add("txt-medium");
        addMatchLayout.add(lblTeamAName, 0, 1, 1, 1);
        addMatchLayout.add(txtTeamA, 0, 2, 3, 1);
        addMatchLayout.add(txtTeamAScore, 3, 2, 1, 1);

        // Team B
        Label lblTeamBName = createLabel("Team B: ", "form-label-heading");
        TextField txtTeamB = new TextField(); txtTeamB.setPromptText("Club Name");
        TextField txtTeamBScore = new TextField(); txtTeamBScore.setPromptText("Score");
        txtTeamBScore.getStyleClass().add("txt-medium");
        addMatchLayout.add(lblTeamBName, 0, 3, 1, 1);
        addMatchLayout.add(txtTeamB, 0, 4, 3, 1);
        addMatchLayout.add(txtTeamBScore, 3, 4, 1, 1);

        // Button: To add a random match to the database
        Button btnAddRandomMatch = new Button("Add Random Match");
        btnAddRandomMatch.getStyleClass().add("button-random-match");
        btnAddRandomMatch.setOnAction(event -> {
            addRandomMatch(premierLeagueManager);
            matchesTable.setItems(getMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches()));
            standingsTable.setItems(getPremierLeagueTableData((ArrayList<FootballClub>) premierLeagueManager.getClubList()));
        });
        addMatchLayout.add(btnAddRandomMatch, 1, 5, 3, 1);

        // Button: To add a match with entered details
        Button btnAddMatch = new Button("Add Match");
        btnAddMatch.setPrefWidth(125);
        btnAddMatch.getStyleClass().add("button");
        btnAddMatch.setOnAction(event -> {
            if (checkForEmptyTextFields(txtDatePlayedDay, txtDatePlayedMonth, txtDatePlayedYear, txtTeamA, txtTeamAScore, txtTeamB, txtTeamBScore)) {
                displayAlert("ERROR", "Empty Text Fields !", "One or more text fields are empty.");
            } else {
                int day = Integer.parseInt(txtDatePlayedDay.getText());
                int month = Integer.parseInt(txtDatePlayedMonth.getText());
                int year = Integer.parseInt(txtDatePlayedYear.getText());
                Date datePlayed = new Date(year, month, day);

                String teamAName = txtTeamA.getText();
                int teamAScore = Integer.parseInt(txtTeamAScore.getText());
                String teamBName = txtTeamB.getText();
                int teamBScore = Integer.parseInt(txtTeamBScore.getText());
                if (!clubExists(premierLeagueManager, teamAName)) {
                    displayAlert("ERROR", "Invalid Team A", "A club with the name '" + teamAName + "' does not exist.");
                } else if (!clubExists(premierLeagueManager, teamBName)) {
                    displayAlert("ERROR", "Invalid Team B", "A club with the name '" + teamBName + "' does not exist.");
                } else {
                    premierLeagueManager.addPlayedMatch(datePlayed, teamAName, teamAScore, teamBName, teamBScore);
                    emptyTextFields(txtDatePlayedDay, txtDatePlayedMonth, txtDatePlayedYear, txtTeamA, txtTeamAScore, txtTeamB, txtTeamBScore);

                    matchesTable.setItems(getMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches())); // Update matches table
                    standingsTable.setItems(getPremierLeagueTableData((ArrayList<FootballClub>) premierLeagueManager.getClubList())); // Update standings table
                }
            }
        });
        addMatchLayout.add(btnAddMatch, 0, 5, 1, 1);

        // Error Label for Validation
        Label lblAddMatchValidation = createLabel("", "validation-text");
        addMatchLayout.add(lblAddMatchValidation, 0, 6, 4, 1);

        // ------------------------------------------- Search for Match ------------------------------------------------

        // Textfield: To search match by date
        Label lblSearchDatePlayed = createLabel("Date Played:", "form-label-heading");
        TextField txtSearchDay = new TextField(); txtSearchDay.setPromptText("DD");
        txtSearchDay.getStyleClass().add("txt-small");
        TextField txtSearchMonth = new TextField(); txtSearchMonth.setPromptText("MM");
        txtSearchMonth.getStyleClass().add("txt-small");
        TextField txtSearchYear = new TextField(); txtSearchYear.setPromptText("YYYY");
        txtSearchYear.getStyleClass().add("txt-medium");
        addMatchLayout.add(lblSearchDatePlayed, 0, 8, 1, 1);
        addMatchLayout.add(txtSearchDay, 1, 8, 1, 1);
        addMatchLayout.add(txtSearchMonth, 2, 8, 1, 1);
        addMatchLayout.add(txtSearchYear, 3, 8, 1, 1);

        // Button: To search match by date
        Button btnSearchMatch = new Button("Search Matches");
        addMatchLayout.add(btnSearchMatch, 0, 9, 1, 1);
        btnSearchMatch.setOnAction(event -> {
            if (checkForEmptyTextFields(txtSearchDay, txtSearchMonth, txtSearchYear)) {
                displayAlert("ERROR", "Empty Text Fields !", "One or more text fields are empty.");
            } else {
                int day = Integer.parseInt(txtSearchDay.getText());
                int month = Integer.parseInt(txtSearchMonth.getText());
                int year = Integer.parseInt(txtSearchYear.getText());
                Date searchDate = new Date(year, month, day);
                // Update table to only show results with entered date
                matchesTable.setItems(getSearchedMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches(), searchDate));
            }
        });

        // Button: Show all matches
        Button btnShowAllMatches = new Button("Show All Matches");
        btnShowAllMatches.getStyleClass().add("button-random-match");
        btnShowAllMatches.setOnAction(event -> {
            emptyTextFields(txtSearchDay, txtSearchMonth, txtSearchYear);
            matchesTable.setItems(getMatchTableData((ArrayList<Match>) premierLeagueManager.getMatches()));
        });
        addMatchLayout.add(btnShowAllMatches,1, 9, 3, 1);


        mainLayout.add(addMatchLayout, 2, 20, 5, 5);
        // -------------------------------------------------------------------------------------------------------------

        Scene scene = new Scene(mainLayout, 1010, 800);
        scene.getStylesheets().add(UserInterface.class.getResource("styles.css").toExternalForm());

        // Save data to file on closing GUI window
        window.setOnCloseRequest(event -> {
            try {
                premierLeagueManager.save("footballClub.ser");
                window.close();
                System.exit(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        window.setScene(scene);
        window.show();
    }

    private static void addRandomMatch(PremierLeagueManager premierLeagueManager) {
        Random random = new Random();

        // Generate random date
        int year = 2020; // Fixed year for random date
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        Date datePlayed = new Date(year, month, day);

        // Get random number between 0 and the max limit of the clubs array
        int clubsArraySize = premierLeagueManager.getClubList().size();

        // Select team 1 randomly from clubs list
        int randomNumber1 = random.nextInt(clubsArraySize);
        FootballClub teamA = premierLeagueManager.getClubList().get(randomNumber1);
        String teamAName = teamA.getClubName();

        // Selecting team 2:
        // Copy clubs array into a new arraylist
        ArrayList<FootballClub> tempList = new ArrayList<>(premierLeagueManager.getClubList());
        // Remove randomly selected Team A from that array list
        tempList.remove(teamA);
        // Randomly select Team B from the new list
        int randomNumber2 = random.nextInt(tempList.size());
        FootballClub teamB = tempList.get(randomNumber2);
        String teamBName = teamB.getClubName();

        // Get random score between 0-5
        // Assign it to the teamScore
        int teamAScore = random.nextInt(6);
        int teamBScore = random.nextInt(6);

        // Pass all variables to addMatchToPremiereLeague()
        premierLeagueManager.addPlayedMatch(datePlayed, teamAName, teamAScore, teamBName, teamBScore);
    }

    private static ObservableList<Match> getMatchTableData(ArrayList<Match> matches) {
        ObservableList<Match> matchesObservableList = FXCollections.observableArrayList();
        ArrayList<Date> datesList = UserInterface.getDatesPlayed(matches);
        ArrayList<FootballClub> teamAList = UserInterface.getTeamANames(matches);
        ArrayList<Integer> teamAScoresList = UserInterface.getTeamAScores(matches);
        ArrayList<FootballClub> teamBList = UserInterface.getTeamBNames(matches);
        ArrayList<Integer> teamBScoresList = UserInterface.getTeamBScores(matches);

        for (int i=0; i < teamAList.size(); i++) {
            matchesObservableList.add(new Match(datesList.get(i), teamAList.get(i), teamAScoresList.get(i),
                    teamBList.get(i), teamBScoresList.get(i)));
        }

        return matchesObservableList;
    }

    private static ObservableList<Match> getSearchedMatchTableData(ArrayList<Match> matches, Date date) {
        ObservableList<Match> matchesObservableList = FXCollections.observableArrayList();
        ArrayList<Date> datesList = UserInterface.searchedGetDatesPlayed(matches, date);
        ArrayList<FootballClub> teamAList = UserInterface.searchedGetTeam1Names(matches, date);
        ArrayList<Integer> teamAScoresList = UserInterface.searchedGetTeam1Scores(matches, date);
        ArrayList<FootballClub> teamBList = UserInterface.searchedGetTeam2Names(matches, date);
        ArrayList<Integer> teamBScoresList = UserInterface.searchedGetTeam2Scores(matches, date);

        for (int i=0; i < teamAList.size(); i++) {
            matchesObservableList.add(new Match(datesList.get(i), teamAList.get(i), teamAScoresList.get(i),
                    teamBList.get(i), teamBScoresList.get(i)));
        }

        return matchesObservableList;
    }

    public static ObservableList<FootballClub> getPremierLeagueTableData(ArrayList<FootballClub> clubs) {
        ObservableList<FootballClub> clubsObservableList = FXCollections.observableArrayList();
        ArrayList<String> clubName = UserInterface.getClubNames(clubs);
        ArrayList<Integer> matchesPlayed = UserInterface.getNoOfMatchesPlayed(clubs);
        ArrayList<Integer> noOfPoints = UserInterface.getClubPoints(clubs);
        ArrayList<Integer> noOfWins = UserInterface.getNoOfWins(clubs);
        ArrayList<Integer> noOfDraws = UserInterface.getNoOfDraws(clubs);
        ArrayList<Integer> noOfLosses = UserInterface.getNoOfLosses(clubs);
        ArrayList<Integer> goalsScored = UserInterface.getGoalsScored(clubs);
        ArrayList<Integer> goalsReceived = UserInterface.getGoalsReceived(clubs);
        ArrayList<Integer> goalDifference = UserInterface.getGoalDifference(clubs);

        for (int i=0; i < clubName.size(); i++) {
            clubsObservableList.add(new FootballClub(clubName.get(i), matchesPlayed.get(i), noOfPoints.get(i), noOfWins.get(i),
                    noOfDraws.get(i), noOfLosses.get(i), goalsScored.get(i), goalsReceived.get(i), goalDifference.get(i)));
        }

        return clubsObservableList;
    }

    // --------------------------------------- Search functions for Matches table --------------------------------------
    public static ArrayList<Date> searchedGetDatesPlayed(ArrayList<Match> matches, Date date)  {
        ArrayList<Date> datesPlayedList = new ArrayList<>();
        for (Match match: matches) {
            if (date.equals(match.getDate())) {
                Date datePlayed = match.getDate();
                datesPlayedList.add(datePlayed);
            }
        }
        return datesPlayedList;
    }

    public static ArrayList<FootballClub> searchedGetTeam1Names(ArrayList<Match> matches, Date date) {
        ArrayList<FootballClub> teamAList = new ArrayList<>();
        for (Match match: matches) {
            if (date.equals(match.getDate())) {
                FootballClub teamA = match.getTeamA();
                teamAList.add(teamA);
            }
        }
        return teamAList;
    }

    private static ArrayList<Integer> searchedGetTeam1Scores(ArrayList<Match> matches, Date date) {
        ArrayList<Integer> teamAScoresList = new ArrayList<>();
        for (Match match : matches) {
            if (date.equals(match.getDate())) {
                int teamAScore = match.getTeamAScore();
                teamAScoresList.add(teamAScore);
            }
        }
        return teamAScoresList;
    }

    public static ArrayList<FootballClub> searchedGetTeam2Names(ArrayList<Match> matches, Date date) {
        ArrayList<FootballClub> teamBList = new ArrayList<>();
        for (Match match: matches) {
            if (date.equals(match.getDate())) {
                FootballClub teamB = match.getTeamB();
                teamBList.add(teamB);
            }

        }
        return teamBList;
    }

    private static ArrayList<Integer> searchedGetTeam2Scores(ArrayList<Match> matches, Date date) {
        ArrayList<Integer> teamBScoresList = new ArrayList<>();
        for (Match match : matches) {
            if (date.equals(match.getDate())) {
                int teamBScore = match.getTeamBScore();
                teamBScoresList.add(teamBScore);
            }
        }
        return teamBScoresList;
    }

    // --------------------------------------- Functions to get data for Premiere League Table -------------------------
    public static ArrayList<String> getClubNames(ArrayList<FootballClub> clubs) {
        ArrayList<String> clubNames = new ArrayList<>();
        for (FootballClub club : clubs) {
            String clubName = club.getClubName();
            clubNames.add(clubName);
        }
        return clubNames;
    }

    public static ArrayList<Integer> getNoOfMatchesPlayed(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> noOfMatchesPlayedList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int noOfMatchesPlayed = club.getNumberOfMatchesPlayed();
            noOfMatchesPlayedList.add(noOfMatchesPlayed);
        }
        return noOfMatchesPlayedList;
    }

    public static ArrayList<Integer> getNoOfWins(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> noOfWinsList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int noOfWins = club.getNumberOfWins();
            noOfWinsList.add(noOfWins);
        }
        return noOfWinsList;
    }

    public static ArrayList<Integer> getNoOfDraws(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> noOfDrawsList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int noOfDraws = club.getNumberOfDraws();
            noOfDrawsList.add(noOfDraws);
        }
        return noOfDrawsList;
    }

    public static ArrayList<Integer> getNoOfLosses(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> noOfLossesList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int noOfLosses = club.getNumberOfDefeats();
            noOfLossesList.add(noOfLosses);
        }
        return noOfLossesList;
    }

    public static ArrayList<Integer> getGoalsScored(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> goalsScoredList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int goalsScored = club.getNumberOfScoredGoals();
            goalsScoredList.add(goalsScored);
        }
        return goalsScoredList;
    }

    public static ArrayList<Integer> getGoalsReceived(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> goalsReceivedList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int goalsReceived = club.getNumberOfGoalsReceived();
            goalsReceivedList.add(goalsReceived);
        }
        return goalsReceivedList;
    }

    public static ArrayList<Integer> getGoalDifference(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> goalDifferenceList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int goalDifference = club.getGoalDifference();
            goalDifferenceList.add(goalDifference);
        }
        return goalDifferenceList;
    }

    public static ArrayList<Integer> getClubPoints(ArrayList<FootballClub> clubs) {
        ArrayList<Integer> clubPointsList = new ArrayList<>();
        for (FootballClub club : clubs) {
            int clubPoints = club.getCurrentNumberOfPoints();
            clubPointsList.add(clubPoints);
        }
        return clubPointsList;
    }

    // --------------------------------- Functions to get data for Played Matches Table --------------------------------
    public static ArrayList<Date> getDatesPlayed(ArrayList<Match> matches) {
        ArrayList<Date> datesPlayedList = new ArrayList<>();
        for (Match match: matches) {
            Date datePlayed = match.getDate();
            datesPlayedList.add(datePlayed);
        }
        return datesPlayedList;
    }

    public static ArrayList<FootballClub> getTeamANames(ArrayList<Match> matches) {
        ArrayList<FootballClub> teamAList = new ArrayList<>();
        for (Match match: matches) {
            FootballClub teamA = match.getTeamA();
            teamAList.add(teamA);
        }
        return teamAList;
    }

    private static ArrayList<Integer> getTeamAScores(ArrayList<Match> matches) {
        ArrayList<Integer> teamAScoresList = new ArrayList<>();
        for (Match match : matches) {
            int teamAScore = match.getTeamAScore();
            teamAScoresList.add(teamAScore);
        }
        return teamAScoresList;
    }

    public static ArrayList<FootballClub> getTeamBNames(ArrayList<Match> matches) {
        ArrayList<FootballClub> teamBList = new ArrayList<>();
        for (Match match: matches) {
            FootballClub teamB = match.getTeamB();
            teamBList.add(teamB);
        }
        return teamBList;
    }

    private static ArrayList<Integer> getTeamBScores(ArrayList<Match> matches) {
        ArrayList<Integer> teamBScoresList = new ArrayList<>();
        for (Match match : matches) {
            int teamBScore = match.getTeamBScore();
            teamBScoresList.add(teamBScore);
        }
        return teamBScoresList;
    }

    // ---------------------------------------------- Utility Methods --------------------------------------------------
    private static TableColumn<FootballClub, String> createStandingsTableColumn(String columnHeader, int minWidth, int maxWidth, String field) {
        TableColumn<FootballClub, String> tableColumn = new TableColumn<>(columnHeader);
        tableColumn.setMinWidth(minWidth);
        tableColumn.setMaxWidth(maxWidth);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field));
        return tableColumn;
    }

    private static TableColumn<Match, String> createMatchesTableColumn(String columnHeader, int minWidth, int maxWidth, String field) {
        TableColumn<Match, String> tableColumn = new TableColumn<>(columnHeader);
        tableColumn.setMinWidth(minWidth);
        tableColumn.setMaxWidth(maxWidth);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field));
        return tableColumn;
    }

    private static void emptyTextFields(TextField... textFields) {
        for (TextField textField:textFields) {
            textField.setText("");
        }
    }

    // AlertBox for Validation
    public static Alert displayAlert(String alertType, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.valueOf(alertType));
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert;
    }

    // Method to avoid duplicate code when creating labels
    private static Label createLabel(String labelText, String styleClass) {
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // Check if parameter includes empty TextFields
    private static boolean checkForEmptyTextFields(TextField... textFields) {
        for (TextField textField: textFields) {
            if (textField.getText().trim().equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean clubExists(PremierLeagueManager premierLeagueManager, String clubName) {
        for (FootballClub club : premierLeagueManager.getClubList()) {
            if (club.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
