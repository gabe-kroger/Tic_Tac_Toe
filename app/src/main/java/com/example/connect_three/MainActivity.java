package com.example.connect_three;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    boolean activeGame = true;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    public void appear (View view) {
        ImageView icon = (ImageView) view;
        int tappedIcon = Integer.parseInt(icon.getTag().toString());
        if (gameState[tappedIcon] == 2 && activeGame) {
            gameState[tappedIcon] = activePlayer;
            icon.setTranslationY(-1000f);
            if (activePlayer == 0) {
                icon.setImageResource(R.drawable.tic_tac_toe_o);
                activePlayer = 1;
            } else {
                icon.setImageResource(R.drawable.tic_tac_toe_x);
                activePlayer = 0;
            }
            icon.animate().translationYBy(1000f).rotation(360).setDuration(500);
            for(int[] w : winningPositions){
                if(gameState[w[0]] == gameState[w[1]] &&
                        gameState[w[1]] == gameState[w[2]] &&
                        gameState[w[0]] != 2){
                    String winner = "X's";
                    if(gameState[w[0]] == 0){
                        winner = "O's";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " Won");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    activeGame = false;
                } else{
                    boolean gameOver = true;
                    for(int g : gameState){
                        if(g == 2){gameOver = false;}
                    }
                    if(gameOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("The Cat Won");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }


            }

        }
    }

    public void playAgain(View view){
        activeGame = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}


