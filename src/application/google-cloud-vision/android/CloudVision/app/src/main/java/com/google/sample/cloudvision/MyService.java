package com.google.sample.cloudvision;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyService extends Service {

    String rmsg;
    String msg;
    static final int SMS_RECEIVE_PERMISSON=1;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            msg = intent.getStringExtra("msg");

            MyService.TCPclient tcpThread = new MyService.TCPclient(msg);
            Thread thread = new Thread(tcpThread);
            thread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
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