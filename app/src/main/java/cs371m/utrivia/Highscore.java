package cs371m.utrivia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import cs371m.utrivia.Questionnaire;

/**
 * Created by julioMendez on 7/19/16.
 */
public class Highscore extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_layout);

        SharedPreferences totalscore = getSharedPreferences(Questionnaire.TOTALSCORE, MODE_PRIVATE);
        int restoredscore = totalscore.getInt("score", 0);

        DatabaseHelper myDbHelper = new DatabaseHelper(this);

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

        TextView score = (TextView) findViewById(R.id.userscore);
        score.setText(Integer.toString(restoredscore));

    }

    public void toHome(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
