package com.mycompany.scarnesdice;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    public  int  p1score,p2score,turnscore,p;
    public  TextView p1text,p2text,turnscoretext,turntext;
    public ImageView img;
     public Button roll,hold,reset;
    Random rand=new Random();
    int r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        p1text=(TextView)findViewById(R.id.p1score);
        p2text=(TextView)findViewById(R.id.p2score);
        turnscoretext=(TextView)findViewById(R.id.turnscore);
        turntext=(TextView)findViewById(R.id.turn);
        //ImageView Declaration
        img=(ImageView)findViewById(R.id.imageView);
        //Button Declaration
        roll=(Button)findViewById(R.id.rollbutton);
        hold=(Button)findViewById(R.id.holdbutton);
        reset=(Button)findViewById(R.id.resetbutton);
        //Playing first for player 1
        p=1;
        //setting scores to 0
        p1score=0;
        p2score=0;
        turnscore=0;

        Toast.makeText(getApplicationContext(),"Player 1's turn  ",Toast.LENGTH_LONG).show();
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               r=rand.nextInt(6)+1;
                updateUI(r);
                update_TextViews();

                if(r==1)
                {
                    ChangePlayer(p);
                    turnscore=0;

                }
                else
                {
                    turnscore+=r;
                    turnscoretext.setText("Turn Score:"+turnscore);
                }
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p==1){
                    p1score+=turnscore;
                    turnscore=0;
                    update_TextViews();

                    if(p1score>100)
                    {
                        Toast.makeText(MainActivity.this,"Player 1 Wins",Toast.LENGTH_LONG).show();
                        roll.setEnabled(false);
                        hold.setEnabled(false);

                    }
                    ChangePlayer(p);
                }
                else if(p==2){
                    p2score+=turnscore;
                    turnscore=0;
                    update_TextViews();

                    if(p2score>100)
                    {
                        Toast.makeText(MainActivity.this,"Player 2 Wins",Toast.LENGTH_LONG).show();
                        roll.setEnabled(false);
                        hold.setEnabled(false);

                    }
                    ChangePlayer(p);
                }


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1score=0;
                p2score=0;
                turnscore=0;
                p=1;
                roll.setEnabled(true);
                hold.setEnabled(true);
                updateUI(1);
                update_TextViews();

            }
        });

    }
    //simply changes the image in the image view
    public void updateUI(int i)
    {
        if(i==1)
        {
            img.setImageResource(R.drawable.dice1);
        }
        else if(i==2)
        {
            img.setImageResource(R.drawable.dice2);
        }
        else if(i==3)
        {
            img.setImageResource(R.drawable.dice3);
        }
        else if(i==4)
        {
            img.setImageResource(R.drawable.dice4);
        }
        else if(i==5)
        {
            img.setImageResource(R.drawable.dice5);
        }
        else if(i==6)
        {
            img.setImageResource(R.drawable.dice6);
        }
    }
    //Changes player flag
    public void ChangePlayer(int i)
    {

        p=3-i;
        Toast.makeText(getApplicationContext(),"Player  "+p+"'s turn",Toast.LENGTH_LONG).show();
    }
    public void update_TextViews()
    {
        p1text.setText("Player 1 Score="+p1score);
        p2text.setText("Player 2 Score="+p2score);
        turnscoretext.setText("Turn Score="+turnscore);
        turntext.setText("Player "+p+"'s Turn ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
