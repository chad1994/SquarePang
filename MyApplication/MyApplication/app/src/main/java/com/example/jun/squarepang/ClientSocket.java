package com.example.jun.squarepang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.Buffer;

public class ClientSocket extends AsyncTask<Void,Void,Void>{

    String ip;
    int port;
    Socket socket;
    Context context;
    Server_Map_info model;
    String name;
    Thread st,rt;
    Activity a;

    BufferedReader br;
    PrintWriter pw;

    ClientSocket(String ip, int port, Activity a,Context context,Server_Map_info model){
        this.ip=ip;
        this.port=port;
        this.a = a;
        this.context=context;
        this.model = model;
    }
            @Override
            protected void onPreExecute() {
//        super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... voids) {

                name = User_info.name;
                try {

                    socket = new Socket(ip, port);
                    PrintWriter pw = new PrintWriter(socket.getOutputStream());
                    pw.println(name);
                    pw.flush();

                    ReceiveThread rt = new ReceiveThread(socket,model,pw,a,context);
                    rt.start();

                    while(socket.isConnected()){
//                Log.d("TAG","소켓 살아있다 !");
                        Thread.sleep(1000);
//                Log.d("TAG","rt는? "+rt.isAlive());
                        if(model.finishGame==true)
                            socket.close();
                    }


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

