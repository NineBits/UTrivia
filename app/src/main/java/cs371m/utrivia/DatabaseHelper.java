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
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/cs371m.utrivia/databases/";

    private static String DB_NAME = "questions.db";

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

        if(!dbExist){ //Originally dbExist but changed to !dbExist to allow for changes to db temporary
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
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

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
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

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



    public List<MultiQuestion> getAllBooks() {
        List<MultiQuestion> books = new LinkedList<MultiQuestion>();

        // 1. build the query
        String query = "SELECT  * FROM " + "MULTI_QUESTION";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
       // Book book = null;
        if (cursor.moveToFirst()) {
            do {
                /*
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                */
                // Add book to books
               // books.add(book);
                //System.out.println(cursor.getString(1));
                Log.d("CURSOR 1 PRINT : ", cursor.getString(1));
            } while (cursor.moveToNext());
        }

        //Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }

    public void read () {
        List t;
        t = getAllBooks();
    }

    public ArrayList<MultiQuestion> getAllQuestions() {
        ArrayList<MultiQuestion> question_list = new ArrayList<MultiQuestion>();
        String selectQuery = "SELECT  * FROM " + "MULTI_QUESTION";
        //database =this.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MultiQuestion question = new MultiQuestion();
                question.setId(cursor.getInt(0));
                question.setQuestion_text(cursor.getString(1));
                question.setCorrect_answer(cursor.getString(2));
                question.setcA(cursor.getString(3));
                question.setcB(cursor.getString(4));
                question.setcC(cursor.getString(5));
                question.setcD(cursor.getString(6));
                question_list.add(question);
            } while (cursor.moveToNext());
        }


        return question_list;
    }

}