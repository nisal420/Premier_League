package com.sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.recoverSaved("footballClub.ser");


        while(true)
            try{
                {
                    System.out.println("===================================");
                    System.out.println("Enter [1] to add a Club");
                    System.out.println("Enter [2] to delete a club");
                    System.out.println("Enter [3] to Display Statistics");
                    System.out.println("Enter [4] to Display the Table");
                    System.out.println("Enter [5] to Add a played Match");
                    System.out.println("Enter [6] to Open GUI");
                    System.out.println("-----------------------------------");
                    System.out.print("\nPlease enter desired choice:- ");

                    int input = scanner.nextInt();

                    if (input == 1) {
                        System.out.print("\nPlease enter Club Name:- ");
                        String clubName = scanner.next();
                        System.out.print("Please Please enter location:- ");
                        String location = scanner.next();
                        System.out.print("Please enter Club Owner:- ");
                        String clubOwner = scanner.next();
                        System.out.print("Please enter the number of Staff:- ");
                        int staffNumber = Integer.parseInt(scanner.next());
                        System.out.print("Please enter the number of Members:- ");
                        int memberNumber = Integer.parseInt(scanner.next());

                        premierLeagueManager.addClub(clubName, location, clubOwner, staffNumber, memberNumber);
                        premierLeagueManager.save("footballClub.ser");

                    } else if (input == 2) {
                        Scanner scannerD = new Scanner(System.in);
                        System.out.println("Please enter Club Name:- ");
                        String clubName = scannerD.next();
                        premierLeagueManager.deleteClub(clubName);
                        premierLeagueManager.save("footballClub.ser");

                    } else if (input == 3) {
                        //Displaying the Statistics of a club
                        Scanner scannerS = new Scanner(System.in);
                        System.out.println("Please enter Club Name:- ");
                        String clubName = scannerS.nextLine();
                        premierLeagueManager.displayStatistics(clubName);

                    } else if (input == 4) {
                        premierLeagueManager.displayTable();

                    } else if (input == 5) {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Enter Date:- ");
                        int dateLine = scan.nextInt();
                        System.out.println("Enter Month:- ");
                        int monthLine = scan.nextInt();
                        System.out.println("Enter Year:- ");
                        int yearLine = scan.nextInt();

                        Date date = new Date(dateLine,monthLine,yearLine);

                        System.out.println("Please Enter the name of the team:- ");
                        String teamA = scan.next();
                        System.out.println("Please Enter number of points scored for the team:- ");
                        int teamAPoints = Integer.parseInt(scan.next());
                        System.out.println("Please Enter the name of the Opposite team:- ");
                        String teamB = scan.next();
                        System.out.println("Please Enter number of points scored for the Opposite team:- ");
                        int teamBPoints = Integer.parseInt(scan.next());
                        premierLeagueManager.addPlayedMatch(date,teamA,teamAPoints,teamB,teamBPoints);
                    }else if (input == 6){
                        premierLeagueManager.displayUserInterface("footballClub.ser");
                        System.exit(0);
                        break;
                    }
                }

            } catch (NumberFormatException | IOException e ) {
                e.printStackTrace();
            }catch (InputMismatchException e){
                System.out.println("Enter a Valid Input");
                return;
            }
    }

}






























