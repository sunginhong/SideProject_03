package com.example.sunginhong.sideproject_03;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    static int screenWidth;
    static int screenHeight;

    static View mainBottom_bgView;
    static ImageButton meinBottomMenu_Icn0;
    static RelativeLayout mainRvLayout;

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

        meinBottomMenu_Icn0 = (ImageButton) findViewById(R.id.meinBottomMenu_Icn0);
        mainRvLayout = (RelativeLayout) findViewById(R.id.mainRL);
        mainRvLayout.setBackgroundResource(R.color.gooeyview_bg_color);

    }
}
