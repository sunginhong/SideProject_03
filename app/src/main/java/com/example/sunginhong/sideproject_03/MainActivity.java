package com.example.sunginhong.sideproject_03;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    static int screenWidth;
    static int screenHeight;

    static View mainBgColor;
    static View mainBottom_bgView;
    static ImageButton mainBottomMenu_Icn0;
    static RelativeLayout mainRvLayout;
    static RelativeLayout mainBottomMenu_CenterRlView;
    static ImageView mainBottomMenu_CenterView_Green;
    static ImageView mainBottomMenu_CenterView_White;

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

        mainBgColor = (View) findViewById(R.id.mainBgColor);
        mainBottomMenu_CenterRlView = (RelativeLayout) findViewById(R.id.mainBottomMenu_CenterRlView);
        mainBottomMenu_CenterView_Green = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_Green);
        mainBottomMenu_CenterView_White = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_White);
        mainBottomMenu_Icn0 = (ImageButton) findViewById(R.id.mainBottomMenu_Icn0);
        mainRvLayout = (RelativeLayout) findViewById(R.id.mainRL);
        mainBgColor.setBackgroundResource(R.color.gooeyview_bg_color);
        mainBgColor.setAlpha(0);
    }
}
