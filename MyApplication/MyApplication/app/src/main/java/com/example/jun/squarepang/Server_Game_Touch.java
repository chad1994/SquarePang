package com.example.jun.squarepang;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_Game_Touch implements View.OnTouchListener {
    Server_GameBoard board;
    PrintWriter pw;
    Server_Map_info map_info;
    Server_Game_Logic logic;
    SoundPool sound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    int block_sd,doublechance,plusscore,timeattack;
    int streamID;

    public Server_Game_Touch(PrintWriter pw, Server_Map_info map_info,Server_GameBoard board){
        this.pw=pw;
        this.map_info=map_info;
        logic = new Server_Game_Logic();
        this.board = board;
        block_sd = sound.load(board.context,R.raw.blockdeletesound,1);
        doublechance = sound.load(board.context,R.raw.doublechancesound,1);
        plusscore = sound.load(board.context,R.raw.plusscoresound,1);
        timeattack = sound.load(board.context,R.raw.timeattacksound,1);

    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int arr_A,arr_B;//클릭시 해당하는 배열 번호
        Point[] point = new Point[4];
        for(int i=0;i<4;i++)
            point[i]=new Point();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            /////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(x>map_info.ax&&x<map_info.ax+(map_info.gap*8)&&y>map_info.ay&&y<map_info.ay+(map_info.gap*15)){
                arr_A=((int)y-map_info.ay)/map_info.gap;
                arr_B=((int)x-map_info.ax)/map_info.gap;
                if(map_info.my_map_arr[arr_A][arr_B]==0){
                    Log.d("TAG@@@@","좌표에 해당하는 배열은 ["+arr_A+"]["+arr_B+"] 입니다.");
                    map_info.blockcheck=logic.check_Block(map_info.my_map_arr,arr_A,arr_B,point); // 사방의 블록 색과 좌표을 체크.
                    Log.d("Tag@@@","상:"+map_info.blockcheck[0]+" 하:"+map_info.blockcheck[1]+" 좌:"+map_info.blockcheck[2]+" 우:"+map_info.blockcheck[3]);
                    Log.d("TAG좌표","상:"+point[0].x+","+point[0].y+" 하:"+point[1].x+","+point[1].y+" 좌:"+point[2].x+","+point[2].y+" 우:"+point[3].x+","+point[3].y);
//                    HashMap<Integer,ArrayList<Point>> color_check = new HashMap<Integer,ArrayList<Point>>();
                    logic.delete_Block(point,map_info); //삭제될 블록의 인덱스 값을 저장시키고,0으로 바꿈.
                    for(int i=1;i<=5;i++)
                        logic.timeItemCount[i]=0;

                    ////삭제되고 수정된 맵 정보를 보내줌.
                    HashMap<Object, Object> map = new HashMap<Object,Object>();
                    map.put("player",map_info.player);
                    Log.d("나는 누구인가","나는 : "+map_info.player+"이다.");
                    if(map_info.player==1) {
                        map.put("p1_map", map_info.my_map_arr);
                        Log.d("클릭후 맵데이터 보냄",""+map.get("p1_map"));
                        sendAll(map);
                        Log.d("보낸 값 확인!!",""+map);
                    }
                    else if(map_info.player==2) {
                        map.put("p2_map", map_info.my_map_arr);
                        Log.d("클릭후 맵데이터 보냄",""+map.get("p2_map"));
                        sendAll(map);
                        Log.d("보낸 값 확인!!",""+map);
                    }


                    map_info.total_score+=map_info.plus_score;
                    if(map_info.plus_score!=0){
                        sound.play(block_sd,1.0f,1.0f,1,0,1.0f);
                    }
                    Server_GameMain.st.setText(""+map_info.total_score);
                    map_info.plus_score=0;



                    board.invalidate();
                    map_info.delete_block.clear(); //삭제시킨 블록 인덱스 정보를 초기화시킴.

                }
            }///////////////////////////////////////////////////////////////////////////
            if(x>710&&x<920&&y>595&&y<775){//item0 눌렀을때
                if(map_info.item0cnt==1){
                    sound.play(timeattack,1.0f,1.0f,1,0,1.0f);
                    map_info.item0cnt=0;
                    HashMap<Object, Object> map = new HashMap<Object,Object>();
                    if(map_info.player==1)
                        map.put("player1_item0",true);
                    else if(map_info.player==2)
                        map.put("player2_item0",true);
                    sendAll(map);
                    board.invalidate();
                }
            }
            if(x>720&&x<920&&y>795&&y<965){//item1 눌렀을때
                if(map_info.item1cnt==1){
                    sound.play(doublechance,1.0f,1.0f,1,0,1.0f);
                    map_info.item1cnt=0;
                    map_info.item1act=true;
                    board.invalidate();
                }
            }
            if(x>720&&x<920&&y>985&&y<1155){//item2 눌렀을때
                if(map_info.game_time>1){
                    sound.play(plusscore,1.0f,1.0f,1,0,1.0f);
                    map_info.game_time-=1;
                    map_info.total_score+=50;
                    Server_GameMain.st.setText(""+map_info.total_score);
                    board.invalidate();
                }
            }
        }

        return false;
    }
    private void sendAll(HashMap<Object, Object> map) {
        Gson gson = new Gson();
        String result = gson.toJson(map);
            pw.println(result);
            pw.flush();
            System.out.println("보냄");
    }
}
