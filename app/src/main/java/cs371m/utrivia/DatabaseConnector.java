package cs371m.utrivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseConnector extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "test_db";

    private static final String TABLE_NAME = "quest";

    private static final String ID = "id";
    private static String QTEXT = "question";
    private static String ANSWER = "answer";
    private static String CHOICE_A = "A";
    private static String CHOICE_B = "B";
    private static String CHOICE_C = "C";
    private static String CHOICE_D = "D";
    private static ArrayList<String> choice_list = new ArrayList<String>();
    private Context context;
    private SQLiteDatabase database;
    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        database =db;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        String sql = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QTEXT
                + " TEXT, " + ANSWER + " TEXT, "+ CHOICE_A +" TEXT, "
                +CHOICE_B +" TEXT, "+ CHOICE_C+" TEXT, "  +CHOICE_D +" TEXT)";
        db.execSQL(sql);
        addQuestionsToDB();
    }

    private void addQuestionsToDB()
    {
        choice_list = new ArrayList<>();
        choice_list.add("Bill Moyers");
        choice_list.add("Roger Clemens");
        choice_list.add("Hans Mark");
        choice_list.add("Harley Clark");
        MultiQuestion q1=new MultiQuestion("Who created the famous Hook 'em Horns sign?", "Harley Clark", choice_list);
        this.addQuestion(q1);

        choice_list = new ArrayList<>();
        choice_list.add("Buffy");
        choice_list.add("Reveille");
        choice_list.add("Bevo");
        choice_list.add("Pig");
        MultiQuestion q2=new MultiQuestion("First mascot for UT was a dog named?", "Pig", choice_list);
        this.addQuestion(q2);

        choice_list = new ArrayList<>();
        choice_list.add("26-12");
        choice_list.add("15-17");
        choice_list.add("41-38");
        choice_list.add("32-39");
        MultiQuestion q3=new MultiQuestion("In the 2006 Rose Bowl between UT and USC what was the final score?", "41-38", choice_list);
        this.addQuestion(q3);

        choice_list = new ArrayList<>();
        choice_list.add("1916");
        choice_list.add("1866");
        choice_list.add("1887");
        choice_list.add("1883");
        MultiQuestion q4=new MultiQuestion("The University of Texas at Austin was founded in what year?","1883",choice_list);
        this.addQuestion(q4);

        choice_list = new ArrayList<>();
        choice_list.add("Memorial Stadium");
        choice_list.add("Darrell K. Royal Texas Memorial Stadium");
        choice_list.add("The Rosebowl");
        choice_list.add("Kyle Field");
        MultiQuestion q5=new MultiQuestion("In 1997, Texas Memorial Stadium was renamed  to the?","Darrell K. Royal Texas Memorial Stadium", choice_list);
        this.addQuestion(q5);

        choice_list = new ArrayList<>();
        choice_list.add("Walter S. Hunnicutt");
        choice_list.add("Richard Simmons");
        choice_list.add("Col. George Hurt");
        choice_list.add("Vince Young");
        MultiQuestion q6=new MultiQuestion("The song Texas Figt is writting by?", "Walter S. Hunnicutt", choice_list);
        this.addQuestion(q6);

        choice_list = new ArrayList<>();
        choice_list.add("127");
        choice_list.add("130");
        choice_list.add("95");
        choice_list.add("107");
        MultiQuestion q7=new MultiQuestion("How many Olympic medals has UT alumni & students won?", "130", choice_list);
        this.addQuestion(q7);

        choice_list = new ArrayList<>();
        choice_list.add("37");
        choice_list.add("40");
        choice_list.add("45");
        choice_list.add("35");
        MultiQuestion q8=new MultiQuestion("a nominee for the Outstading Young Texas Ex award be must be younger ", "40", choice_list);
        this.addQuestion(q8);

        choice_list = new ArrayList<>();
        choice_list.add("Red River Rivalry");
        choice_list.add("Texas vs A&M");
        choice_list.add("Cotton Bowl");
        choice_list.add("BIG 12 championship game");
        MultiQuestion q9=new MultiQuestion("Smokey the cannon was created in response to shotgun blasts often heard from where? ", "Red River Rivalry", choice_list);
        this.addQuestion(q9);

        choice_list = new ArrayList<>();
        choice_list.add("Red River Rivalry");
        choice_list.add("Texas vs A&M");
        choice_list.add("Cotton Bowl");
        choice_list.add("BIG 12 championship game");
        MultiQuestion q10=new MultiQuestion("Smokey the cannon was created in response to shotgun blasts often heard from where? ", "Red River Rivalry", choice_list);
        this.addQuestion(q10);

        choice_list = new ArrayList<>();
        choice_list.add("Accounting");
        choice_list.add("Computer Science");
        choice_list.add("Mechanical Engineering");
        choice_list.add("Finance");
        MultiQuestion q11=new MultiQuestion("UT is #1 in the US for what major?", "Accounting", choice_list);
        this.addQuestion(q11);

        choice_list = new ArrayList<>();
        choice_list.add("1972");
        choice_list.add("2007");
        choice_list.add("2002");
        choice_list.add("1998");
        MultiQuestion q12=new MultiQuestion("Ernest Allen Emerson won the Turing award in what year?", "2007", choice_list);
        this.addQuestion(q12);

        choice_list = new ArrayList<>();
        choice_list.add("Sweetheart of the Longhorn Band");
        choice_list.add("Big Bass Drum");
        choice_list.add("The Heart of Texas");
        choice_list.add("Bertha Howitzer");
        MultiQuestion q13=new MultiQuestion(" ", "Sweetheart of the Longhorn Band", choice_list);
        this.addQuestion(q13);

        choice_list = new ArrayList<>();
        choice_list.add("You'll ace you're next exam");
        choice_list.add("You'll ave nightmares for a week");
        choice_list.add("Nothing. What is the Albino Squirrel");
        choice_list.add("You need to report it to animal Services");
        MultiQuestion q14=new MultiQuestion("What happens when you spot the famous Albino Squirrel", "You'll ace you're next exam", choice_list);
        this.addQuestion(q14);

        choice_list = new ArrayList<>();
        choice_list.add("WWI");
        choice_list.add("WWII");
        choice_list.add("the Vietnam War");
        choice_list.add("the UT Tower shooting");
        MultiQuestion q15=new MultiQuestion("The Littlefield fountain was built to commemorate UT students and alumni who died during ____ ?", "WWI", choice_list);
        this.addQuestion(q15);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    public void addQuestion(MultiQuestion quest) {

        ContentValues values = new ContentValues();
        values.put(QTEXT, quest.getQuestion_text());
        values.put(ANSWER, quest.getCorrect_answer());
        values.put(CHOICE_A, quest.getcA());
        values.put(CHOICE_B, quest.getcB());
        values.put(CHOICE_C, quest.getcC());
        values.put(CHOICE_D, quest.getcD());
        database.insert(TABLE_NAME, null, values);
    }
    public ArrayList<MultiQuestion> getAllQuestions() {
        ArrayList<MultiQuestion> question_list = new ArrayList<MultiQuestion>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        database =this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
      
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

