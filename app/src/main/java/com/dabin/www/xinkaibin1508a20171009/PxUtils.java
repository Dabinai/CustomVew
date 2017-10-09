package com.dabin.www.xinkaibin1508a20171009;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Dabin on 2017/10/9.
 */

public class PxUtils {

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
