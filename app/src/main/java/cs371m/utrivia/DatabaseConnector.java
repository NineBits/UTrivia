package cs371m.utrivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "test1";

    private static final String TABLE_QUEST = "quest";

    private static final String ID = "id";
    private static final String QTEXT = "question";
    private static final String ANSWER = "answer";
    private static final String CHOICE_A = "A";
    private static final String CHOICE_B = "B";
    private static final String CHOICE_C = "C";
    private static final String CHOICE_D = "D";
    private SQLiteDatabase dbase;
    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        String sql = "CREATE TABLE " + TABLE_QUEST + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QTEXT
                + " TEXT, " + ANSWER+ " TEXT, "+ CHOICE_A +" TEXT, "
                +CHOICE_B +" TEXT, "+CHOICE_C+" TEXT, "  +CHOICE_D +" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        MultiQuestion q1=new MultiQuestion("Who created the famous Hook 'em Horns sign?", "Harley Clark", "Bill Moyers", "Roger Clemens","Hans Mark", "Harley Clark");
        this.addQuestion(q1);
        MultiQuestion q2=new MultiQuestion("First mascot for UT was a dog named?", "Reveille", " Pig", "Bevo", "Buffy","Pig");
        this.addQuestion(q2);
        MultiQuestion q3=new MultiQuestion("In the 2006 Rose Bowl between UT and USC what was the final score?", "26-12","15-17", "41-38","32-39", "41-38");
        this.addQuestion(q3);
        MultiQuestion q4=new MultiQuestion("The University of Texas at Austin was founded in what year?", "1916", "1866", "1887","1883", "1883");
        this.addQuestion(q4);
        MultiQuestion q5=new MultiQuestion("In 1997, Texas Memorial Stadium was renamed  to the?","Memorial Stadium","Darrell K. Royal Texas Memorial Stadium","The Rosebowl","Kyle Field","Darrell K. Royal Texas Memorial Stadium");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(MultiQuestion quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QTEXT, quest.getQuestion_text());
        values.put(ANSWER, quest.getCorrect_answer());
        values.put(CHOICE_A, quest.getcA());
        values.put(CHOICE_B, quest.getcB());
        values.put(CHOICE_C, quest.getcC());
        values.put(CHOICE_D, quest.getcD());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<MultiQuestion> getAllQuestions() {
        List<MultiQuestion> quesList = new ArrayList<MultiQuestion>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MultiQuestion quest = new MultiQuestion();
                quest.setId(cursor.getInt(0));
                quest.setQuestion_text(cursor.getString(1));
                quest.setCorrect_answer(cursor.getString(2));
                quest.setcA(cursor.getString(3));
                quest.setcB(cursor.getString(4));
                quest.setcC(cursor.getString(5));
                quest.setcD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
}

