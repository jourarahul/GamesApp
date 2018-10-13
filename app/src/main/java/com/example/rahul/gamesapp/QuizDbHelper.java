package com.example.rahul.gamesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.rahul.gamesapp.QuizContratct.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul on 9/30/2018.
 */

public class QuizDbHelper extends SQLiteOpenHelper {
    private static QuizDbHelper instance;
    private static final String DATABASE_NAME="MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION=1;
    private SQLiteDatabase db;
    private QuizDbHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context)
    {
        if(instance==null)
        {
            instance= new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        final String SQL_CREATE_CATEGORIES_TABLE= "CREATE TABLE "+
                CategoriesTable.TABLE_NAME+"(" +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTION_TABLE= "CREATE TABLE " +
                QuestionsTable.TABLE_NAME+"(" +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" +"ON DELETE CASCADE" +
                ")" ;
        try {
            db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
            db.execSQL(SQL_CREATE_QUESTION_TABLE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        fillCategoriesTable();
        fillQuestionTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.execSQL(" DROP TABLE IF EXISTS "+QuestionsTable.TABLE_NAME);
        onCreate(db);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable()
    {
        Category c1=new Category("Programming");
        addCategory(c1);
        Category c2=new Category("Geography");
        addCategory(c2);
        Category c3=new Category("Math");
        addCategory(c3);
    }

    private void addCategory(Category category)
    {
        ContentValues cv=new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME,category.getName());
        db.insert(CategoriesTable.TABLE_NAME,null,cv);
    }

    private void fillQuestionTable() {
        Question question1=new Question("Programming Easy :A is correct","A","B","C","D",1,
                Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        addQuestion(question1);
        Question question2=new Question("Geography Medium :B is correct","A","B","C","D",2,
                Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        addQuestion(question2);
        Question question3=new Question("Math Hard :C is correct","A","B","C","D",3,
                Question.DIFFICULTY_EASY,Category.MATH);
        addQuestion(question3);
        Question question4=new Question("Math Easy :A is correct","A","B","C","D",1,
                Question.DIFFICULTY_EASY,Category.MATH);
        addQuestion(question4);
        Question question5=new Question("Non Existing, Easy :A is correct","A","B","C","D",1,
                Question.DIFFICULTY_EASY,4);
        addQuestion(question5);
        Question question6=new Question("Non Existing, Medium :B is correct","A","B","C","D",1,
                Question.DIFFICULTY_EASY,5);
        addQuestion(question6);
    }

    private void addQuestion(Question question)
    {
     ContentValues cv=new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestions());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY,question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID,question.getCategoryId());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Category> getAllCategories()
    {
        List<Category> categoryList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+CategoriesTable.TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do{
               Category category=new Category();
               category.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
               category.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
               categoryList.add(category);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;

    }

    public ArrayList<Question> getAllQuestion()
    {
        ArrayList<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+QuestionsTable.TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do{
                Question question=new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestions(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }




    public ArrayList<Question> getQuestion(int categoryId, String difficulty)
    {
        ArrayList<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();
        String selection= QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs=new String[]{String.valueOf(categoryId),difficulty};

        Cursor c= db.query(QuestionsTable.TABLE_NAME
                                ,null,selection,selectionArgs,
                                null,
                                null,
                    null);

        if(c.moveToFirst())
        {
            do{
                Question question=new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestions(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
