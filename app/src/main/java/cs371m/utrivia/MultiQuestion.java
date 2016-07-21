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



    public MultiQuestion(String question_text,String cA, String cB, String cC, String cD, String answer) {
        this.question_text = question_text;
        correct_answer = answer;
        this.cA = cA;
        this.cB = cB;
        this.cC = cC;
        this.cD = cD;
        //Collections.shuffle(answer_choices);
    }
    public MultiQuestion(String question_text, String answer, ArrayList<String> choice_list) {
        this.question_text = question_text;
        correct_answer = answer;
        this.choice_list = choice_list;
        Collections.shuffle(choice_list);
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
    public int getID() {return id;}

    public void setQuestion_text(String qt) { question_text = qt;}
    public void setCorrect_answer(String answer) {correct_answer = answer;}
    public void setId(int ID) { id = ID;}
    public void setcA(String c) { cA = c;}
    public void setcB(String c) { cB = c;}
    public void setcC(String c) { cC = c;}
    public void setcD(String c) { cD = c;}



}
