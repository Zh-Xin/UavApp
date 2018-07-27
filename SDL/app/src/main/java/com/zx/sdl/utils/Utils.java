package com.zx.sdl.utils;

import android.provider.Settings;
import android.view.View;

public class Utils {

    /*
     * 隐藏虚拟按钮
     */
    public static int hideNavigationBar() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | 0X00001000;
    }

}
