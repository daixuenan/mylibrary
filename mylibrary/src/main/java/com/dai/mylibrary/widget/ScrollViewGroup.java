package com.dai.mylibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

public class ScrollViewGroup extends ViewGroup {

    private static final String TAG = "ScrollView";

    private Scroller mScroller;
    private int mScreenHeight; // 窗口高度
    private int mLastY;
    private int mStart;
    private int mEnd;

    public ScrollViewGroup(Context context) {
        this(context, null);
    }

    public ScrollViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        // 获取屏幕高度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        Log.d(TAG, "ScrollViewGroup: ScreenHeight " + mScreenHeight);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量所有子View
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 设置ViewGroup的高度,对所有子View进行排列
        int childCount = getChildCount();
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        params.height = mScreenHeight * childCount;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                // 给每个ChildView放置在指定位置
                childView.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) event.getY();
                mStart = getScrollY();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    // 终止滑动
                    mScroller.abortAnimation();
                }
                int offsetY = (int) (mLastY - event.getY());
                Log.d(TAG, "onTouchEvent: getScrollY: " + getScrollY());
                Log.d(TAG, "onTouchEvent: offsetY " + offsetY);
                // 到达顶部,使用offset判断方向
                if (getScrollY() + offsetY < 0) { // 当前已经滑动的 Y 位置
                    offsetY = 0;
                }
                // 到达底部
                if (getScrollY() > getHeight() - mScreenHeight && offsetY > 0) {
                    offsetY = 0;
                }
                scrollBy(0, offsetY);
                // 滑动完成后,重新设置LastY位置
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int distance = mEnd - mStart;
                if (distance > 0) { // 向上滑动
                    if (distance < mScreenHeight / 3) {
                        Log.d(TAG, "onTouchEvent: distance < screen/3");
                        // 回到原来位置
                        mScroller.startScroll(0, getScrollY(), 0, -distance);
                    } else {
                        // 滚到屏幕的剩余位置
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - distance);
                    }
                } else {    // 向下滑动
                    if (-distance < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -distance);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - distance);
                    }
                }
                postInvalidate();
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
