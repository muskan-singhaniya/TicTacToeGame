package com.muskan.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public ImageView counterImage1;
    public ImageView counterImage2;
    public ImageView counterImage3;
    public ImageView counterImage4;
    public ImageView counterImage5;
    public ImageView counterImage6;
    public ImageView counterImage7;
    public ImageView counterImage8;
    public ImageView counterImage9;

    public Button playAgainButton;


    public int activePlayer;  //0 - red, 1 - yellow
    public boolean gameIsActive = true;
    public int selectedCounter;
    public int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 - unplayed

    public int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGameData();
        initViews();
        initListeners();
        setData();
    }

    public void initGameData() {
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        gameIsActive = true;
        message = "";

        GridLayout gridLayout = (GridLayout) findViewById(R.id.board);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    private void initListeners() {
        counterImage1.setOnClickListener(listener);
        counterImage2.setOnClickListener(listener);
        counterImage3.setOnClickListener(listener);
        counterImage4.setOnClickListener(listener);
        counterImage5.setOnClickListener(listener);
        counterImage6.setOnClickListener(listener);
        counterImage7.setOnClickListener(listener);
        counterImage8.setOnClickListener(listener);
        counterImage9.setOnClickListener(listener);

        playAgainButton.setOnClickListener(listener2);

    }

    private void initViews() {
        counterImage1 = (ImageView) findViewById(R.id.counter1);
        counterImage2 = findViewById(R.id.counter2);
        counterImage3 = findViewById(R.id.counter3);
        counterImage4 = findViewById(R.id.counter4);
        counterImage5 = findViewById(R.id.counter5);
        counterImage6 = findViewById(R.id.counter6);
        counterImage7 = findViewById(R.id.counter7);
        counterImage8 = findViewById(R.id.counter8);
        counterImage9 = findViewById(R.id.counter9);
        playAgainButton = findViewById(R.id.play_again);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (gameIsActive) {
                selectedCounter = Integer.parseInt(view.getTag().toString()) - 1;
                if (gameState[selectedCounter] == 2) {
                    gameState[selectedCounter] = activePlayer;
                    beforeAnimation((ImageView) view);
                    if (activePlayer == 1) setImageYellow((ImageView) view);
                    else setImageRed((ImageView) view);
                    afterAnimation((ImageView) view);

                    checkWinner();
                }
            }
        }
    };

    View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initGameData();
        }
    };

    public void checkWinner() {
        for (int[] winner : winningPositions) {
            Log.d("muskan","for loop running");
            if (gameState[winner[0]] == gameState[winner[1]] && gameState[winner[1]] == gameState[winner[2]] && gameState[winner[1]] != 2) {
                Log.d("muskan","if condition satisfied");
                if(winner[0] == 1) message = "Yellow Wins!!";
                else message = "Red Wins!!";
                gameIsActive = false;
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setImageYellow(ImageView imgView) {
        imgView.setImageResource(R.drawable.yellow);
        activePlayer = 0;
    }

    public void setImageRed(ImageView imgView) {
        imgView.setImageResource(R.drawable.red);
        activePlayer = 1;
    }

    public void afterAnimation(ImageView imgView) {
        imgView.animate().translationXBy(1000f).translationYBy(1000f).rotationXBy(1080f).rotationYBy(1080f).setDuration(300);
    }

    public void beforeAnimation(ImageView imgView) {
        imgView.setTranslationX(-1000f);
        imgView.setTranslationY(-1000f);
    }

    private void setData() {
    }
}