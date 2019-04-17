package com.example.dell.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        /*初始化关卡按钮并给与监听，存储于链表listOfPart中*/
        Part Cpu = new Part(R.id.CPU);
        Cpu.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent(MapActivity.this,CPU.class);
                startActivityForResult(intent0,0);
            }
        });
        MapActivity ma = new MapActivity();
        LinkedList<Part> listOfPart= new LinkedList<>();
        listOfPart = ma.init();

    }

    /*初始化按钮*/
    private LinkedList<Part> init(){
        final LinkedList<Part> listOfPart= new LinkedList<>();
        listOfPart.add(new Part(R.id.keyboard));
        listOfPart.add(new Part(R.id.memory));
        listOfPart.add(new Part(R.id.printer));

        listOfPart.get(0).getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*检测前一个关卡是否完成，完成可进入下一个关卡，第一关默认可进入*/
                Intent intent1 = new Intent(MapActivity.this,Keyboard.class);
                startActivityForResult(intent1,1);
            }

        });

        listOfPart.get(1).getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MapActivity.this,CPU.class);
                if(listOfPart.get(1).Isfinished){
                    startActivityForResult(intent2,2);
                }else{
                    Toast.makeText(MapActivity.this,"请完成前面的关卡",Toast.LENGTH_SHORT).show();
                }
            }
        });

        listOfPart.get(2).getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MapActivity.this,Printer.class);
                if(listOfPart.get(2).Isfinished){
                    startActivityForResult(intent3,3);
                }else{
                    Toast.makeText(MapActivity.this,"请完成前面的关卡",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return listOfPart;
    }

    /*创建一个Part内部类用于存储各个部分的状态*/
    private class Part {

        private Button button;
        private boolean Isfinished = false;

        /*返回关卡是否清除*/
        public boolean getState(){
            return Isfinished;
        }

        /*返回控制关卡的按钮*/
        public Button getButton(){
            return button;
        }

        /*关卡清除*/
        public void partClear(){
            Isfinished = true;
        }

        /*构造器，用于初始化按钮*/
        public Part(int ID){
            button = findViewById(ID);
        }
    }


}
