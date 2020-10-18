package com.example.zeroandkata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    // 0 for circle
    int[] playerState={2,2,2,2,2,2,2,2,2};
    boolean gameState = true;
    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        int tagNumber=Integer.parseInt(counter.getTag().toString());
        if(playerState[tagNumber]==2 && gameState==true){
            playerState[tagNumber]=activePlayer;
        if(activePlayer==0){
        counter.setImageResource(R.drawable.circle);
        activePlayer=1;
        }
        else{
            counter.setImageResource(R.drawable.cross);
            activePlayer=0;
        }}
        else{
            Toast.makeText(this, "AAB NOOB CHEATING KAREGA KYA?", Toast.LENGTH_SHORT).show();
        }
        counter.animate().translationYBy(1000f).setDuration(500);
        for(int[] winningPosi:winningPosition){
            if(playerState[winningPosi[0]]==playerState[winningPosi[1]] &&
               playerState[winningPosi[1]]==playerState[winningPosi[2]] &&
               playerState[winningPosi[0]]!=2){
                gameState=false;
                System.out.println(playerState[winningPosi[0]]);
                String winner="cross";
                if(playerState[winningPosi[0]]==0)
                    winner="circle";
                TextView winnerMessage=(TextView) findViewById(R.id.result);
                winnerMessage.setText(winner+" has won!");
                LinearLayout resultLayout=(LinearLayout) findViewById(R.id.layoutId);
                resultLayout.setVisibility(View.VISIBLE);
                resultLayout.animate().rotationYBy(1800).setDuration(1000);
            }
            else{
                boolean gameEnd = true;
                for (int counterForDraw : playerState){
                    if(counterForDraw==2){
                        gameEnd=false;
                    }
                }
                if(gameEnd){
                    TextView ItsDraw=(TextView) findViewById(R.id.pubg);
                    ItsDraw.setText("DONO PLAYER HI NOOB HAI");
                    TextView winnerMessage=(TextView) findViewById(R.id.result);
                    winnerMessage.setText("Its draw");
                    LinearLayout resultLayout=(LinearLayout) findViewById(R.id.layoutId);
                    resultLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        LinearLayout resultLayout=(LinearLayout) findViewById(R.id.layoutId);
        resultLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        gameState=true;
        for(int i=0;i<playerState.length;i++){
            playerState[i]=2;
        }
        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int j=0;j<gridlayout.getChildCount();j++){
            ((ImageView) gridlayout.getChildAt(j)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
