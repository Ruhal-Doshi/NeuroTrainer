package com.example.neurotrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SixTileActivity extends AppCompatActivity {

    int[] imageNum;
    boolean[] active;
    boolean[] lock;
    ArrayList<Button> buttons;
    int num;
    TextView textView;
    public void check(){
        int[] count = new int[num];
        int sum =0;
        for(int i =0;i<6;i++){
            if(active[i]){
                count[imageNum[i]]++;
                sum++;
            }
        }
        if(sum%2!=0){
            return;
        }
        for(int i : count){
            if(i%2!=0){
                for(int j =0;j<6;j++){
                    if(!lock[j]){
                        active[j]=false;
                        buttons.get(j).setBackgroundResource(R.drawable.magnifyingglass);
                    }
                }
                return;
            }
        }

        for(int i =0;i<6;i++){
            if(active[i]){
                lock[i]=true;
            }
        }

        int l = 0;
        for(boolean b : lock){
            if(b){
                l++;
            }
        }

        if(l==6){
            //Toast.makeText(getApplicationContext(), "LEVEL COMPLETED", Toast.LENGTH_SHORT).show();
            if(MainActivity.level <= getIntent().getIntExtra("level",0)){
                MainActivity.level = getIntent().getIntExtra("level",0)+1;
                MainActivity.setLevelNum();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.neurotrainer",MODE_PRIVATE);
                sharedPreferences.edit().putInt("level",MainActivity.level).apply();
            }
        }
    }


    public void reveal(View view){
        int tag = Integer.parseInt(view.getTag().toString());
        Button button = (Button) view;
        if(lock[tag-1]){
           return;
        }
        if(active[tag-1]){
            ImageSetter.hide(button);
            active[tag-1]=false;
        }else{
            ImageSetter.show(button,imageNum[tag-1]);
            active[tag-1] = true;
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                check();
            }
        },500);
        //check();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_tile);

        Intent intent = getIntent();
        active = new boolean[6];
        lock = new boolean[6];
        num = intent.getIntExtra("num",0);
        textView = findViewById(R.id.textView2);
        imageNum = new int[6];
        int[] count = new int[num];
        int limit = 6/num;
        for(int i =0;i<6;i++){
            Random random = new Random();
            while(true){
                int temp = random.nextInt(num);
                if(count[temp]<limit){
                    count[temp]++;
                    imageNum[i] = temp;
                    break;
                }
            }
        }

        buttons = new ArrayList<>();
        buttons.add((Button)findViewById(R.id.button1));
        buttons.add((Button)findViewById(R.id.button2));
        buttons.add((Button)findViewById(R.id.button3));
        buttons.add((Button)findViewById(R.id.button4));
        buttons.add((Button)findViewById(R.id.button5));
        buttons.add((Button)findViewById(R.id.button6));
    }
}