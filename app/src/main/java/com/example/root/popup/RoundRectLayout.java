package com.example.root.popup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class RoundRectLayout  extends FrameLayout {

    public float[] radii = new float[8];   // top-left, top-right, bottom-right, bottom-left
    public Path mClipPath;                 // 剪裁区域路径
    public Paint mPaint;                   // 画笔
    public RectF mLayer;                   // 画布图层大小



    public RoundRectLayout(Context context) {
        this(context, null);
    }

    public RoundRectLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundRectLayout);
        int roundCorner = ta.getDimensionPixelSize(R.styleable.RoundRectLayout_CornerRadius, 0);
        setRadius(roundCorner);
        ta.recycle();
        mLayer = new RectF();
        mClipPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mLayer.set(0, 0, width, height);
        int w = (int) mLayer.width();
        int h = (int) mLayer.height();
        RectF areas = new RectF();
        areas.left = getPaddingLeft();
        areas.top = getPaddingTop();
        areas.right = w - getPaddingRight();
        areas.bottom = h - getPaddingBottom();
        mClipPath.reset();
        mClipPath.addRoundRect(areas, radii, Path.Direction.CW);
        mClipPath.moveTo(0, 0);  // 通过空操作让Path区域占满画布
        mClipPath.moveTo(w , h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mClipPath, mPaint);
        canvas.restore();
    }

    public void setRadius(float radius) {
        for (int i = 0; i < radii.length; i++) {
            radii[i] = radius;
        }
        invalidate();
    }





}
