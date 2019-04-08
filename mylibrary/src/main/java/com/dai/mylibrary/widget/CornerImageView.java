package com.dai.mylibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.dai.mylibrary.utils.DpPxSpUtils;

public class CornerImageView extends AppCompatImageView {

    //圆角
    public static final int CORNER_RADIOS = DpPxSpUtils.dip2px(6);

    private int radios = CORNER_RADIOS;

    public CornerImageView(Context context) {
        this(context, null);
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }

    public CornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), radios, radios, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
