package com.example.sunginhong.sideproject_03.Utils_Folder;

import android.content.Context;
import android.content.res.Resources;

public class Utils_Calc {
    protected Context context;

    public Utils_Calc(){
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static float ModulateCalc(float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        return result;
    }
}