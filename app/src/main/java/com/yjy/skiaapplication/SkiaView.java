package com.yjy.skiaapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     author : yjy
 *     e-mail : yujunyu12@gmail.com
 *     time   : 2019/09/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SkiaView extends View {

    // Used to load the 'native-lib' library on application startup.

    Bitmap bitmap;
    Paint paint = new Paint();

    public SkiaView(Context context) {
        super(context);
    }

    public SkiaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkiaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(bitmap == null){
            bitmap=
                    Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),
                            Bitmap.Config.ARGB_8888);
        }

        SkiaUtils.native_renderCanvas(bitmap);

        canvas.drawBitmap(bitmap,0,0,paint);
    }


}
