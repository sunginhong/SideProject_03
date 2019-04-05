package com.example.sunginhong.sideproject_03;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Calc;

import java.util.Timer;
import java.util.TimerTask;

public class MainBottom_CircleView extends android.support.v7.widget.AppCompatImageView {

    Context context;
    static boolean drag = false;
    private float dragStart_x = 0;
    private float dragStart_y = 0;
    private float dragMove_x = 0;
    private float dragMove_y = 0;
    static ImageView meinBottomMenu_CenterCircle;
    private float meinBottomMenu_CenterCircle_onriginY = 0;
    static int mainBottom_bgView_Height = 0;

    public MainBottom_CircleView(Context context) {
        super(context);
        initView();
    }

    public MainBottom_CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MainBottom_CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        meinBottomMenu_CenterCircle = (ImageView) findViewById(R.id.meinBottomMenu_CenterCircle);

        mainBottom_bgView_Height = Utils_Calc.dpToPx(75+10);
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        meinBottomMenu_CenterCircle_onriginY = meinBottomMenu_CenterCircle.getY();
                    }
                }, 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                drag = true;
                dragStart_x = meinBottomMenu_CenterCircle.getX() - e.getRawX();
                dragStart_y = meinBottomMenu_CenterCircle.getY() - e.getRawY();

                MainBottom_GooeyView.dragStart_point_x = e.getX();
                MainBottom_GooeyView.dragStart_point_y = e.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                dragMove_x = e.getRawX() + dragStart_x;
                dragMove_y = e.getRawY() + dragStart_y;

                meinBottomMenu_CenterCircle.setX(dragMove_x);
                meinBottomMenu_CenterCircle.setY(dragMove_y);

                MainBottom_GooeyView.dragMove_point_x = dragMove_x;
                MainBottom_GooeyView.dragMove_point_y = dragMove_y;

                MainBottom_GooeyView.gooeyview_canvas.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                drag = false;
                if (meinBottomMenu_CenterCircle.getY() >= meinBottomMenu_CenterCircle_onriginY-meinBottomMenu_CenterCircle.getHeight()){
                    //미달
                } else {
                    //넘어감
                }

                break;
            default:
                break;
        }
        return true;
    }

}

