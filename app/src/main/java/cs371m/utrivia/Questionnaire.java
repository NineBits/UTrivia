package cs371m.utrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by julioMendez on 7/19/16.
 */
public class Questionnaire extends Activity{
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
