package com.cookandroid.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    String rmsg;

    static final int SMS_RECEIVE_PERMISSON=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();
        //권한설정 dialog에서 거부를 누르면 //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다. //단, 사용자가 "Don't ask again"을 체크한 경우 //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_RECEIVE_PERMISSON);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_RECEIVE_PERMISSON);
            }
        }


        button = (Button) findViewById(R.id.button1);
        edit = (EditText) findViewById(R.id.edittext1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), edit.getText().toString(), 0);
                toast.show();

                TCPclient tcpThread = new TCPclient(edit.getText().toString());
                Thread thread = new Thread(tcpThread);
                thread.start();
            }
        });
    }
    private class TCPclient implements Runnable {
        private static final String serverIP = "*.*.*.*";
        private static final int serverPort = 1111;
        private Socket inetSocket = null;
        private String msg;
        private String phoneNum = "xxx-xxxx-xxxx";

        public TCPclient(String msg) {
            this.msg = msg;
        }

        public void run() {
            try {
                Log.d("TCP", "C: Connecting ... ");
                inetSocket = new Socket(serverIP, serverPort);
                try {
                    Log.d("TCP", "C: Sending ... " + msg);
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(inetSocket.getOutputStream())), true);
                    out.println(msg);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inetSocket.getInputStream()));

                    rmsg = in.readLine();
                    Log.d("TCP", "C: Server send to me this message --> " + rmsg);

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNum, null, rmsg, null, null);

                } catch (Exception e) {
                    Log.e("TCP", "C: Error1", e);
                } finally {
                    inetSocket.close();
                }
            } catch (Exception e) {
                Log.e("TCP", "C: Error2", e);
            }
        }
    }
}
