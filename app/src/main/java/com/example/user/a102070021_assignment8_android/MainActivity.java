package com.example.user.a102070021_assignment8_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    public static InetAddress serverIp;
    public static Socket clientSocket;
    public static BufferedReader br;
    public static PrintWriter writer;
    public static Thread thread;
    public Button ok;
    public EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ok = (Button) this.findViewById(R.id.OK);
        ip = (EditText) this.findViewById(R.id.IP);
        ip.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ip.getText()!=null) {
                    //取得server ip
                    try {
                        serverIp = InetAddress.getByName(ip.getText().toString());
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }

                    thread=new Thread(Connection);
                    thread.start();
                    System.out.println("Get connected already...");

                    //換頁面
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, Calculator.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }

    //建立連線
    private Runnable Connection=new Runnable() {
        public void run() {
            try{
                // IP為Server端
                System.out.println(serverIp);
                clientSocket = new Socket();
                clientSocket.bind(null);
                clientSocket.connect(new InetSocketAddress(serverIp, 8000), 100000);
                System.out.println("Socket gets connected");
                //取得網路輸出串流
                writer = new PrintWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }
    };
}
