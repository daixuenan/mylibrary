package com.dai.mylibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dai.mylibrary.R;

public class StepView extends LinearLayout {

    public StepView(Context context) {
        super(context);
        init();
    }

    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvNumber3;

    private View line1Right;
    private View line2Left;
    private View line2Right;
    private View line3Left;

    private TextView tvNumber1Text;
    private TextView tvNumber2Text;
    private TextView tvNumber3Text;

    private int currentStep = 0;

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_stepview, this, false);
        addView(rootView);

        tvNumber1 = rootView.findViewById(R.id.tvNumber1);
        tvNumber2 = rootView.findViewById(R.id.tvNumber2);
        tvNumber3 = rootView.findViewById(R.id.tvNumber3);

        line1Right = rootView.findViewById(R.id.line1Right);
        line2Left = rootView.findViewById(R.id.line2Left);
        line2Right = rootView.findViewById(R.id.line2Right);
        line3Left = rootView.findViewById(R.id.line3Left);

        tvNumber1Text = rootView.findViewById(R.id.tvNumber1Text);
        tvNumber2Text = rootView.findViewById(R.id.tvNumber2Text);
        tvNumber3Text = rootView.findViewById(R.id.tvNumber3Text);

        updateSteps(currentStep);
    }

    public void updateSteps(int step) {
        if (step >= 0 && step <= 3) {
            this.currentStep = step;
            setStep1Status(step >= 0);
            setStep2Status(step >= 1);
            setStep3Status(step >= 2);
        }
    }

    public int getCurrentStep() {
        return currentStep;
    }

    private void setStep1Status(boolean isActived) {
        tvNumber1.setBackgroundResource(isActived ? R.drawable.shape_bg_circle_red : R.drawable.shape_bg_circle_gray);
        tvNumber1Text.setTextColor(isActived ? getContext().getResources().getColor(R.color.black_3) : getContext().getResources().getColor(R.color.default_line_color));
    }

    private void setStep2Status(boolean isActived) {
        tvNumber2.setBackgroundResource(isActived ? R.drawable.shape_bg_circle_red : R.drawable.shape_bg_circle_gray);
        tvNumber2Text.setTextColor(isActived ? getContext().getResources().getColor(R.color.black_3) : getContext().getResources().getColor(R.color.default_line_color));

        line1Right.setBackgroundColor(isActived ? getContext().getResources().getColor(R.color.main_color) : getContext().getResources().getColor(R.color.gray_bg));
        line2Left.setBackgroundColor(isActived ? getContext().getResources().getColor(R.color.main_color) : getContext().getResources().getColor(R.color.gray_bg));
    }

    private void setStep3Status(boolean isActived) {
        tvNumber3.setBackgroundResource(isActived ? R.drawable.shape_bg_circle_red : R.drawable.shape_bg_circle_gray);
        tvNumber3Text.setTextColor(isActived ? getContext().getResources().getColor(R.color.black_3) : getContext().getResources().getColor(R.color.default_line_color));

        line2Right.setBackgroundColor(isActived ? getContext().getResources().getColor(R.color.main_color) : getContext().getResources().getColor(R.color.gray_bg));
        line3Left.setBackgroundColor(isActived ? getContext().getResources().getColor(R.color.main_color) : getContext().getResources().getColor(R.color.gray_bg));
    }

    public void setTvNumber1Text(String number1Text) {
        this.tvNumber1Text.setText(number1Text);
    }

    public void setTvNumber2Text(String number2Text) {
        this.tvNumber2Text.setText(number2Text);
    }

    public void setTvNumber3Text(String number3Text) {
        this.tvNumber3Text.setText(number3Text);
    }
}