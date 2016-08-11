package cs371m.utrivia;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomePage extends BaseMenuActivity {
    String TAG = "HOME";
    // for all the sounds we play
    private SoundPool mSounds;
    private int nextSound;
    private int newgameSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInstanceVarsFromSharedPrefs();
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setInstanceVarsFromSharedPrefs();
        setSupportActionBar(toolbar);
    }

    public void toHighscores(View view) {
        if(gs.getSoundStatus().equals(gameSounds.SoundStatus.on)) {
            mSounds.play(newgameSound, 1, 1, 1, 0, 1);
        }
        saveSoundState();
        Intent intent = new Intent(this, TopHighscores.class);
        startActivity(intent);
    }

    public void startGame(View view) {
        if(gs.getSoundStatus().equals(gameSounds.SoundStatus.on)) {
            mSounds.play(newgameSound, 1, 1, 1, 0, 1);
        }
        saveSoundState();
        Intent intent = new Intent(this, Questionnaire.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "in onResume");
        mSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        // 2 = maximum sounds to play at the same time,
        // AudioManager.STREAM_MUSIC is the stream type typically used for games
        // 0 is the "the sample-rate converter quality. Currently has no effect. Use 0 for the default."


        newgameSound = mSounds.load(this, R.raw.newgame_sound, 1);
        nextSound = mSounds.load(this, R.raw.button_sound, 1);
        // Context, id of resource, priority (currently no effect)
    }


}
