package com.example.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView {
    private SurfaceHolder mSurfaceHolder;
    private Paint paint;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    private void draw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();//在surface上绘制
        if (canvas != null) {
            canvas.drawCircle(100, 100, 50, paint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);//呈现到前台
        }
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                //surface被创建时
                draw();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                //surface大小发生变化时
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                //surface销毁
            }
        });
    }
}
