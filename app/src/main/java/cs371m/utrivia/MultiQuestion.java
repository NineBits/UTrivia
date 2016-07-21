package cs371m.utrivia;

import java.util.ArrayList;
import java.util.Collections;

public class MultiQuestion {

    private int id;
    private String question_text;
    private String correct_answer;
    private String cA;
    private String cB;
    private String cC;
    private String cD;
    ArrayList<String> choice_list;

    public MultiQuestion(String question_text, String answer, ArrayList<String> choice_list) {
        this.question_text = question_text;
        correct_answer = answer;
        this.choice_list = choice_list;
        Collections.shuffle(choice_list);
        this.cA = choice_list.get(0);
        this.cB = choice_list.get(1);
        this.cC = choice_list.get(2);
        this.cD = choice_list.get(3);
    }

    public MultiQuestion() {
        id = 0;
        question_text = "";
        correct_answer = "";
        cA = "";
        cB = "";
        cC = "";
        cD = "";
    }

    public String getQuestion_text() { return question_text; }
    public String getCorrect_answer() { return correct_answer; }
    public String getcA() { return cA;}
    public String getcB() { return cB;}
    public String getcC() { return cC;}
    public String getcD() { return cD;}


    public void setQuestion_text(String qt) { question_text = qt;}
    public void setCorrect_answer(String answer) {correct_answer = answer;}
    public void setId(int ID) { id = ID;}
    public void setcA(String c) { cA = c;}
    public void setcB(String c) { cB = c;}
    public void setcC(String c) { cC = c;}
    public void setcD(String c) { cD = c;}



}
