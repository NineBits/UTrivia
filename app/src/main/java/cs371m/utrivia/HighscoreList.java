package cs371m.utrivia;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreList {

    private int rank;
    private String name;
    private int score;

    public HighscoreList(int rank, String name, int score, ArrayList<String> choice_list) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public HighscoreList() {
        rank = 6;
        name = "Ghost";
        score = 0;
    }

    public int getRank() { return rank; }
    public String getName() { return name; }
    public int getScore() { return score;}


    public void setRank(int newRank) { rank = newRank;}
    public void setName(String newName) { name = newName;}
    public void setScore(int newScore) { score = newScore;}



}
