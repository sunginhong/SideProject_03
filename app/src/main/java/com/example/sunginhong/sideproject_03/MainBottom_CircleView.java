package com.example.sunginhong.sideproject_03;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Anim;
import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Calc;
import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Interp;

import java.util.Timer;
import java.util.TimerTask;

public class MainBottom_CircleView extends android.support.v7.widget.AppCompatImageView {

    Context context;
    static boolean drag = false;
    private float dragStart_x = 0;
    private float dragStart_y = 0;
    static float dragMove_x = 0;
    static float dragMove_y = 0;
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
                }, 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (!drag){
                    dragMove_x = MainBottom_GooeyView.circle.getX();
                    dragMove_y = MainBottom_GooeyView.circle.getY();
                }
                dragStart_x = meinBottomMenu_CenterCircle.getX() - e.getRawX();
                dragStart_y = meinBottomMenu_CenterCircle.getY() - e.getRawY();

                MainBottom_GooeyView.dragStart_point_x = e.getX();
                MainBottom_GooeyView.dragStart_point_y = e.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                drag = true;
                if (drag){
                    dragMove_x = e.getRawX() + dragStart_x;
                    dragMove_y = e.getRawY() + dragStart_y;
                    if (dragMove_y < MainActivity.screenHeight/2.8f){
                        dragMove_y = MainActivity.screenHeight/2.8f;
                    }

                    meinBottomMenu_CenterCircle.setX(dragMove_x);
                    meinBottomMenu_CenterCircle.setY(dragMove_y);

                    MainBottom_GooeyView.dragMove_point_x = dragMove_x;
                    MainBottom_GooeyView.dragMove_point_y = dragMove_y;

                    MainBottom_GooeyView.gooeyview_canvas.invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (drag){
                    MainBottom_GooeyView.function_SetFloat_calcRate_reset();
                    circle_originAnim(600);
                }
                drag = false;
                break;
            default:
                break;
        }
        return true;
    }

    static void circle_originAnim(int duration) {
        ValueAnimator animator_x = ValueAnimator.ofFloat(dragMove_x, MainBottom_GooeyView.circleSetOriginX);
        ValueAnimator animator_y = ValueAnimator.ofFloat(dragMove_y, MainBottom_GooeyView.circleSetOriginY);
        animator_x.setDuration(duration/2);
        animator_x.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator_x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos_origin_Xn = (float) animation.getAnimatedValue();
                meinBottomMenu_CenterCircle.setX(pos_origin_Xn);
            }
        });
        animator_x.start();

        animator_y.setDuration(duration);
        Utils_Interp.MyBounceInterpolator interpolator = new Utils_Interp.MyBounceInterpolator(0.1, 2);
        animator_y.setInterpolator(interpolator);
//        animator_y.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator_y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos_origin_Yn = (float) animation.getAnimatedValue();
                meinBottomMenu_CenterCircle.setY(pos_origin_Yn);
                MainBottom_GooeyView.gooeyview_canvas.invalidate();
            }
        });
        animator_y.start();
    }
}

