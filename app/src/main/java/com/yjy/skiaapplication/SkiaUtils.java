package com.yjy.skiaapplication;

import android.graphics.Bitmap;
import android.view.Surface;

/**
 * <pre>
 *     author : yjy
 *     e-mail : yujunyu12@gmail.com
 *     time   : 2019/09/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SkiaUtils {
    static {
        System.loadLibrary("skia");
        System.loadLibrary("native-lib");
    }

    public static native void native_renderCanvas(Bitmap bitmap);

    public static native void native_render(Surface surface,int width,int height);
}
