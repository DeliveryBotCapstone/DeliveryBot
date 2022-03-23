package com.cookandroid.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
