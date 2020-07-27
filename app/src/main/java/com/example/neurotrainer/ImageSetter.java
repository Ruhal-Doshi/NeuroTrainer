package com.example.neurotrainer;

import android.util.Log;
import android.widget.Button;

public class ImageSetter {
    public static void show(Button button,int num){
        Log.i("num",Integer.toString(num));
        switch (num){
            case 0 :
                button.setBackgroundResource(R.drawable.bird);
                break;
            case 1 :
                button.setBackgroundResource(R.drawable.cow);
                break;
            case 2 :
                button.setBackgroundResource(R.drawable.koala);
                break;
            case 3 :
                button.setBackgroundResource(R.drawable.elephant);
                break;
            case 4 :
                button.setBackgroundResource(R.drawable.flamingo);
                break;
            case 5 :
                button.setBackgroundResource(R.drawable.frog);
                break;
            default:
                button.setBackgroundResource(R.drawable.magnifyingglass);
        }
    }

    public static void hide(Button button){
        button.setBackgroundResource(R.drawable.magnifyingglass);
    }
}
