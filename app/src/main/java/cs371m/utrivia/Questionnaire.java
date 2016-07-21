package cs371m.utrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
    MultiQuestion current_question;
    int numQuestions = 0;
    int score = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_layout);
        DatabaseConnector database = new DatabaseConnector(this);
        final  ArrayList<MultiQuestion> question_list =database.getAllQuestions();
        current_question = question_list.get(numQuestions);
        qText = (TextView) findViewById(R.id.question_text);
        score_display = (TextView) findViewById(R.id.score_display);
        option_A = (RadioButton) findViewById(R.id.radio_button_a);
        option_B = (RadioButton) findViewById(R.id.radio_button_b);
        option_C = (RadioButton) findViewById(R.id.radio_button_c);
        option_D = (RadioButton) findViewById(R.id.radio_button_d);


        next_button = (Button) findViewById(R.id.next_button);
        setQuestion();
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action for onclick

                RadioGroup rGroup = (RadioGroup) findViewById(R.id.rGroup);
                RadioButton answer = (RadioButton) Questionnaire.this.findViewById(rGroup.getCheckedRadioButtonId());
                Log.d("yourans", current_question.getCorrect_answer()+" "+answer.getText());
                if(answer.getText().toString().equals(current_question.getCorrect_answer())) {
                    score += 5;
                }

                if(numQuestions < 5) {
                    current_question = question_list.get(numQuestions);
                    setQuestion();
                } else {
                    //Start Bonus Question Activity
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



}
