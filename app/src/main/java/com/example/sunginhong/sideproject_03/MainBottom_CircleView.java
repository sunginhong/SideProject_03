package com.example.sunginhong.sideproject_03;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Anim;
import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Calc;
import com.example.sunginhong.sideproject_03.Utils_Folder.Utils_Interp;

import java.util.Timer;
import java.util.TimerTask;

public class MainBottom_CircleView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    Context context;
    static boolean drag = false;
    static boolean dragENTER = false;
    private float dragStart_x = 0;
    private float dragStart_y = 0;
    static float dragMove_x = 0;
    static float dragMove_y = 0;
    static ImageView mainBottomMenu_CenterCircle;
    static float icnRotate_deg = 180+45;

    private float mainBottomMenu_CenterCircle_onriginY = 0;
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
        mainBottomMenu_CenterCircle = (ImageView) findViewById(R.id.mainBottomMenu_CenterCircle);
        mainBottom_bgView_Height = Utils_Calc.dpToPx(75+10);

        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        mainBottomMenu_CenterCircle_onriginY = mainBottomMenu_CenterCircle.getY();
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
                dragStart_x = mainBottomMenu_CenterCircle.getX() - e.getRawX();
                dragStart_y = mainBottomMenu_CenterCircle.getY() - e.getRawY();

                MainBottom_GooeyView.dragStart_point_x = e.getX();
                MainBottom_GooeyView.dragStart_point_y = e.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                drag = true;
                if (drag && !dragENTER){
                    dragMove_x = e.getRawX() + dragStart_x;
                    dragMove_y = e.getRawY() + dragStart_y;
                    if (dragMove_y < MainActivity.screenHeight / 2 - MainBottom_GooeyView.circleWidth){
                        dragMove_y = MainActivity.screenHeight / 2 - MainBottom_GooeyView.circleWidth;
                    }

                    mainBottomMenu_CenterCircle.setX(dragMove_x);
                    mainBottomMenu_CenterCircle.setY(dragMove_y);
                    MainActivity.mainBottomMenu_CenterRlView.setX(dragMove_x);
                    MainActivity.mainBottomMenu_CenterRlView.setY(dragMove_y);

                    MainBottom_GooeyView.dragMove_point_x = dragMove_x;
                    MainBottom_GooeyView.dragMove_point_y = dragMove_y;

                    MainBottom_GooeyView.gooeyview_canvas.invalidate();

                    MainBottom_GooeyView.gooeyview_canvas.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                    MainActivity.menuViewRl.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                    MainActivity.mainBottomMenu_CenterView_White.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                    MainActivity.mainBottomMenu_CenterView_Green.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_out);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (drag){
                    if (MainActivity.mainBottomMenu_CenterRlView.getY() < MainActivity.screenHeight/1.8f){
                        if (!dragENTER) {
                            /// position - top
                            dragENTER = true;
                            Utils_Anim.TransAnim(MainActivity.mainBottomMenu_CenterRlView, -(MainBottom_GooeyView.circleSetOriginX - dragMove_x), 0, -(MainActivity.screenHeight / 2 - (MainBottom_GooeyView.circleWidth + dragMove_y)), 0, 100);
                            MainActivity.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);
                            MainActivity.mainBottomMenu_CenterRlView.setY(MainActivity.screenHeight / 2 - MainBottom_GooeyView.circleWidth);
                            MainActivity.mainBottomMenu_CenterRlView.invalidate();
                            Utils_Anim.drawableAlphaAnim(MainActivity.mainBottomMenu_CenterView_White, 1, 1, 0);
                            Utils_Anim.AlphaAnim(MainActivity.menuViewRl, MainActivity.menuViewRl.getAlpha(), 1, 500);
                            Utils_Anim.RotateAnim(MainActivity.mainBottomMenu_CenterView_Icn, 0, icnRotate_deg, 0.5f, 0.5f, 500);
                            MainActivity.mainBottomMenu_CenterRlView.setOnClickListener(this);
                        }
                    } else {
                        /// position - bottom
                        dragENTER = false;
                        Utils_Anim.TransAnim(MainActivity.mainBottomMenu_CenterRlView, -(MainBottom_GooeyView.circleSetOriginX-dragMove_x), 0, -(MainBottom_GooeyView.circleSetOriginY-dragMove_y), 0, 500);
                        MainActivity.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);
                        MainActivity.mainBottomMenu_CenterRlView.setY(MainBottom_GooeyView.circleSetOriginY);
                        MainActivity.mainBottomMenu_CenterRlView.invalidate();
                        Utils_Anim.drawableAlphaAnim(MainActivity.mainBottomMenu_CenterView_White, 1, 0, 0);
                        Utils_Anim.AlphaAnim(MainActivity.menuViewRl, MainActivity.menuViewRl.getAlpha(), 0, 500);
                        mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                        Utils_Anim.RotateAnim(MainActivity.mainBottomMenu_CenterView_Icn, icnRotate_deg, 0, 0.5f, 0.5f, 500);
                    }
                    circle_originAnim(500);
                }
                drag = false;
                break;
            default:
                break;
        }
        return true;
    }


    static void circle_originAnim(int duration) {
        float distance_calc = 0;
        if (!dragENTER){
            distance_calc = 0;
        } else {
            distance_calc = MainBottom_GooeyView.circleWidth;
        }
        ValueAnimator animator_x = ValueAnimator.ofFloat(dragMove_x, MainBottom_GooeyView.circleSetOriginX);
        ValueAnimator animator_y = ValueAnimator.ofFloat(dragMove_y, MainBottom_GooeyView.circleSetOriginY+distance_calc);
        animator_x.setDuration(duration/2);
        animator_x.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator_x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos_origin_Xn = (float) animation.getAnimatedValue();
                mainBottomMenu_CenterCircle.setX(pos_origin_Xn);
//                MainBottom_GooeyView.gooeyview_canvas.invalidate();
            }
        });
        animator_x.start();

        animator_y.setDuration(duration);
//        Utils_Interp.MyBounceInterpolator interpolator = new Utils_Interp.MyBounceInterpolator(0.1, 1);
//        animator_y.setInterpolator(interpolator);
        animator_y.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator_y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos_origin_Yn = (float) animation.getAnimatedValue();
                mainBottomMenu_CenterCircle.setY(pos_origin_Yn);
                MainBottom_GooeyView.gooeyview_canvas.invalidate();
            }
        });
        animator_y.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainBottomMenu_CenterRlView:
                drag = false;
                dragENTER = false;
                Utils_Anim.TransAnim(MainActivity.mainBottomMenu_CenterRlView, 0, 0, -(MainBottom_GooeyView.circleSetOriginY-dragMove_y), 0, 400);
                MainActivity.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);
                MainActivity.mainBottomMenu_CenterRlView.setY(MainBottom_GooeyView.circleSetOriginY);
                MainActivity.mainBottomMenu_CenterRlView.invalidate();
                Utils_Anim.drawableAlphaAnim(MainActivity.mainBottomMenu_CenterView_White, 1, 0, 400);
                Utils_Anim.drawableAlphaAnim(MainActivity.menuViewRl, 1, 0, 400);
                Utils_Anim.RotateAnim(MainActivity.mainBottomMenu_CenterView_Icn, icnRotate_deg, 0, 0.5f, 0.5f, 500);
                MainActivity.menuViewRl.setAlpha(0);

                MainActivity.mainBottomMenu_CenterRlView.setOnClickListener(null);
                mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                break;
        }

    }
}

