package com.example.jun.squarepang;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jun on 2017-11-07.
 */

public class GameBoard extends View {
    Paint paint = new Paint();
    Bitmap b_blue,b_red,b_yellow,b_green,b_purple;
    Map_info map_info;
    GameLogic logic;
    GameTimer timer;

    static int gap=75,e_gap=30;
//    static int ax=50,ay=350,bx=125,by=425;
    static int ax=50,ay=50,bx=125,by=125;
    int blockcheck[]=new int[4]; //상하좌우 블록 색상 체크.
    public GameBoard(Context context){
        super(context);
        Resources r = getResources();
        b_blue= BitmapFactory.decodeResource(r,R.drawable.block_blue);
        b_red= BitmapFactory.decodeResource(r,R.drawable.block_red);
        b_yellow= BitmapFactory.decodeResource(r,R.drawable.block_yellow);
        b_green= BitmapFactory.decodeResource(r,R.drawable.block_green);
        b_purple= BitmapFactory.decodeResource(r,R.drawable.block_purple);
        map_info=new Map_info();
        logic= new GameLogic();

        timer = new GameTimer((Activity)context, map_info);
        timer.taskStart();

//        tt=(TextView)findViewById(R.id.time_text);
//        st=(TextView)findViewById(R.id.score_text);

    }



    protected void onDraw(Canvas canvas){
        int a=ax,b=ay,c=bx,d=by;
        Rect rect = new Rect(a,b,c,d);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        for(int i=0;i<15;i++){
            for(int j=0;j<8;j++){
                canvas.drawRect(rect,paint);
                switch (map_info.map_arr[i][j]){
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
                }
                a+=gap;c+=gap;
                rect.set(a,b,c,d);
            }
            b+=gap;d+=gap;
            a=ax;c=bx;
            rect.set(a,b,c,d);
        }


        //위치 테스트
//        for(int i=0;i<8;i++) {
//            rect.set(a,b,c,d);
//            canvas.drawBitmap(b_blue, null, rect, null);
//            a+=75;c+=75;
//        }
//
//        a=50;b=350;c=125;d=425;
//        for(int i=0;i<15;i++){
//            rect.set(a,b,c,d);
//            canvas.drawBitmap(b_blue, null, rect, null);
//            b+=75;d+=75;
//        }
//
//        a=750;b=50;c=780;d=80;
//        for(int i=0;i<8;i++){
//            rect.set(a,b,c,d);
//            canvas.drawBitmap(b_blue, null, rect, null);
//            a+=30;c+=30;
//        }
//
//        a=750;b=50;c=780;d=80;
//        for(int i=0;i<15;i++){
//            rect.set(a,b,c,d);
//            canvas.drawBitmap(b_blue, null, rect, null);
//            b+=30;d+=30;
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        int arr_A,arr_B;//클릭시 해당하는 배열 번호
        Point[] point = new Point[4];
        for(int i=0;i<4;i++)
            point[i]=new Point();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(x>ax&&x<ax+(gap*8)&&y>ay&&y<ay+(gap*15)){
                arr_A=((int)y-ay)/gap;
                arr_B=((int)x-ax)/gap;
                if(map_info.map_arr[arr_A][arr_B]==0){
                    Log.d("TAG@@@@","좌표에 해당하는 배열은 ["+arr_A+"]["+arr_B+"] 입니다.");
                    blockcheck=logic.check_Block(map_info.map_arr,arr_A,arr_B,point); // 사방의 블록 색과 좌표을 체크.
                    Log.d("Tag@@@","상:"+blockcheck[0]+" 하:"+blockcheck[1]+" 좌:"+blockcheck[2]+" 우:"+blockcheck[3]);
                    Log.d("TAG좌표","상:"+point[0].x+","+point[0].y+" 하:"+point[1].x+","+point[1].y+" 좌:"+point[2].x+","+point[2].y+" 우:"+point[3].x+","+point[3].y);
//                    HashMap<Integer,ArrayList<Point>> color_check = new HashMap<Integer,ArrayList<Point>>();
                    logic.delete_Block(point,map_info); //삭제될 블록의 인덱스 값을 저장시키고,0으로 바꿈.
//                    for(int i=0;i<map_info.delete_block.size();i++){
//                        Rect rect[] = new Rect[i];
//                        for(int j=0;j<rect.length;j++){
//                            rect[j]=new Rect();
//                            rect[j].set(50+(75*map_info.delete_block.get(j).x),350+(75*map_info.delete_block.get(j).y),50+(75*map_info.delete_block.get(j).x+1),350+(75*map_info.delete_block.get(j).y+1));
//                            invalidate(rect[j]);
//                        }
//                    }
                    map_info.total_score+=map_info.plus_score;
                    Log.d("TAGTAG@@@@",""+map_info.total_score+"//"+ map_info.plus_score);
                    GameMain.st.setText(""+map_info.total_score);
                    map_info.plus_score=0;

                    invalidate();
                    map_info.delete_block.clear(); //삭제시킨 블록 인덱스 정보를 초기화시킴.
                    /*
                    ArrayList<Point> point_list[] = new ArrayList[5];//사방의 색상 별 좌표 리스트.0빨강,1노랑,2초록,3파랑,4보라
                    for(int i=0;i<5;i++)
                        point_list[i] = new ArrayList<Point>();
                    for(int i=0;i<4;i++){ //해당 색의 배열에 배열인덱스값 넣기.
                        if(point[i].x!=-1&&point[i].y!=-1) {
                            switch (map_info.map_arr[point[i].x][point[i].y]) {
                                case 1:
                                    point_list[0].add(point[i]);
                                    break;
                                case 2:
                                    point_list[1].add(point[i]);
                                    break;
                                case 3:
                                    point_list[2].add(point[i]);
                                    break;
                                case 4:
                                    point_list[3].add(point[i]);
                                    break;
                                case 5:
                                    point_list[4].add(point[i]);
                                    break;
                            }
                        }
                    }
                    HashMap<Integer,ArrayList<Point>> color_list = new HashMap<Integer,ArrayList<Point>>();//사방의 색 별로 좌표리스트.
                    color_list.put(1,point_list[0]);
                    color_list.put(2,point_list[1]);
                    color_list.put(3,point_list[2]);
                    color_list.put(4,point_list[3]);
                    color_list.put(5,point_list[4]);

                    for(int i=1;i<=5;i++){
                        if(color_list.get(i).size()==4){
                            map_info.plus_score+=300;

                            for(int j=0;j<color_list.get(i).size();j++){
                                map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
//                                map_info.map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                            }
//                                map_info.delete_block.add()

                            break;
                        }
                        else if(color_list.get(i).size()==3){
                            map_info.plus_score+=100;

                            for(int j=0;j<color_list.get(i).size();j++){
                                map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
//                                map_info.map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                            }

                            break;
                        }
                        else if(color_list.get(i).size()==2){
                            if(map_info.plus_score==20){//두개짜리가 두개인경우
                                map_info.plus_score+=180;

                                for(int j=0;j<color_list.get(i).size();j++){
                                    map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
//                                    map_info.map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                                }
                                break;
                            }

                            else { //두개짜리가 하나인경우
                                map_info.plus_score += 20;

                                for(int j=0;j<color_list.get(i).size();j++){
                                    map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
//                                    map_info.map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                                }

                            }
                        }
                    }*/
//                    Log.d("DELETE",map_info.delete_block.toString());
//                    Log.d("DELETE",""+map_info.map_arr[map_info.delete_block.get(0).x][map_info.delete_block.get(0).y]);


                }
            }
        }

        return super.onTouchEvent(event);
    }

}
