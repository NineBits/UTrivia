package cs371m.utrivia;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by kellypc on 8/10/2016.
 */
public class BaseMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //mute audio
            //might be temp solution since this method is deprecated.
            /*
            AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            amanager.setStreamMute(AudioManager.STREAM_RING, true);
            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
            */
            // int currentDifficulty = mGame.getDifficultyLevel().ordinal();
            SoundDialogFragment soundDialogFragment
                    = SoundDialogFragment.newInstance(R.bool.mute_sound);
            soundDialogFragment.show(fm, "sound");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toAbout(MenuItem item) {
        Intent intent = new Intent(this, HowTo.class);
        startActivity(intent);
    }

    public void setSound(int status) {
        //Log.d(TAG,""+status);

        Toast.makeText(getApplicationContext(),"HELLO: " + status,
                Toast.LENGTH_SHORT).show();

    }

}
