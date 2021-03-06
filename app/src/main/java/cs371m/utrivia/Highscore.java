package cs371m.utrivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import cs371m.utrivia.Questionnaire;

/**
 * Created by julioMendez on 7/19/16.
 */
public class Highscore extends BaseMenuActivity {
    boolean newHighscoreFlag = false;
    DatabaseHelper myDbHelper;
    HighscoreList newHighscore;

    // for all the sounds we play
    private SoundPool mSounds;
    private int nextSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        newHighscoreFlag = false;
        super.onCreate(savedInstanceState);

        SharedPreferences totalscore = getSharedPreferences(Questionnaire.TOTALSCORE, MODE_PRIVATE);
        int restoredscore = totalscore.getInt("score", 0);

        newHighscore = new HighscoreList();
        newHighscore.setScore(restoredscore);

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

        ArrayList<HighscoreList> highscore_list = myDbHelper.getAllHighscores();
        setNewHighscore(myDbHelper, highscore_list, newHighscore);


        TextView score = (TextView) findViewById(R.id.userscore);
        score.setText(Integer.toString(restoredscore));

    }

    public void toHome(View view) {
        if(newHighscoreFlag) {
            final EditText editTextName = (EditText) findViewById(R.id.userName);
            newHighscore.setName(editTextName.getText().toString());
            myDbHelper.deleteHS(newHighscore.getScore());
            myDbHelper.addHS(newHighscore);
        }
        Intent intent = new Intent(this, TopHighscores.class);
        mSounds.play(nextSound, 1, 1, 1, 0, 1);
        startActivity(intent);
    }

    private void setNewHighscore(DatabaseHelper myDbHelper, ArrayList<HighscoreList> highscore_list, HighscoreList newHighscore) {

        Log.d("highscores", "Iterating through highscores with: " + newHighscore.getScore());
        for(int i=0; i < 5; i++) {
            Log.d("Rank", highscore_list.get(i).getRank() + " Score: " + highscore_list.get(i).getScore());
            if(newHighscore.getScore() > highscore_list.get(i).getScore()) {
                newHighscoreFlag = true;
                // Delete Last Place Rank
                myDbHelper.updateHS(highscore_list.get(4), newHighscore.getScore());

                if(i < 4){
                    for(int j = 3; j >= i; j--) {
                        myDbHelper.updateHS(highscore_list.get(j), (j+2));
                    }
                    newHighscore.setRank(i+1);
                    break;
                }
                else {
                    newHighscore.setRank(5);
                    break;
                }
            }
        }

        if(newHighscoreFlag) {
            Log.d("Highscore Layout", "New Highscore Layout");
            setContentView(R.layout.new_highscore_layout);
        }
        else {
            Log.d("Highscore Layout", "Regular Highscore Layout");
            setContentView(R.layout.highscore_layout);
        }
    }
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "in onResume");
        mSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        // 2 = maximum sounds to play at the same time,
        // AudioManager.STREAM_MUSIC is the stream type typically used for games
        // 0 is the "the sample-rate converter quality. Currently has no effect. Use 0 for the default."

        nextSound = mSounds.load(this, R.raw.button_sound, 1);
        // Context, id of resource, priority (currently no effect)


    }

}
