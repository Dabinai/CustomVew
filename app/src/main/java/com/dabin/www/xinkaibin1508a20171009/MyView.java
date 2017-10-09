package com.dabin.www.xinkaibin1508a20171009;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dabin on 2017/4/9.
 */

public class MyView extends View {

    //圆的半径
    private float mRadius;

    //色带的宽度
    private float mStripeWidth;
    //总体大小
    private int mHeight;
    private int mWidth;

    //动画位置百分比进度
    private int mCurPercent;

    //实际百分比进度
    private int mPercent;
    //圆心坐标
    private float x;
    private float y;

    //要画的弧度
    private int mEndAngle;

    //小圆的颜色
    private int mSmallColor;
    //大圆颜色
    private int mBigColor;

    //中心百分比文字大小
    private float mCenterTextSize;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);

        //获取色带的宽度
        mStripeWidth = a.getDimension(R.styleable.MyView_stripeWidth, PxUtils.dpToPx(30, context));
        //获取当前的百分比
        mCurPercent = a.getInteger(R.styleable.MyView_percent, 0);
        //获取小园的颜色
        mSmallColor = a.getColor(R.styleable.MyView_smallColor, Color.BLACK);
        //获取大圆的颜色
        mBigColor = a.getColor(R.styleable.MyView_bigColor, Color.WHITE);
        //获取中心文字的大小
        mCenterTextSize = a.getDimensionPixelSize(R.styleable.MyView_centerTextSize, PxUtils.spToPx(20, context));
        //获取园的半径
        mRadius = a.getDimensionPixelSize(R.styleable.MyView_radius, PxUtils.dpToPx(100, context));


    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //如果为确定大小值，则圆的半径为宽度/2
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }
        //如果为wrap_content 那么View大小为圆的半径大小*2
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
            x = mRadius;
            y = mRadius;

        }
        //设置视图的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        mEndAngle = (int) (mCurPercent * 3.6);
        //绘制大圆
        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius, bigCirclePaint);

        //饼状图
        Paint sectorPaint = new Paint();
        sectorPaint.setColor(mSmallColor);
        sectorPaint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawArc(rect, 270, mEndAngle, true, sectorPaint);

        //绘制小圆,颜色透明
        Paint smallCirclePaint = new Paint();
        smallCirclePaint.setAntiAlias(true);
        smallCirclePaint.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);

        //绘制文本
        Paint textPaint = new Paint();
        String text = mCurPercent + "%";
        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);

        textPaint.setColor(Color.RED);
        canvas.drawText(text, x - textLength / 2, y, textPaint);
        super.onDraw(canvas);
    }

    public void setmSmallColor(int mSmallColor) {
        this.mSmallColor = mSmallColor;
        invalidate();
    }

    //重置
    public void setReset(int percent) {
        mCurPercent = percent;
        invalidate();  //刷新视图
    }

    //外部设置百分比数
    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");  //抛异常
        }
        setCurPercent(percent);
    }

    //内部设置百分比 用于动画效果
    private void setCurPercent(int percent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 101; i++) {
                    mCurPercent = i;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate(); //线程中刷新视图
                }
            }
        }).start();
    }
}
