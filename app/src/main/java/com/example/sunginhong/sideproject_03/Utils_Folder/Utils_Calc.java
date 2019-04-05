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
}