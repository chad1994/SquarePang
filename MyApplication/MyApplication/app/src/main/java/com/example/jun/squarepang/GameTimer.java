package com.example.jun.squarepang;

import android.app.Activity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by Jun on 2017-11-15.
 */

public class GameTimer{
    Map_info map_info;
    TimerTask timer;
    Handler handler;
    Activity activity;

    public GameTimer(Activity activity, Map_info map_info) {
        this.map_info = map_info;
        this.activity = activity;
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
                            Log.d("Time!!@@@@", "//" + map_info.game_time);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GameMain.tt.setText("" + map_info.game_time);
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
}
