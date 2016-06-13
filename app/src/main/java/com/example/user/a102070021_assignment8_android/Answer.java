package com.example.user.a102070021_assignment8_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by user on 2016/6/9.
 */
public class Answer extends AppCompatActivity {

    private String operator1=Calculator.operator;
    private float num_answer;
    private String my_answer;
    public static String tmp;
    public static int ctrl;
    private TextView answer;
    public Button rtn;
    public Button home;
    public Button again;
    private Socket clientSocket1=MainActivity.clientSocket;
    private BufferedReader br1=MainActivity.br;
    private PrintWriter writer1=MainActivity.writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        ctrl=0;

        answer=(TextView) this.findViewById(R.id.answer);
        rtn = (Button) this.findViewById(R.id.rtn);
        home = (Button) this.findViewById(R.id.home);
        again = (Button) this.findViewById(R.id.again);

        if (operator1.equals("+"))
            num_answer=Calculator.numX+Calculator.numY;
        else if (operator1.equals("-"))
            num_answer=Calculator.numX-Calculator.numY;
        else if (operator1.equals("*"))
            num_answer=Calculator.numX*Calculator.numY;
        else if (operator1.equals("/"))
            num_answer=Calculator.numX/Calculator.numY;

        //顯示答案
        my_answer=Calculator.numX+operator1+Calculator.numY+"="+num_answer;
        answer.setText(my_answer);
        writer1.println(my_answer);
        writer1.flush();

        tmp=Float.toString(num_answer);

        //回輸入數字頁面
        this.rtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Answer.this, Calculator.class);
                Answer.this.startActivity(intent);
            }
        });

        //下次計算使用這次結果
        this.again.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctrl=1;
                Intent intent = new Intent();
                intent.setClass(Answer.this, Calculator.class);
                Answer.this.startActivity(intent);
            }
        });

        //回主畫面
        this.home.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Answer.this, MainActivity.class);
                Answer.this.startActivity(intent);
            }
        });
    }


}

