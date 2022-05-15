package org.example;


import javafx.scene.layout.StackPane;

import java.io.*;


public class MenuTable extends StackPane {

    String[] names = new String[10];
    int[] scores = new int[10];
    MenuLabel[] leaderboard = new MenuLabel[10];

    FileWriter writer = new FileWriter("Players.txt", true);
    BufferedReader reader = new BufferedReader(new FileReader("Players.txt"));

    public MenuTable() throws IOException {
        copyToArr();

        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i] = new MenuLabel(names[i] + "       " + scores[i]);
            leaderboard[i].setLayoutX(300);
            leaderboard[i].setLayoutY(i*50);
            getChildren().add(leaderboard[i]);
        }
    }

    public MenuLabel[] getLeaderboard() {
        return leaderboard;
    }

    public void showBoard(){
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i].changeText(names[i] + "       " + scores[i]);
        }
    }

    public void copyToArr() throws IOException {
        String line = reader.readLine();

        int i = 0;

        while (line != null) {
            names[i] = (line.substring(0, line.indexOf(' ')));
            scores[i] = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
            i++;
            line = reader.readLine();
        }

    }

    public void copyToFile() throws IOException {
        clearFile();
        for (int i = 0; i < names.length; i++) {
            writer.write(names[i] + "       " + scores[i]);
            writer.append('\n');
        }
        writer.flush();
    }

    public void clearFile() throws IOException {
        PrintWriter remover = new PrintWriter("Players.txt");
        remover.print("");
        remover.close();
    }

    public void updateScore(int score, String name) throws IOException {
        for (int i = 0; i < scores.length; i++) {
            if(score >= scores[i]){
                listEditor(i, score, name);
                copyToFile();
                break;
            }
        }
    }

    public void listEditor(int i, int score, String name){
        int[] scoreBuff = new int[11];
        String[] nameBuff = new String[11];

        for (int j = 0; j < i; j++) {
            scoreBuff[j] = scores[j];
            nameBuff[j] = names[j];
        }

        scoreBuff[i] = score;
        nameBuff[i] = name;

        for (int j = i+1; j < scoreBuff.length; j++) {
            scoreBuff[j] = scores[j-1];
            nameBuff[j] = names[j-1];
        }

        for (int j = 0; j < scores.length; j++) {
            scores[j] = scoreBuff[j];
            names[j] = nameBuff[j];
        }
    }
}
