package com.example.rizwancircularprogressbar.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rizwancircularprogressbar.CircularProgressBar;
import com.example.rizwancircularprogressbar.R;

/**
 * Created by Abdul Rizwan on 24-09-2018.
 */
public class CircularProgressBarHelper {
    TextView progressText;
    CircularProgressBar circularProgressBar;

    CountDownTimer countDownTimer;
    int PREV_COUNT = 0;
    int PROGRESS_MAX = 30;
    int COUNT = 0;
    int lastMax = 0;
    boolean isRunning;
    Context context;

    public CircularProgressBarHelper(Context context, ViewGroup viewGroup) {
        this.doSetup(context, PROGRESS_MAX, viewGroup);
    }

    /***
     * at initial you must need to call this method;
     * @param context pass the context
     * @param countDownInSec give your count will start from eg :30 then progress will be start from 30 then 29 28 27 ...till 0
     * @param viewGroup Where you want to place this circular progress bar please prove the view
     */
    public CircularProgressBarHelper(Context context, int countDownInSec, ViewGroup viewGroup) {
        doSetup(context, countDownInSec, viewGroup);
    }

    private void doSetup(Context context, int countDownInSec, ViewGroup viewGroup) {
        if (countDownInSec != 0)
            PROGRESS_MAX = countDownInSec;
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.circular_progress_layout, viewGroup);
        this.progressText = view.findViewById(R.id.progressText);
        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        setupViewAndCount();
    }

    private void setupViewAndCount() {
        lastMax = PROGRESS_MAX;
        circularProgressBar.setMax(PROGRESS_MAX);
        progressText.setText(String.valueOf(PROGRESS_MAX));
    }

    /***
     *
     * @return return the centered progress text view, you can modify by using this method to get TextView;
     */
    public TextView getCountTextView() {
        if (progressText != null)
            return progressText;
        return null;
    }

    /***
     * default setup is there for circular progress bar but if you would like to change it with your progress drawable use this method;
     * @param drawable pass your progress drawable here;
     */
    public void setProgressDrawable(Drawable drawable) {
        circularProgressBar.setProgressDrawable(drawable);
    }

    /***
     * default setup is there for circular progress bar but if you would like to change it with your progress drawable use this method;
     * @param drawable pass your progress background drawable  here;
     */
    public void setProgressBackgroundDrawable(Drawable drawable) {
        circularProgressBar.setBackground(drawable);
    }

    /***
     * byDefault 48X48 is given for this circular progress bar if you wish to change it then use this method
     * @param width consider as dp
     * @param height consider as dp
     */
    public void setWidthAndHeight(int width, int height) {
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, context.getResources().getDisplayMetrics());
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics());
        circularProgressBar.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    }

    /***
     * pass the max of progress bar;
     * @param max
     */
    public void setMax(int max) {
        PROGRESS_MAX = max;
        setupViewAndCount();
    }

    //    public void setProgressBackgoundThickness(int thickness){
//        if (circularProgressBar.getBackground() instanceof ShapeDrawable) {
//            // cast to 'ShapeDrawable'
//            ShapeDrawable shapeDrawable = (ShapeDrawable) circularProgressBar.getBackground();
//            shapeDrawable.getPaint().setColor(ContextCompat.getColor(this, R.color.md_blue_500));
//            Shape shape = shapeDrawable.getPaint().getShader().
//        }
//        thickness = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, thickness, context.getResources().getDisplayMetrics());
//        circularProgressBar.getBackground().setBounds();
//
//    }
    private void start() {
        if (COUNT == 0) {
            stopProgressBar();
            return;
        }
        isRunning = true;
        countDownTimer = new CountDownTimer(PROGRESS_MAX * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                COUNT--;
                circularProgressBar.setProgress((PREV_COUNT + PROGRESS_MAX) - COUNT);
                progressText.setText(String.valueOf(COUNT));
            }

            @Override
            public void onFinish() {
                COUNT--;
                circularProgressBar.setProgress((PREV_COUNT + PROGRESS_MAX) - COUNT);
                progressText.setText(String.valueOf(COUNT));
                stopProgressBar();
            }
        };
        countDownTimer.start();
    }

    /***
     * call this method whenever you want to start the progress bar;
     */
    public void startProgressBar() {
        stopProgressBar();
        PROGRESS_MAX = lastMax;
        COUNT = PROGRESS_MAX;
        PREV_COUNT = 0;
        start();
    }

    /***
     * this will stop your progress bar count down;
     */
    public void stopProgressBar() {
        if (countDownTimer != null) {
            isRunning = false;
            countDownTimer.cancel();
        }
    }

    /***
     * if you want to pause your progress bar use this to stop the count down and later you can restart the count down using resume method;
     */
    public void pauseProgressBar() {
        stopProgressBar();
        PREV_COUNT = lastMax - COUNT;
        if (COUNT >= 1) {
            PROGRESS_MAX = COUNT;
        }
    }

    /***
     * to resume your count down use this method;
     */
    public void resumeProgressBar() {
        start();
    }

    /***
     * this method
     * @return true/false boolean value to identify circular progress bar count down is in running mode or stop/stop;
     */
    public boolean isRunning() {
        return isRunning;
    }
}
