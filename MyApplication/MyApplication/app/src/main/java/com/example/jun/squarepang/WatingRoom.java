package com.example.jun.squarepang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Jun on 2017-11-15.
 */


public class WatingRoom extends AppCompatActivity {

    Button sb,vb;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wating_room);

        sb  = (Button) findViewById(R.id.single_button);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WatingRoom.this,GameMain.class);
                startActivity(intent);
            }
        });

        vb = (Button) findViewById(R.id.vs_button);

        vb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(WatingRoom.this,Server_GameMain.class);
                    startActivity(intent);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


}