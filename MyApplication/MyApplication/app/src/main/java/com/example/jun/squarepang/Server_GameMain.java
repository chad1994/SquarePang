package com.example.jun.squarepang;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_GameMain extends AppCompatActivity {
    public static final String host = "192.168.0.4";
    public static final int port = 9999;
    static LinearLayout ll;
    static RelativeLayout mainboard;
    static ImageView waitingimg;
    FrameLayout fl;
    static TextView tt,st;
    Button cb ;
    static ClientSocket socket;
    static Context context;
    String name ;
    static Server_Map_info model;
    static TextView mn,yn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_game_main);
        context=this;

        waitingimg = (ImageView)findViewById(R.id.waitingimage);
        mainboard=(RelativeLayout) findViewById(R.id.mainBoard);
        mainboard.setVisibility(View.INVISIBLE);
        model = new Server_Map_info();
        st=(TextView)findViewById(R.id.vs_score_text);
        tt=(TextView)findViewById(R.id.vs_time_text);
        ll=(LinearLayout)findViewById(R.id.vs_linear);
        fl=(FrameLayout)findViewById(R.id.frame);
        mn = (TextView)findViewById(R.id.myname);
        yn = (TextView)findViewById(R.id.yourname);
        cb = (Button)findViewById(R.id.connectbutton);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    name = User_info.name;
                    fl.setVisibility(View.GONE);
                    socket= new ClientSocket(host,port,(Activity)context,context,model);
                    socket.execute();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        onBackPressed();
    }
}
