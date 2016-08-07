package cs371m.utrivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by julioMendez on 7/19/16.
 */
/*
public class Questionnaire extends Activity{
    public static Map topHighscores;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If we decide to separate the layout by question type,
        // perhaps an if statement here deciding which layout to load?
        // If we decide to do it like that
        setContentView(R.layout.questionnaire_layout);
    }

    public void toHighscore(View view) {
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }
}
*/
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Questionnaire extends AppCompatActivity {
    TextView qText, score_display;
    RadioButton option_A, option_B, option_C,option_D;
    RadioGroup rGroup;
    Button next_button;
    Button lifeline_button;
    MultiQuestion current_question;
    int numQuestions = 0;
    int score = 0;

    public static final String TOTALSCORE = "TotalScore";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DatabaseHelper myDbHelper = new DatabaseHelper(null);
        myDbHelper = new DatabaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
       // myDbHelper.read();
        setContentView(R.layout.questionnaire_layout);

       // DatabaseConnector database = new DatabaseConnector(this);
        final  ArrayList<MultiQuestion> question_list = myDbHelper.getAllQuestions(); //database.getAllQuestions();
        current_question = question_list.get(numQuestions);
        qText = (TextView) findViewById(R.id.question_text);
        score_display = (TextView) findViewById(R.id.score_display);
        option_A = (RadioButton) findViewById(R.id.radio_button_a);
        option_B = (RadioButton) findViewById(R.id.radio_button_b);
        option_C = (RadioButton) findViewById(R.id.radio_button_c);
        option_D = (RadioButton) findViewById(R.id.radio_button_d);


        next_button = (Button) findViewById(R.id.next_button);
        lifeline_button = (Button) findViewById(R.id.lifeline_button);
        setQuestion();
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rGroup = (RadioGroup) findViewById(R.id.rGroup);
                RadioButton answer = (RadioButton) Questionnaire.this.findViewById(rGroup.getCheckedRadioButtonId());
                Log.d("yourans", current_question.getCorrect_answer()+" "+answer.getText());
                if(answer.getText().toString().equals(current_question.getCorrect_answer())) {
                    score += 5;
                }

                //action for onclick
                if(numQuestions >= 15) {
                    toHighscore(v);
                }

                if(numQuestions < 15) {
                    lifeline_button.setEnabled(true);
                    option_C.setVisibility(View.VISIBLE);
                    option_D.setVisibility(View.VISIBLE);
                    boolean useLifeline = false;
                    /*
                    lifeline_button.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {

                          boolean useLifeline = true;
                          Log.d("Lifeline: ", " " + useLifeline);
                        }
                    });
                    */
                    current_question = question_list.get(numQuestions);
                    setQuestion();
                    // To bonus question
                }

            }
        });
    }
    public void setQuestion()
    {
        score_display.setText("SCORE: " + score);
        qText.setText(current_question.getQuestion_text());

        option_A.setText(current_question.getcA());
        option_B.setText(current_question.getcB());
        option_C.setText(current_question.getcC());
        option_D.setText(current_question.getcD());
        numQuestions++;
    }


    public void toHighscore(View view) {
        Intent intent = new Intent(this, Highscore.class);


        SharedPreferences.Editor sharedScore = getSharedPreferences(TOTALSCORE, MODE_PRIVATE).edit();
        sharedScore.putInt("score", score);
        sharedScore.apply();
        //setting preferences

        startActivity(intent);
        startActivity(intent);
    }


    public void onClickLifeline(View view) {
        ArrayList<String> current_choices = new ArrayList<String>();
        ArrayList<String> wrong_choices = current_question.getChoice_list();
        wrong_choices.remove(current_question.getCorrect_answer());
        //Log.d("yourans","SIZE : " + current_question.getChoice_list().size());
        Collections.shuffle(wrong_choices);
        current_choices.add(current_question.getCorrect_answer());
        current_choices.add(wrong_choices.get(0));
        Collections.shuffle(current_choices);
        option_A.setText(current_choices.get(0));
        option_B.setText(current_choices.get(1));
        option_C.setVisibility(View.INVISIBLE);
        option_D.setVisibility(View.INVISIBLE);

        //Need to add score logic to lifeline
        lifeline_button.setEnabled(false);
    }
}
