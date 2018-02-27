package com.example.jun.squarepang;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Jun on 2017-11-17.
 */

public class ReceiveThread extends Thread {
    Socket socket;
    Server_Map_info model;
    PrintWriter writer;
    Context context;
    Activity a;
    Server_GameBoard board;

    public ReceiveThread(Socket socket, Server_Map_info model, PrintWriter writer, Activity a, Context context) {
        this.socket = socket;
        this.model = model;
        this.writer = writer;
        this.a = a;
        this.context=context;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String player = reader.readLine(); //서버로 부터 내가 몇번째 플레이어인지 확인 받음.
            player = player.substring(1, player.length() -1);
            String[] keyValuePairs = player.split(",");
            HashMap<Object, Object> map = new HashMap<Object, Object>();
                for(int i=0;i<keyValuePairs.length;i++){
                    String pair = keyValuePairs[i].trim();
                    String[] entry = pair.split("=");
                    map.put(entry[0],entry[1]);
                }
            model.player = Integer.parseInt(map.get("player").toString());
            Log.d("player", "im player : " + model.player);

            while (true) {
                StringBuilder str = new StringBuilder();
                str.append(reader.readLine());
                Log.d("str", "str : " + str.toString());


                Gson gson = new Gson();
                ServerMapData serverMapData = gson.fromJson(str.toString(),ServerMapData.class);
                Log.d("parsing", "map : " + serverMapData.getGamestate());
                model.game_state = serverMapData.getGamestate();
                if(model.player==1) {
                    model.my_map_arr = serverMapData.getP1_map();
                    model.your_map_arr = serverMapData.getP2_map();
                }
                else if(model.player==2) {
                    model.my_map_arr = serverMapData.getP2_map();
                    model.your_map_arr = serverMapData.getP1_map();
                }



                if(model.game_state==1){ //게임이 시작 됐을때 (플레이어가 2명 들어왔을 때!)
                    if(str.toString().contains("name")){
                        if(model.player==1){
                            model.yourname=serverMapData.getPlayer2name();
                        }
                        else if(model.player==2){
                            model.yourname=serverMapData.getPlayer1name();
                        }
                    }
                    ////////////////////////////////////////////////////////////
                    board = new Server_GameBoard(context,model,writer,a);
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Server_GameMain.waitingimg.setVisibility(View.GONE);
                                    Server_GameMain.mainboard.setVisibility(View.VISIBLE);
                                    Server_GameMain.mn.setText(User_info.name);
                                    Server_GameMain.yn.setText(model.yourname);
                                    Server_GameMain.ll.addView(board);
                                }
                            });
                    /////////////////////게임이 시작 됐음, 여기서 부터 이제 상대방의 클릭에 따라 서버에서 보내 주는 데이터를 받게됨.
                    while(true){
                        StringBuilder str2 = new StringBuilder();
                        str2.append(reader.readLine());
                        Log.d("str2", "str2 : " + str2.toString());
                        serverMapData = gson.fromJson(str2.toString(),ServerMapData.class);
                        ////////////////////////////////////////////////////////////////////
                        if(str2.toString().contains("_map")) {
                            if (model.player == 1 && serverMapData.getP2_map() != null) {
                                model.your_map_arr = serverMapData.getP2_map();
                                a.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            } else if (model.player == 2 && serverMapData.getP1_map() != null) {
                                model.your_map_arr = serverMapData.getP1_map();
                                Log.d("Tag11111", "" + serverMapData.getP1_map()[1][4]);
                                a.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            }
                        }
                        if(str2.toString().contains("endplayer")){
                            if(model.player == 1 && serverMapData.isEndplayer2()==true){
                                model.endYourgame=true;
                                Log.d("End check","들어왔어");
                                a.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            }
                            if(model.player == 2 && serverMapData.isEndplayer1()==true){
                                model.endYourgame=true;
                                a.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            }
                        }
                        if(str2.toString().contains("endgame")&&serverMapData.isEndgame()==true){
                            model.endGame = true;
                            model.whowin = serverMapData.getWhowin();
                            if(model.player==1) {
                                model.your_score = serverMapData.getPlayer2score();
                                model.mywin = serverMapData.getP1win();
                                model.mydraw = serverMapData.getP1draw();
                                model.mylose = serverMapData.getP1lose();
                            }
                            else if(model.player==2) {
                                model.your_score = serverMapData.getPlayer1score();
                                model.mywin = serverMapData.getP2win();
                                model.mydraw = serverMapData.getP2draw();
                                model.mylose = serverMapData.getP2lose();
                            }
                            a.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    board.invalidate();
                                }
                            });
                        }
                        if(str2.toString().contains("attacked")){
                            if(model.player==1&&serverMapData.isP1attacked()==true){
                                model.game_time-=3;
                                serverMapData.setP1attacked(false);
                            }
                            if(model.player==2&&serverMapData.isP2attacked()==true){
                                model.game_time-=3;
                                serverMapData.setP2attacked(false);
                            }
                        }

                    }
                }
                else{
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
