package com.example.sayan.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public int p1_score,p2_score,turn_score;

    public TextView p1_text,p2_text,turn_text,turn_score_text;
    public Button roll,hold,reset;
    public int p;
    public ImageView img;
    public int r;
    public Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        p1_text = (TextView)findViewById(R.id.p1_score);
        p2_text = (TextView)findViewById(R.id.p2_score);
        turn_text = (TextView)findViewById(R.id.turn);
        turn_score_text = (TextView)findViewById(R.id.turn_score_text);
        img = (ImageView)findViewById(R.id.imageView);

        roll = (Button) findViewById(R.id.buttonRoll);
        hold = (Button) findViewById(R.id.buttonHold);
        reset = (Button) findViewById(R.id.buttonReset);

        //Initalizing the game
        turn_score = 0;
        p1_score = 0;
        p2_score = 0;

        //Playing for the first player
        p = 1;

        Toast.makeText(MainActivity.this,"Player 1's turn",Toast.LENGTH_LONG).show();



    }
    public void clickRoll(View v){
        r = rand.nextInt(6) + 1;
        updateUI(r);
        update_TextViews();
        if(r == 1){
            changePlayer(p);
            turn_score = 0;
        }
        else{
            turn_score = turn_score + r;
            turn_score_text.setText("Turn Score : " + turn_score);
        }

    }
    public void clickHold(View v){
        if(p==1){
            p1_score = p1_score + turn_score;
            changePlayer(p);
            update_TextViews();
            if(p1_score > 50){
                Toast.makeText(MainActivity.this,"Player 1 Wins",Toast.LENGTH_LONG).show();
                roll.setEnabled(false);
                hold.setEnabled(false);

            }
        }
        else{
            p2_score = p2_score + turn_score;
            changePlayer(p);
            update_TextViews();
            if(p2_score > 50){
                Toast.makeText(MainActivity.this,"Player 2 Wins",Toast.LENGTH_LONG).show();
                roll.setEnabled(false);
                hold.setEnabled(false);

            }
        }
    }
    public void clickReset(View v){
        turn_score = 0;
        p1_score = 0;
        p2_score = 0;
        p = 1;
        roll.setEnabled(true);
        hold.setEnabled(true);
        updateUI(1);
        update_TextViews();

    }

    //updates the image of ImageView
    public void updateUI(int i){
        switch(i){
            case 1:img.setImageResource(R.drawable.dice1);
                break;
            case 2:img.setImageResource(R.drawable.dice2);
                break;
            case 3:img.setImageResource(R.drawable.dice3);
                break;
            case 4:img.setImageResource(R.drawable.dice4);
                break;
            case 5:img.setImageResource(R.drawable.dice5);
                break;
            case 6:img.setImageResource(R.drawable.dice6);
                break;
        }

    }

    public void changePlayer(int x){
        p = 3 - x;
        Toast.makeText(MainActivity.this,"Player "+p+"'s turn",Toast.LENGTH_LONG).show();
    }
    public void update_TextViews(){
        p1_text.setText("Player 1 score : "+p1_score);
        p2_text.setText("Player 2 score : "+p2_score);
        turn_score_text.setText("Turn score : "+turn_score);
        turn_text.setText("Payer "+p+" turn");
    }
}
