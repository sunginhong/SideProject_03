package com.example.sunginhong.sideproject_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainBottom_GooeyView extends View {

    Context context;

    static float dragStart_point_x = 0;
    static float dragStart_point_y = 0;
    static float dragMove_point_x = 0;
    static float dragMove_point_y = 0;

    static Paint paint;
    static Path path;
    static MainBottom_GooeyView gooeyview_canvas;
    static int mainBottom_bgView_HeightSet = 0;
    static ImageView circle;
    static float circleWidth = 0;

    public MainBottom_GooeyView(Context context) {
        super(context);

        init();
    }

    public MainBottom_GooeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainBottom_GooeyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        gooeyview_canvas = (MainBottom_GooeyView) findViewById(R.id.gooeyview_canvas);
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        mainBottom_bgView_HeightSet = MainActivity.screenHeight - MainBottom_CircleView.mainBottom_bgView_Height;

                    }
                }, 150);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        circle = MainBottom_CircleView.meinBottomMenu_CenterCircle;
        View bottom_bgView = MainActivity.mainBottom_bgView;
        circleWidth = circle.getWidth();
        float p_x1 = 0;
        float p_y1 = mainBottom_bgView_HeightSet;
        float p_x2 = dragMove_point_x;
        float p_y2 = dragMove_point_y;
        float p_x3 = dragMove_point_x;
        float p_y3 = dragMove_point_y + (MainActivity.screenHeight - circle.getY())/2;
//        Log.d("SSSS"+bottom_bgView.getY(), "SSS"+ (circle.getY()));
        MainBottom_GooeyView.pathDraw(p_x1, p_y1, p_x2, p_y2, p_x3, p_y3);
//        MainBottom_GooeyView.pathDraw(dragMove_point_x - MainActivity.screenWidth/4, dragMove_point_y, dragMove_point_x, dragMove_point_y - Utils_Calc.dpToPx(3), MainActivity.screenWidth/4, dragMove_point_y);
        canvas.drawPath(path, paint);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    static void pathDraw(float x1, float y1, float x2, float y2, float x3, float y3){
        int radius = 90;
        RectF oval = new RectF(x3+circleWidth/2 - radius, circle.getY() - radius, x3+circleWidth/2 + radius, circle.getY() + radius);
        path = new Path();
        paint.setColor(Color.rgb(95, 206, 85));
        paint.setStrokeWidth(3);
        path.reset();
        path.moveTo(0, mainBottom_bgView_HeightSet);
        path.lineTo(x3, y3);
        path.lineTo(x3, y2 + circleWidth/2);
//        path.addArc(oval, 180, 180);
        path.lineTo(x3 + circleWidth, y2 + circleWidth/2);
        path.lineTo(x3 + circleWidth, y3);
        path.lineTo(MainActivity.screenWidth, mainBottom_bgView_HeightSet);
//        path.cubicTo(x1, y1, x2, y2, x3, y3);
//        path.cubicTo(0, mainBottom_bgView_HeightSet, x1, y1, x2+MainBottom_CircleView.meinBottomMenu_CenterCircle.getWidth()/2, y2);
//        path.cubicTo(x2+(MainBottom_CircleView.meinBottomMenu_CenterCircle.getWidth()/2*1.5f), y2, ((x2+MainBottom_CircleView.meinBottomMenu_CenterCircle.getWidth()/2*1.5f)+x3), y3, MainActivity.screenWidth, mainBottom_bgView_HeightSet);
//        path.close();
    }
}
