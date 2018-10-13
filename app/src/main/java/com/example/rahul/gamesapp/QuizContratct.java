package com.example.rahul.gamesapp;

import android.provider.BaseColumns;

/**
 * Created by Rahul on 9/30/2018.
 */

public final class QuizContratct {

    private QuizContratct()
    {

    }

    public static class CategoriesTable implements BaseColumns
    {
        public static final String TABLE_NAME="quiz_categories";
        public static final String COLUMN_NAME="name";
    }


    public static class QuestionsTable implements BaseColumns{  //Base column for unique id of each question
        public static final String TABLE_NAME="quiz_questions";
        public static final String COLUMN_QUESTION="question";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_OPTION4="option4";
        public static final String COLUMN_ANSWER_NR="answer_nr";
        public static final String COLUMN_DIFFICULTY="difficulty";
        public static final String COLUMN_CATEGORY_ID="category_id";
    }
}
