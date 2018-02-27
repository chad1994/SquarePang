package com.example.jun.squarepang;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_GameTimer {
    Server_Map_info map_info;
    TimerTask timer;
    Activity activity;
    PrintWriter pw;
    Server_GameBoard board;

    public Server_GameTimer(Activity activity, Server_Map_info map_info, PrintWriter pw,Server_GameBoard board) {
        this.map_info = map_info;
        this.activity = activity;
        this.pw = pw;
        this.board = board;
    }

    public void taskStart(){
        timer = new TimerTask() {
            @Override
            public void run() {
                try {
                    while(map_info.game_time>0) {
                        //                        GameMain.tt.setText("" + map_info.game_time);
                        Thread.sleep(1000);
                        map_info.game_time--;
                        if(map_info.game_time==0){
                            map_info.endMygame=true;
                            HashMap<Object,Object> map = new HashMap<Object,Object>();
                            if(map_info.player==1) {
                                map.put("endplayer1", true);
                                map.put("totalscore1",map_info.total_score);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            }
                            else if(map_info.player==2) {
                                map.put("endplayer2", true);
                                map.put("totalscore2",map_info.total_score);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.invalidate();
                                    }
                                });
                            }
                            sendAll(map);

                        }
                        Log.d("Time!!@@@@", "//" + map_info.game_time);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Server_GameMain.tt.setText("" + map_info.game_time);
                            }
                        });
                    }
//                    Toast.makeText(activity.getApplication(),"게임 끝!",Toast.LENGTH_SHORT);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        Timer tim  = new Timer();
        tim.schedule(timer,0);
    }
    private void sendAll(HashMap<Object, Object> map) {
        Gson gson = new Gson();
        String result = gson.toJson(map);
        pw.println(result);
        pw.flush();
        System.out.println("보냄");

    }
}
