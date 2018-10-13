package com.example.rahul.gamesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private Boolean player1Turn=true;
    private int roundCount;
    private int player1Ponits;
    private int player2Points;
    private TextView textviewPlayer1;
    private TextView textviewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        textviewPlayer1=findViewById(R.id.text_view_p1);
        textviewPlayer2=findViewById(R.id.text_view_p2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonId="button_"+i+j;
                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals(""))
        {
            return;
        }
        if(player1Turn)
        {
            ((Button)v).setText("X");
        }
        else {
            ((Button)v).setText("O");
        }

        roundCount++;
    }

    private boolean checkForWin()
    {
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]))
            {

            }
        }
        return true;
    }

}
