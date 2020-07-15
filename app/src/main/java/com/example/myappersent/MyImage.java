package com.example.myappersent;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Auther: hchen
 * @Date: 2020/7/15 0015
 * @Description:
 */
public class MyImage extends View {

    private final Paint mPaint;

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.c);
        canvas.drawBitmap(bitmap,0,0,mPaint);
        super.onDraw(canvas);

    }
}
