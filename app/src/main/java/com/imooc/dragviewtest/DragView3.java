package com.imooc.dragviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragView3 extends View {

    private int lastX;
    private int lastY;

    public DragView3(Context context) {
        super(context);
        ininView();
    }

    public DragView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView();
    }

    public DragView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView();
    }

    private void ininView() {
//        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ViewGroup.MarginLayoutParams layoutParams1 = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        View parentView = (View) getParent();
        int width = parentView.getWidth();
        int height = parentView.getHeight();

        layoutParams1.leftMargin = width / 2 - 50;//+ getLeft();// + offsetX;
        layoutParams1.topMargin = height / 3 * 2 - 50;//+getTop();// + offsetY;
        setLayoutParams(layoutParams1);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint();
        int center = getWidth() / 2;
        int innerCircle = 20;
//        int ringWidth = dip2px(context, 5); //设置圆环宽度

        //绘制内圆
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, mPaint);


        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                // 计算偏移量
//                int offsetX = x - lastX;
//                int offsetY = y - lastY;

                View parentView = (View) getParent();
                int width = parentView.getWidth();
                int height = parentView.getHeight();
                int x0 = width / 2;
                int y0 = height / 3 * 2;

                int x1 = getLeft() + 50;  // 离开时的中心位置
                int y1 = getTop() + 50;


                float k = ((float) y1 - y0) / (x1 - x0);


                ViewGroup.MarginLayoutParams layoutParams1 = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();

                if ((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) <= 200 * 200)  // 滑出的范围


                {
                    layoutParams1.leftMargin = x0 - 50;// 原点位置
                    layoutParams1.topMargin = y0 - 50;
                    setLayoutParams(layoutParams1);
                } else {

                    if (x1 > x0 && y1 > y0) // 右下角
                    {
                        int Dx1 = width - x1;
                        int Dy1 = (int) (Dx1 * k);

                        int Dy2 = height - y1;
                        int Dx2 = (int) (Dy2 / k);
                        if (Dx1 < Dx2) {
                            layoutParams1.leftMargin = x1 + Dx1 - 100;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 + Dy1 - 100;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        } else {
                            layoutParams1.leftMargin = x1 + Dx2 - 100;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 + Dy2 - 100;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        }
                    }

                    if (x1 < x0 && y1 < y0) // 左上角
                    {
                        int Dx1 = x1;
                        int Dy1 = (int) (Dx1 * k);

                        int Dy2 = y1;
                        int Dx2 = (int) (Dy2 / k);

                        if (Dx1 < Dx2) {
                            layoutParams1.leftMargin = x1 - Dx1;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 - Dy1;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        } else {
                            layoutParams1.leftMargin = x1 - Dx2;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 - Dy2;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        }
                    }

                    if (x1 > x0 && y1 < y0) // 右上角
                    {
                        int Dx1 = width - x1;
                        int Dy1 = -(int) (Dx1 * k);

                        int Dy2 = y1;
                        int Dx2 = -(int) (Dy2 / k);

                        if (Dx1 < Dx2) {
                            layoutParams1.leftMargin = x1 + Dx1 - 100;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 - Dy1;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        } else {

                            layoutParams1.leftMargin = x1 + Dx2 - 100;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 - Dy2;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        }
                    }

                    if (x1 < x0 && y1 > y0) // 左下角
                    {
                        int Dx1 = x1;
                        int Dy1 = -(int) (Dx1 * k);

                        int Dy2 = height - y1;
                        int Dx2 = -(int) (Dy2 / k);

                        if (Dx1 < Dx2) {
                            layoutParams1.leftMargin = x1 - Dx1;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 + Dy1 - 100;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        } else {

                            layoutParams1.leftMargin = x1 - Dx2;//+ getLeft();// + offsetX;
                            layoutParams1.topMargin = y1 + Dy2 - 100;//+getTop();// + offsetY;
                            setLayoutParams(layoutParams1);
                        }

                    }


                }


                break;
        }
//        setBackgroundColor(Color.RED);
        return true;
    }
}
