package com.example.jun.squarepang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jun on 2017-11-26.
 */

public class SignupPage extends AppCompatActivity {

    EditText id,pw;
    Button signupbtn;
    HttpConn httpConn;
    String answer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id=(EditText)findViewById(R.id.SignupID);
        pw=(EditText)findViewById(R.id.SignupPW);
        signupbtn= (Button)findViewById(R.id.btn_signup);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().length()>=4&&pw.getText().length()>=4) {
                    httpConn = new HttpConn("http://192.168.0.4:8080/SquarepangLoginServer/Signup", id.getText().toString(), pw.getText().toString());
                    try {
                        answer = httpConn.execute().get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (answer.equals("success")) {
                        Toast.makeText(SignupPage.this, "회원 가입 완료.", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else if (answer.equals("already")) {
                        Toast.makeText(SignupPage.this, "해당 아이디가 이미 있습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(SignupPage.this, "아이디 비밀번호를 4자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
