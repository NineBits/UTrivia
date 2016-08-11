package cs371m.utrivia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/cs371m.utrivia/databases/";

    private static String DB_NAME = "gameData.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;



    private static final String ID = "id";
    private static String QTEXT = "question";
    private static String ANSWER = "answer";
    private static String CHOICE_A = "A";
    private static String CHOICE_B = "B";
    private static String CHOICE_C = "C";
    private static String CHOICE_D = "D";


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){ //Originally dbExist but changed to !dbExist to allow for changes to db temporary
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase().close();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.



    public ArrayList<MultiQuestion> getAllQuestions() {
        ArrayList<MultiQuestion> question_list = new ArrayList<MultiQuestion>();
        String selectQuery = "SELECT  * FROM " + "MULTI_QUESTION";
        //database =this.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> choice_list = new ArrayList<String>();
                choice_list.add(cursor.getString(3));
                choice_list.add(cursor.getString(4));
                choice_list.add(cursor.getString(5));
                choice_list.add(cursor.getString(6));

                MultiQuestion question = new MultiQuestion(cursor.getString(1),cursor.getString(2),choice_list);

                question.setId(cursor.getInt(0));
                //question.setQuestion_text(cursor.getString(1));
                //question.setCorrect_answer(cursor.getString(2));
                //question.setcA(cursor.getString(3));
                //question.setcB(cursor.getString(4));
                //question.setcC(cursor.getString(5));
                //question.setcD(cursor.getString(6));
                question_list.add(question);
            } while (cursor.moveToNext());
            Collections.shuffle(question_list);
        }


        return question_list;
    }

    public ArrayList<MultiQuestion> getBonusQuestions() {
        ArrayList<MultiQuestion> question_list = new ArrayList<MultiQuestion>();
        String selectQuery = "SELECT  * FROM " + "BONUS_QUESTION";
        //database =this.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> choice_list = new ArrayList<String>();
                choice_list.add(cursor.getString(3));
                choice_list.add(cursor.getString(4));
                choice_list.add(cursor.getString(5));
                choice_list.add(cursor.getString(6));

                MultiQuestion question = new MultiQuestion(cursor.getString(1),cursor.getString(2),choice_list);

                question.setId(cursor.getInt(0));
                //question.setQuestion_text(cursor.getString(1));
                //question.setCorrect_answer(cursor.getString(2));
                //question.setcA(cursor.getString(3));
                //question.setcB(cursor.getString(4));
                //question.setcC(cursor.getString(5));
                //question.setcD(cursor.getString(6));
                question_list.add(question);
            } while (cursor.moveToNext());
            Collections.shuffle(question_list);
        }


        return question_list;
    }

    public ArrayList<HighscoreList> getAllHighscores() {
        ArrayList<HighscoreList> highscore_list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + "HIGHSCORES";
        //database =this.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HighscoreList highscore = new HighscoreList();
                highscore.setRank(cursor.getInt(0));
                highscore.setName(cursor.getString(1));
                highscore.setScore(cursor.getInt(2));
                highscore_list.add(highscore);
            } while (cursor.moveToNext());
        }


        return highscore_list;
    }

    public void updateHS(HighscoreList updatedHighscore, int newRank) {
        String rank = "" + updatedHighscore.getRank();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Rank", newRank);
        myDataBase.update("HIGHSCORES", contentValues,"Rank=?",new String[] {rank});

        Log.d("Updating: ", "Name: " + updatedHighscore.getName() + " oldRank: " + rank + "newRank: " + updatedHighscore.getRank());

    }

    public void deleteHS(int rank) {
        Log.d("Deleting ", "Rank: " + rank);
        myDataBase.delete("HIGHSCORES", "Rank=?", new String[]{("" + rank)});
    }

    public void addHS(HighscoreList highscore) {
        ContentValues values = new ContentValues();
        values.put("Rank", highscore.getRank());
        values.put("Name", highscore.getName());
        values.put("Score", highscore.getScore());

        Log.d("Adding ", "Rank: " + highscore.getRank() + " Name: " + highscore.getName() + " " + highscore.getScore());
        myDataBase.insert("HIGHSCORES", null, values);
    }

}