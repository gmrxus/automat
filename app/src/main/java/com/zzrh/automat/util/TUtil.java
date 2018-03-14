package com.zzrh.automat.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Gmrxus on 2018/3/13.
 */

public class TUtil {
    private static Toast toast;
    private TUtil() {
    }
    public static void showToast(Context context,String msg){
        if(toast==null){
            toast = toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else {
            toast.cancel();
            toast=toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
