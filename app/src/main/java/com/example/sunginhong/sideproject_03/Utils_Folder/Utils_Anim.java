package com.example.sunginhong.sideproject_03.Utils_Folder;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

public class Utils_Anim {
    protected Context context;

    public Utils_Anim(){
    }

    public static void TransAnim(View view, float startX, float endX, float startY, float endY, int duration) {
        TranslateAnimation anim = new TranslateAnimation(
                startX, endX,
                startY, endY );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void bgColorAnim(View view, Object startColor, Object endColor, int duration ){
        final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), startColor, endColor);
        backgroundColorAnimator.setDuration(duration);
        backgroundColorAnimator.start();
    }
}
