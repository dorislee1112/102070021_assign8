package com.example.user.a102070021_assignment8_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by user on 2016/6/9.
 */
public class Calculator extends AppCompatActivity {

    public Button add;
    public Button minus;
    public Button multi;
    public Button divide;
    public Button clean;
    public static EditText num1;
    public static EditText num2;
    public static float numX;
    public static float numY;
    public static String operator;
    private Socket clientSocket1=MainActivity.clientSocket;
    private BufferedReader br1=MainActivity.br;
    private PrintWriter writer1=MainActivity.writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        add = (Button) this.findViewById(R.id.add);
        minus = (Button) this.findViewById(R.id.minus);
        multi = (Button) this.findViewById(R.id.multi);
        divide = (Button) this.findViewById(R.id.divide);
        num1 = (EditText) this.findViewById(R.id.num1);
        //num1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        num2 = (EditText) this.findViewById(R.id.num2);
        //num2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        clean = (Button) this.findViewById(R.id.clean);

        //若要使用上次結果 幫num1先設定好值
        if(Answer.ctrl==1){
            num1.setText(Answer.tmp);
            Answer.ctrl=0;
        }


        this.add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.getText()!=null&&num2.getText()!=null) {
                    operator = "+";
                    try {
                        numX = Float.valueOf(num1.getText().toString());
                        numY = Float.valueOf(num2.getText().toString());
                    }catch (Exception e){}
                    Intent intent = new Intent();
                    intent.setClass(Calculator.this, Answer.class);
                    Calculator.this.startActivity(intent);
                }
            }
        });

        this.minus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.getText()!=null&&num2.getText()!=null) {
                    operator = "-";
                    try {
                        numX = Float.valueOf(num1.getText().toString());
                        numY = Float.valueOf(num2.getText().toString());
                    }catch (Exception e){}

                    Intent intent = new Intent();
                    intent.setClass(Calculator.this, Answer.class);
                    Calculator.this.startActivity(intent);
                }
            }
        });

        this.multi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.getText()!=null&&num2.getText()!=null) {
                    operator = "*";
                    try {
                        numX = Float.valueOf(num1.getText().toString());
                        numY = Float.valueOf(num2.getText().toString());
                    }catch (Exception e){}

                    Intent intent = new Intent();
                    intent.setClass(Calculator.this, Answer.class);
                    Calculator.this.startActivity(intent);
                }
            }
        });

        this.divide.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.getText()!=null&&num2.getText()!=null) {
                    operator = "/";
                    try {
                        numX = Float.valueOf(num1.getText().toString());
                        numY = Float.valueOf(num2.getText().toString());
                    }catch (Exception e){}

                    Intent intent = new Intent();
                    intent.setClass(Calculator.this, Answer.class);
                    Calculator.this.startActivity(intent);
                }
            }
        });

        this.clean.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                num2.setText("");
            }
        });
    }
}
