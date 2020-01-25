package com.yjy.skiaapplication;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.annotation.NonNull;

/**
 * <pre>
 *     author : yjy
 *     e-mail : yujunyu12@gmail.com
 *     time   : 2019/09/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SkiaCanvasView extends SurfaceView implements SurfaceHolder.Callback2 {

    private SurfaceHolder mHolder;
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private static final int DRAW = 1;

    public SkiaCanvasView(Context context) {
        this(context,null);
    }

    public SkiaCanvasView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SkiaCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mHandlerThread = new HandlerThread("Skia");
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHandlerThread.start();
        mHandler = new SkiaHandler(mHandlerThread.getLooper());

    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Message message = new Message();
        message.what = DRAW;
        message.obj = holder.getSurface();
        message.arg1 = getWidth();
        message.arg2 = getHeight();
        mHandler.sendMessage(message);
        Log.e("create","width:"+getWidth());
        Log.e("create","height"+getHeight());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHandlerThread.quit();
    }

    private  class SkiaHandler extends Handler{

        public SkiaHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DRAW:
                    synchronized (SkiaCanvasView.class){
                        SkiaUtils.native_render((Surface) msg.obj,msg.arg1,msg.arg2);
                    }

                    break;
            }
        }
    }






}
