package cs371m.utrivia;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by julioMendez on 8/9/16.
 */
public class HowTo extends BaseMenuActivity {
    private SoundPool mSounds;
    private int nextSound;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInstanceVarsFromSharedPrefs();
        setContentView(R.layout.how_to_layout);
    }

    public void toHome(View view) {
        if(gs.getSoundStatus().equals(gameSounds.SoundStatus.on)) {
            mSounds.play(nextSound, 1, 1, 1, 0, 1);
        }
        saveSoundState();
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
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
