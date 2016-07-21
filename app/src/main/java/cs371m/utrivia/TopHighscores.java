package cs371m.utrivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import cs371m.utrivia.Questionnaire;

/**
 * Created by julioMendez on 7/17/16.
 *
 * Top Highscores Activity displays top 5 highscores.
 */

public class TopHighscores extends Activity {
    private static final String HIGHSCORES_FILE= "highscore_file";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_highscores_layout);

        fillHighscoreWithStatic();

//        new CreateHighscoresTask().execute((Object[]) null);
//        new GetHighscoresTask().execute((Object[]) null);
    }

    private void fillHighscoreWithStatic() {
        TextView scoreList = (TextView) findViewById(R.id.highscoreList);
        TextView highscore_numbers = (TextView) findViewById(R.id.highscoreNumbers);
        String highscores = "Larry\n" +
                            "Carol\n" +
                            "John\n" +
                            "Kevin\n" +
                            "Billy\n";

        String highscoreNumbers = "60\n55\n50\n45\n40";

        scoreList.setText(highscores);
        highscore_numbers.setText(highscoreNumbers);
    }

    public void toHome(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

//    private void fillHighscoreList() {
//        TextView scoreList = (TextView) findViewById(R.id.highscoreList);
//        String highscores = "";
//        try {
//            FileInputStream fis = openFileInput(HIGHSCORES_FILE);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//
//            String line = reader.readLine();
//            while(line != null) {
//                highscores += line + "\n";
//            }
//            fis.close();
//        }
//        catch(java.io.IOException e) {
//            System.out.println("Error writing to file");
//        }
//
//        scoreList.setText(highscores);
//    }
//
//    private void createHighScoreList() {
//        // Iterate through local files to check if HIGHSCORES_FILE was already created
//        String[] fileList = fileList();
//        boolean file_exists = false;
//        for(String file : fileList) {
//            if(file.equals(HIGHSCORES_FILE))
//                file_exists = true;
//        }
//
//        // If HIGHSCORES_FILE doesn't exist, then load up default highscores
//        if(!file_exists) {
//            try {
//                FileOutputStream fos = openFileOutput(HIGHSCORES_FILE, Context.MODE_PRIVATE);
//                fos.write(("Hello World").getBytes());
//                fos.close();
//            }
//            catch(java.io.IOException e) {
//                System.out.println("Error writing to file");
//            }
//        }
//
//    }
//
//
//
//    // performs database query outside GUI thread
//    private class GetHighscoresTask extends AsyncTask<Object, Object, Cursor> {
//
//        // perform the database access
//        @Override
//        protected Cursor doInBackground(Object... params) {
//            Cursor nothing = null;
//
//            fillHighscoreList();
//
//            return nothing;
//        }
//    }
//
//    // performs database query outside GUI thread
//    private class CreateHighscoresTask extends AsyncTask<Object, Object, Cursor> {
//
//        // perform the database access
//        @Override
//        protected Cursor doInBackground(Object... params) {
//            Cursor nothing = null;
//
//            createHighScoreList();
//
//            return nothing;
//        }
//    }
}
