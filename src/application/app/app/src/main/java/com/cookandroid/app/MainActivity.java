package com.cookandroid.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    Button button;
    TextView tv;
    String temp;

    static final int SMS_SEND_PERMISSON = 1; // SMS 송신 권한 설정용 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // SMS 송신 권한 설정
        int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 송신 권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SMS 송신 권한 없음", Toast.LENGTH_SHORT).show();
            // 권한설정 dialog에서 거부를 누르면
            // ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.
            // 단, 사용자가 "Don't ask again"을 체크한 경우
            // 거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                // 이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSON);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSON);
            }
        }


        button = (Button) findViewById(R.id.button1);
        edit = (EditText) findViewById(R.id.edittext1);
        tv = (TextView) findViewById(R.id.tv1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), edit.getText().toString(), 0);
                toast.show();

                temp = "0호12345호678공학관 A1404호 박종건";

                int start = temp.indexOf("공학관 ");
                int end = temp.indexOf("호");

                while (start > end) {
                    int num = temp.substring(end + 1).indexOf("호");
                    end += num + 1;
                }
                tv.setText(temp.substring((start + 4), end));


                // Intent intent = new Intent(getApplicationContext(), MyService.class);
                // intent.putExtra("msg", edit.getText().toString());
                // startService(intent);
            }
        });
    }
}
