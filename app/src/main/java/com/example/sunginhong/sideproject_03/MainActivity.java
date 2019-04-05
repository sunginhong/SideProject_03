package com.example.sunginhong.sideproject_03;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static int screenWidth;
    static int screenHeight;

    static View mainBottom_bgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screeSizeCalc();
        mainBottom_bgView = (View) findViewById(R.id.mainBottom_bgView);
    }

    private void screeSizeCalc(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }
}
