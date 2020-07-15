package com.example.myappersent;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Auther: hchen
 * @Date: 2020/7/15 0015
 * @Description:
 */
public class MyRecView extends View {

    private final Paint mPaint;

    public MyRecView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
//        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setAntiAlias(true);//去掉锯齿
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF arc = new RectF(0, 0, 290, 290);
        //第一参数是  扇形的样子    参数3 角度  参数4 是否有边  true有 参数5 画笔
        //参数2 开始的角度  水平是0度 顺时针是正的度数  逆时针是负的度数  水平为0  12点钟位置是  -90
        //参数3  是圆弧的角度范围  180是半圆
        canvas.drawArc(arc,-90,330,true,mPaint);
        super.onDraw(canvas);
    }
}
