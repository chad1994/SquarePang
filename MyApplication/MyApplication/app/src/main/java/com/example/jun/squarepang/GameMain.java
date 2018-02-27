package com.example.jun.squarepang;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jun on 2017-11-07.
 */

public class GameMain extends AppCompatActivity {

    LinearLayout ll;
    static TextView tt,st;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        st=(TextView)findViewById(R.id.score_text);
        tt=(TextView)findViewById(R.id.time_text);
        ll=(LinearLayout)findViewById(R.id.linear);


        GameBoard gameBoard = new GameBoard(this);
        gameBoard.setDrawingCacheEnabled(true);
//        Bitmap bitmap = gameBoard.getDrawingCache();
//        bitmap = BitmapFactory.
        ll.addView(gameBoard);

    }

    @Override
    public void onBackPressed() {
        onBackPressed();
    }
}
