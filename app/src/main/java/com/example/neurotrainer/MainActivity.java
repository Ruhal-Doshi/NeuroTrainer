package com.example.neurotrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static  int level;
    static ArrayList<ImageView> imageViews;
    public void openLevel(View view){

        int tag = Integer.parseInt(view.getTag().toString());
        if(tag>level){
            return;
        }
        if(tag==1||tag==2){
            Intent intent = new Intent(this,SixTileActivity.class);
            intent.putExtra("num",3);
            intent.putExtra("level",tag);
            startActivity(intent);
        }else if(tag==3){
            Intent intent = new Intent(this,EightTileActivity.class);
            intent.putExtra("num",2);
            intent.putExtra("level",tag);
            startActivity(intent);
        }else if(tag==4||tag==5){
            Intent intent = new Intent(this,EightTileActivity.class);
            intent.putExtra("num",4);
            intent.putExtra("level",tag);
            startActivity(intent);
        }else if(tag==6){
            Intent intent = new Intent(this,TwelveTileActivity.class);
            intent.putExtra("num",3);
            intent.putExtra("level",tag);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,TwelveTileActivity.class);
            intent.putExtra("num",6);
            intent.putExtra("level",tag);
            startActivity(intent);
        }

    }

    public static void setLevelNum(){
        for(int i =0;i<level;i++){
            imageViews.get(i).setAlpha(1.0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.example.neurotrainer",MODE_PRIVATE);
        level = sharedPreferences.getInt("level",1);

        imageViews = new ArrayList<>();
        imageViews.add((ImageView) findViewById(R.id.imageView1));
        imageViews.add((ImageView) findViewById(R.id.imageView2));
        imageViews.add((ImageView) findViewById(R.id.imageView3));
        imageViews.add((ImageView) findViewById(R.id.imageView4));
        imageViews.add((ImageView) findViewById(R.id.imageView5));
        imageViews.add((ImageView) findViewById(R.id.imageView6));
        imageViews.add((ImageView) findViewById(R.id.imageView7));
        imageViews.add((ImageView) findViewById(R.id.imageView8));

        setLevelNum();
    }
}