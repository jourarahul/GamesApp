package com.example.rahul.gamesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rahul on 9/30/2018.
 */

public class Question implements Parcelable {

    public static final String DIFFICULTY_EASY="Easy";
    public static final String DIFFICULTY_MEDIUM="Medium";
    public static final String DIFFICULTY_HARD="Hard";

    private int id;
    private String questions;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNr;
    private String difficulty;
    private int categoryId;

    public Question()
    {

    }
    public Question(String questions, String option1, String option2, String option3, String option4, int answerNr,String difficulty,
                    int categoryId) {
        this.questions = questions;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.difficulty=difficulty;
        this.categoryId=categoryId;
    }

    protected Question(Parcel in) {
        id=in.readInt();
        questions = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNr = in.readInt();
        difficulty=in.readString();
        categoryId=in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }



    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(questions);
        parcel.writeString(option1);
        parcel.writeString(option2);
        parcel.writeString(option3);
        parcel.writeString(option4);
        parcel.writeInt(answerNr);
        parcel.writeString(difficulty);
        parcel.writeInt(categoryId);
    }



    public static String[] getAllDifficultyLevels()
    {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
}
