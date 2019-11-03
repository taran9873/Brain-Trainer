package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers= new ArrayList<Integer>();
    Button goButton;
    Button option0,option1,option2,option3;
    Random rand;
    GridLayout grid;
    boolean istimeractive = false;
    int LocationOfCorrectAnswer;
    TextView questionTextView,timertextview;
    TextView scoreTextView,correct;
    int score=0;
    int totalquestion=0;
    Button playagain;

    public int generaterandom(){
        int RandomNumber;
        // random number between 0 to 50
        RandomNumber = rand.nextInt(51);
        return RandomNumber;
    }
    public void Start(View view){
        option0.setEnabled(true);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        goButton.setVisibility(View.INVISIBLE);
        grid.setAlpha(1);
        questionTextView.setVisibility(View.VISIBLE);
        final CountDownTimer timer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                istimeractive = true;
                timertextview.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }
            @Override
            public void onFinish() {
                // show play again button
                playagain.setAlpha(1);
                istimeractive = false;
                option0.setEnabled(false);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
            }
        }.start();
    }
    public void playAgain(View view){
        score = 0;
        totalquestion =0;
        timertextview.setText("30s");
        scoreTextView.setText("0/0");
        grid.setAlpha(0);
        goButton.setVisibility(View.VISIBLE);
        playagain.setAlpha(0);
        option0.setEnabled(true);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        correct.setText("");
        questionTextView.setVisibility(View.INVISIBLE);
        quiz();
    }
    public void ChooseAnswer(View view){
        Log.i("tag:",view.getTag().toString());
        totalquestion++;
        if(view.getTag().toString().equals(Integer.toString(LocationOfCorrectAnswer))){
            correct.setText("Correct!");

            score++;
        }
        else{
            correct.setText("Wrong :(");
        }
        correct.setAlpha(1);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalquestion));

        answers.clear();
        if(istimeractive){
            quiz();
        }


    }
    public void quiz(){
//        correct.setAlpha(0);
        rand = new Random();
//        questionTextView.setVisibility(View.INVISIBLE);
        int number1 = generaterandom();
        int number2 = generaterandom();
        questionTextView.setText(Integer.toString(number1)+" + "+Integer.toString(number2));
        LocationOfCorrectAnswer = rand.nextInt(4);
        for(int i=0;i<4;i++){
            if(i== LocationOfCorrectAnswer){
                answers.add(number1+number2);
            }
            else{
                // random number generated might be equal to a+b so we can't simply write this
                // answers.add(rand.nextInt(201));
                int wrongAnswer = rand.nextInt(101);
                while(wrongAnswer == number1+number2){
                    wrongAnswer = rand.nextInt(101);
                }
                answers.add(wrongAnswer);
            }
        }
        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         option0 = findViewById(R.id.option0);
         option1 = findViewById(R.id.option1);
         option2 = findViewById(R.id.option2);
         option3 = findViewById(R.id.option3);
        scoreTextView = findViewById(R.id.ScoreTextView);
        goButton = findViewById(R.id.goButton);
        questionTextView = findViewById(R.id.questionTextView);
        grid = findViewById(R.id.grid);
        correct = findViewById(R.id.correct);
        playagain = findViewById(R.id.PlayAgain);
        timertextview = findViewById(R.id.timerTextView);
        questionTextView.setVisibility(View.INVISIBLE);
        option0.setEnabled(false);
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        quiz();

    }
}
