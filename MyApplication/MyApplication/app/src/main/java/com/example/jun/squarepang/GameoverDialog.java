package com.example.jun.squarepang;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jun on 2017-11-26.
 */

public class GameoverDialog extends Dialog {

    static TextView winText;
    static TextView drawText;
    static TextView loseText;
    static TextView myscore;
    static TextView yourscore;
    static Button mButton;
    static ImageView dial_image;
    Context context;
    int whatimage,mwin,mdraw,mlose;
    int ms,ys;
    Activity a;
//    private View.OnClickListener mClickListener;

    public GameoverDialog(Context context,int whatimage,int ms,int ys,int mwin,int mdraw,int mlose,Activity a){
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;
        this.whatimage=whatimage;
        this.mwin=mwin;this.mdraw=mdraw;this.mlose=mlose;
        this.ms=ms;this.ys=ys;
        this.a = a;
    }

    private View.OnClickListener mclickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(context.getApplicationContext(), "게임이 끝났습니다.", Toast.LENGTH_SHORT).show();
            dismiss();
            Intent intent = new Intent(context.getApplicationContext(),Server_GameMain.class);
            a.startActivity(intent);
            a.finish();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.custom_dialog);

        dial_image = (ImageView)findViewById(R.id.dialog_image);
        winText = (TextView) findViewById(R.id.dialog_win);
        drawText =(TextView) findViewById(R.id.dialog_draw);
        loseText = (TextView) findViewById(R.id.dialog_lose);
        myscore = (TextView)findViewById(R.id.dialog_myscore);
        yourscore = (TextView)findViewById(R.id.dialog_yourscore);
        mButton = (Button) findViewById(R.id.dialog_btn);

        // 제목과 내용을 생성자에서 셋팅한다.
        if(whatimage==1) {
            dial_image.setImageResource(R.drawable.win);
        }else if(whatimage==3){
            dial_image.setImageResource(R.drawable.draw);
        }
        else{
            dial_image.setImageResource(R.drawable.lose);
        }
        winText.setText("승: "+mwin+"");drawText.setText("무: "+mdraw+"");loseText.setText("패: "+mlose+"");
        myscore.setText(ms+"");yourscore.setText(ys+"");


        // 클릭 이벤트 셋팅
        mButton.setOnClickListener(mclickListener);

    }
}
