package com.example.jun.squarepang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jun on 2017-11-17.
 */

public class SenderThread extends Thread {
    Socket socket;
    PrintWriter pw;
    Server_Map_info model;
    String name;

    public SenderThread(Socket socket,String name, Server_Map_info model,PrintWriter writer){
        this.socket=socket;
        this.name=name;
        this.model = model;
        this.pw = writer;
    }


    @Override
    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            pw.println(name);
            pw.flush();

            while(true){
                String str= reader.readLine();
                if(str.equals("bye"))
                    break;
                pw.println(str);
                pw.flush();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                socket.close();
            }
            catch(Exception ignored){}
        }
    }
}
