package cs371m.utrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by julioMendez on 7/17/16.
 *
 * Top Highscores Activity displays top 5 highscores.
 */

public class TopHighscores extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_highscores_layout);

        ListView mHighScoresList = (ListView) findViewById(R.id.highscoreList);

    }

    public void toHome(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
