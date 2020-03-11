package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    RelativeLayout layout;
    GridLayout gridLayout;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void invisible(){

        new CountDownTimer(1000, 1000){


            @Override
            public void onTick(long millisUntilFinished) {
                resultTextView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFinish() {
                resultTextView.setText("");
            }
        }.start();
    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        answers.clear();

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add((a+b));
            }
            else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b ){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString((answers.get(0))));
        button1.setText(Integer.toString((answers.get(1))));
        button2.setText(Integer.toString((answers.get(2))));
        button3.setText(Integer.toString((answers.get(3))));


    }

    public void start(View view){

       // startButton.setVisibility(View.VISIBLE);

        layout = (RelativeLayout) findViewById(R.id.layout);
        layout.setVisibility(RelativeLayout.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);

        //timer

        playAgain(findViewById(R.id.playAgainButton));

    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
            invisible();
        }
        else{
            resultTextView.setText("Incorrect!");
            invisible();
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();

    }

    public void playAgain(View view){


        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        for(int i =0; i< gridLayout.getChildCount(); i++){
            View view1 = gridLayout.getChildAt(i);
            view1.setEnabled(true);
        }

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                for(int i =0; i< gridLayout.getChildCount(); i++){
                    View view = gridLayout.getChildAt(i);
                    view.setEnabled(false);
                }
               // gridLayout.setEnabled(false);


            }
        }.start();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

    }
}
