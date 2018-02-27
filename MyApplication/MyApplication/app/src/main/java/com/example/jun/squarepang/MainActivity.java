package com.example.jun.squarepang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {
    EditText id,pw;
    Button button_login;
    Button button_signup;
    User_info user_info;
    HttpConn httpConn;
    String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        id=(EditText)findViewById(R.id.edit_id);
        user_info  = new User_info();
        pw=(EditText)findViewById(R.id.edit_pw);
        button_signup=(Button)findViewById(R.id.signupButton);


        button_login = (Button)findViewById(R.id.login_button);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpConn = new HttpConn("http://192.168.0.4:8080/SquarepangLoginServer/Login",id.getText().toString(),pw.getText().toString());
                try {
                    answer = httpConn.execute().get();
                    Log.d("Answer:" ,""+answer);
                }catch(Exception e){
                 e.printStackTrace();
                }
                if(answer.equals("login")){
                    Intent intent = new Intent(MainActivity.this,WatingRoom.class);
                    user_info.name = id.getText().toString();
                    startActivity(intent);
                }
                else if(answer.equals("misspw")){
                    Toast.makeText(MainActivity.this, "패스워드 오류, 확인 후 로그인 해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(answer.equals("missid")){
                    Toast.makeText(MainActivity.this, "아이디 정보가 없습니다. 회원가입 후 이용해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupPage.class);
                startActivity(intent);
            }
        });
    }
}
