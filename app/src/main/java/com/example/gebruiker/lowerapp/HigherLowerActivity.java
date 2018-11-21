package com.example.gebruiker.lowerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HigherLowerActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mResult;
    private TextView mHighscore;
    private TextView mScore;
    private ListView mListView;
    private List<String> results;
    private ImageButton mLowerButton;
    private ImageButton mHigherButton;
    protected int oldValue;
    public int newValue;
    public int score = 0;
    public int highScore = 0;

    private static final int MAX_VALUE = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_lower);
        this.mResult = (TextView) findViewById(R.id.mResulttxt);
        this.mHighscore = (TextView) findViewById(R.id.mHighScoretxt);
        this.mScore = (TextView) findViewById(R.id.mScoretxt);
        this.mResult = (TextView) findViewById(R.id.mResulttxt);
        this.mListView = (ListView) findViewById(R.id.mListView);

        this.newValue = this.createNewValue();
        this.oldValue = this.newValue;
        this.mImageView= (ImageView) findViewById(R.id.mImageView);
        this.results = new ArrayList<String>();
        this.switchImage();


        this.mLowerButton = (ImageButton) findViewById(R.id.mLowerButton);
        this.mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newValue = createNewValue();
                String message;
                if (newValue >= oldValue ){
                    lose();
                    message = "Throw is "+newValue+". You lost";
                }else {
                    win();
                    message = "Throw is "+newValue+". You lost";
                }

                if (results.size() == 4){
                    results.remove(0);
                }
                results.add(message);
                updateList();

                oldValue = newValue;
                switchImage();


            }
        });

        this.mHigherButton = (ImageButton) findViewById(R.id.mHigherButton);
        this.mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newValue = createNewValue();
                String message;
                if (newValue <= oldValue ){
                    lose();
                    message = "Throw is "+newValue+". You lost";
                }else {
                    win();
                    message = "Throw is "+newValue+". You win";
                }

                if (results.size() == 4){
                    results.remove(0);
                }
                results.add(message);
                updateList();

                oldValue = newValue;
                switchImage();

            }
        });
    }

    public void win(){
        mResult.setText("Winner");
        this.score++;
        this.mScore.setText("Score: "+this.score);
    }

    public void lose(){
        mResult.setText("Loser");
        if (this.score > this.highScore) {
            this.highScore = this.score;
            this.mHighscore.setText("Highscore: "+this.highScore);

        }
        this.score = 0;
        this.mScore.setText("Score: "+this.score);

    }

    public int createNewValue(){
        int temp = (int)(Math.random() * MAX_VALUE);

        while(temp == this.oldValue){
            temp = (int)(Math.random() * MAX_VALUE);
        }
        return temp;
    }


    public void switchImage(){
        switch (this.oldValue){
            case(1):
                this.mImageView.setImageResource(R.drawable.d1);
                break;
            case(2):
                this.mImageView.setImageResource(R.drawable.d2);
                break;
            case(3):
                this.mImageView.setImageResource(R.drawable.d3);
                break;
            case(4):
                this.mImageView.setImageResource(R.drawable.d4);
                break;
            case(5):
                this.mImageView.setImageResource(R.drawable.d5);
                break;
            case(6):
                this.mImageView.setImageResource(R.drawable.d6);
                break;
        }
    }

    public void updateList(){
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, this.results);
        mListView.setAdapter(arrayAdapter);
    }
}
