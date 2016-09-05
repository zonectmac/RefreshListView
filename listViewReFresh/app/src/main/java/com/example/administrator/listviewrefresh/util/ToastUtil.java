package com.example.administrator.listviewrefresh.util;

import android.widget.Toast;

import com.example.administrator.listviewrefresh.App;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ToastUtil {

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG).show();
        }
    }
}
