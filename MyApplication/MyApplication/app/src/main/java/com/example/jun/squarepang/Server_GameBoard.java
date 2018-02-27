package com.example.jun.squarepang;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.PrintWriter;

import static com.example.jun.squarepang.Server_GameMain.context;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_GameBoard extends View {

    Bitmap b_blue,b_red,b_yellow,b_green,b_purple;
    Bitmap b_t_blue,b_t_red,b_t_yellow,b_t_green,b_t_purple;
    Bitmap win,lose,draw;
    Bitmap endimg,itembox,item0a,item0ia,item1a,item2a,item1ia,item2ia;
    Server_Map_info map_info;
    Server_GameTimer timer;
    Server_Game_Touch touchListener;
    PrintWriter pw;
    Context context;
    Activity a;
    public Server_GameBoard(Context context,Server_Map_info map_info,PrintWriter pw,Activity a){
        super(context);
        this.context = context;
        this.pw=pw;
        this.a=a;
        Resources r = getResources();


        win = BitmapFactory.decodeResource(r,R.drawable.win);
        lose = BitmapFactory.decodeResource(r,R.drawable.lose);
        draw = BitmapFactory.decodeResource(r,R.drawable.draw);
        b_blue= BitmapFactory.decodeResource(r,R.drawable.block_blue);
        b_red= BitmapFactory.decodeResource(r,R.drawable.block_red);
        b_yellow= BitmapFactory.decodeResource(r,R.drawable.block_yellow);
        b_green= BitmapFactory.decodeResource(r,R.drawable.block_green);
        b_purple= BitmapFactory.decodeResource(r,R.drawable.block_purple);
        b_t_blue= BitmapFactory.decodeResource(r,R.drawable.block_blue_time);
        b_t_red= BitmapFactory.decodeResource(r,R.drawable.block_red_time);
        b_t_yellow= BitmapFactory.decodeResource(r,R.drawable.block_yellow_time);
        b_t_green= BitmapFactory.decodeResource(r,R.drawable.block_green_time);
        b_t_purple= BitmapFactory.decodeResource(r,R.drawable.block_purple_time);
        endimg = BitmapFactory.decodeResource(r,R.drawable.waitingend);
        itembox = BitmapFactory.decodeResource(r,R.drawable.itembox);
        item0a = BitmapFactory.decodeResource(r,R.drawable.item0act);
        item1a = BitmapFactory.decodeResource(r,R.drawable.item1act);
        item2a = BitmapFactory.decodeResource(r,R.drawable.item2act);
        item0ia = BitmapFactory.decodeResource(r,R.drawable.item0inact);
        item1ia = BitmapFactory.decodeResource(r,R.drawable.item1inact);
        item2ia = BitmapFactory.decodeResource(r,R.drawable.item2inact);
        this.map_info = map_info;


        touchListener = new Server_Game_Touch(this.pw,this.map_info,this);
        setOnTouchListener(touchListener);
        timer = new Server_GameTimer((Activity)context,this.map_info,this.pw,this);
        timer.taskStart();



    }



    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        setBackgroundColor(Color.parseColor("#DAE9F5"));
        ///////////////////////////////////////////////////////////////////////////////
        int a=map_info.ax,b=map_info.ay,c=map_info.bx,d=map_info.by;
        Rect rect = new Rect(a,b,c,d);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.FILL);

        for(int i=0;i<15;i++){ //내화면 그려줌.
            for(int j=0;j<8;j++){
                canvas.drawRect(rect,paint2);
                canvas.drawRect(rect,paint);
                switch (map_info.my_map_arr[i][j]){
                    case 1:
                        canvas.drawBitmap(b_red, null, rect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(b_yellow, null, rect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(b_green, null, rect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(b_blue, null, rect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(b_purple, null, rect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(b_t_red, null, rect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(b_t_yellow, null, rect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(b_t_green, null, rect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(b_t_blue, null, rect, null);
                        break;
                    case 10:
                        canvas.drawBitmap(b_t_purple, null, rect, null);
                        break;
                }
                a+=map_info.gap;c+=map_info.gap;
                rect.set(a,b,c,d);
            }
            b+=map_info.gap;d+=map_info.gap;
            a=map_info.ax;c=map_info.bx;
            rect.set(a,b,c,d);
        }
        /////////////////////////////////////////////////////////
        int ya=map_info.y_ax,yb=map_info.y_ay,yc=map_info.y_bx,yd=map_info.y_by;
        Rect y_rect = new Rect(ya,yb,yc,yd);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        for(int i=0;i<15;i++){ //상대화면 그려줌.
            for(int j=0;j<8;j++){
                canvas.drawRect(y_rect,paint2);
                canvas.drawRect(y_rect,paint);
                switch (map_info.your_map_arr[i][j]){
                    case 1:
                        canvas.drawBitmap(b_red, null, y_rect, null);
                        break;
                    case 2:
                        canvas.drawBitmap(b_yellow, null, y_rect, null);
                        break;
                    case 3:
                        canvas.drawBitmap(b_green, null, y_rect, null);
                        break;
                    case 4:
                        canvas.drawBitmap(b_blue, null, y_rect, null);
                        break;
                    case 5:
                        canvas.drawBitmap(b_purple, null, y_rect, null);
                        break;
                    case 6:
                        canvas.drawBitmap(b_t_red, null, y_rect, null);
                        break;
                    case 7:
                        canvas.drawBitmap(b_t_yellow, null, y_rect, null);
                        break;
                    case 8:
                        canvas.drawBitmap(b_t_green, null, y_rect, null);
                        break;
                    case 9:
                        canvas.drawBitmap(b_t_blue, null, y_rect, null);
                        break;
                    case 10:
                        canvas.drawBitmap(b_t_purple, null, y_rect, null);
                        break;
                }
                ya+=map_info.y_gap;yc+=map_info.y_gap;
                y_rect.set(ya,yb,yc,yd);
            }
            yb+=map_info.y_gap;yd+=map_info.y_gap;
            ya=map_info.y_ax;yc=map_info.y_bx;
            y_rect.set(ya,yb,yc,yd);
        }////////////////////////////////////////////////////////////////////////////////
        Rect item_rect = new Rect(700,575,940,1175);
        Rect item0= new Rect(710,595,920,775);
        Rect item1= new Rect(720,795,920,965);
        Rect item2= new Rect(720,985,920,1155);
        canvas.drawBitmap(itembox,null,item_rect,null);
        if(map_info.item0cnt==1)
            canvas.drawBitmap(item0a,null,item0,null);
        else
            canvas.drawBitmap(item0ia,null,item0,null);
        if(map_info.item1cnt==1)
            canvas.drawBitmap(item1a,null,item1,null);
        else
            canvas.drawBitmap(item1ia,null,item1,null);
        if(map_info.item2cnt==1)
            canvas.drawBitmap(item2a,null,item2,null);
        else
            canvas.drawBitmap(item2ia,null,item2,null);

        ////////////////////////맵그리기 이외 이벤트.
        if(map_info.endMygame==true){ //먼저 끝나고 상대방을 기다릴때 이미지 덮기
            Paint epaint = new Paint();
            epaint.setAlpha(180);
            Rect endrect = new Rect(50,50,650,1175);
            canvas.drawBitmap(endimg,null,endrect,epaint);
        }
        if(map_info.endYourgame==true){
            Paint epaint = new Paint();
            epaint.setAlpha(180);
            Rect endrect = new Rect(700,50,940,500);
            canvas.drawBitmap(endimg,null,endrect,epaint);
        }
        Log.d("몇번뜰지는 모르겠어","게임이 끝났을까? "+ map_info.endGame);

        if(map_info.endGame==true&&map_info.finishGame==false){
            Log.d("끝냄","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            int whatimage=0;
            if(map_info.whowin==map_info.player)
                whatimage=1;
            else if(map_info.whowin==3)
                whatimage=3;
            else
                whatimage=2;
            GameoverDialog dial = new GameoverDialog(context,whatimage,map_info.total_score,map_info.your_score,map_info.mywin,map_info.mydraw,map_info.mylose,this.a);
            dial.getWindow().setGravity(Gravity.CENTER);
            dial.show();
            map_info.finishGame=true;
            ////////////////////////////////////////////////////////////////////////////////
        }


}


}
