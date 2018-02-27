package com.example.jun.squarepang;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_Map_info {
    int my_map_arr[][] = new int[][]{};
    int your_map_arr[][]= new int[][]{};

    ArrayList<Point> delete_block = new ArrayList<Point>();
    int plus_score=0;
    int total_score=0;
    int your_score=0;
    int game_time = 30;
    int game_state = 0;
    int player =0;
    boolean endMygame =false; //내 게임 끝남.
    boolean endYourgame =false;// 상대 게임 끝남.
    boolean endGame=false; // 전체 게임 끝남.
    boolean finishGame=false;
    String yourname;
    int whowin;
    int howManyTimeItem=0;
    int mywin,mydraw,mylose;
    int item0cnt=1,item1cnt=1,item2cnt=1;
    boolean item1act=false;



    static int gap=75,e_gap=30;
    //    static int ax=50,ay=350,bx=125,by=425;
    static int ax=50,ay=50,bx=125,by=125;

    static int y_gap=30; //상대화면 위치 값.
    static int y_ax=700,y_ay=50,y_bx=730,y_by=80;

    int blockcheck[]=new int[4]; //상하좌우 블록 색상 체크.
}
