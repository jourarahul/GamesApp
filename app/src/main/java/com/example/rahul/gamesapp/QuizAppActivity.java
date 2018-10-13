package com.example.rahul.gamesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class QuizAppActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ=2;
    public static final String EXTRA_DIFFICULTY="extraDifficulty";
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";
    public static final String EXTRA_CATEGORY_ID="extraCategoryId";
    public static final String EXTRA_CATEGORY_NAME="extraCategoryName";
    private TextView textviewHighscore;
    private int highscore;
    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);
        textviewHighscore=findViewById(R.id.text_view_highscore);
        spinnerDifficulty=findViewById(R.id.spinner_difficulty);
        spinnerCategory=findViewById(R.id.spinner_category);
        loadCategories();
        loadDifficultyLevels();


        loadHighScore();
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
    }
    private void loadCategories()
    {
        QuizDbHelper quizDbHelper=QuizDbHelper.getInstance(this);
        List<Category> categories=quizDbHelper.getAllCategories();
        ArrayAdapter<Category> adapter=new ArrayAdapter<Category>(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void loadDifficultyLevels()
    {
        String[] difficultyLevels=Question.getAllDifficultyLevels();


        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDifficulty.setAdapter(adapterDifficulty);

    }
    private void startQuiz() {
        Category category= (Category) spinnerCategory.getSelectedItem();
        int id = category.getId();
        String categoryName=category.getName();
        String difficulty=spinnerDifficulty.getSelectedItem().toString();
        Intent intent=new Intent(this,QuizActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        intent.putExtra(EXTRA_CATEGORY_ID,id);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
//        startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_QUIZ && resultCode==RESULT_OK)
        {
            int score=data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
            if(score>highscore)
            {
                updateHighScore(score);
            }
        }
    }

    private void loadHighScore()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=sharedPreferences.getInt(KEY_HIGHSCORE,0);
        textviewHighscore.setText("HIGHSCORE :" +highscore);
    }

    private void updateHighScore(int highscoreNew) {
        highscore=highscoreNew;
        textviewHighscore.setText("HIGHSCORE :" +highscore);
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }
}
