package com.example.rizwancircularprogressbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Abdul Rizwan on 24-09-2018.
 *
 */
public class CircularProgressBar extends ProgressBar {

    public CircularProgressBar(Context context) {
        super(context);
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.circular_progress_bar));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(ContextCompat.getDrawable(context, R.drawable.circle_shape));
        }
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);

    }

    @Override
    public void setProgressDrawable(Drawable d) {
        super.setProgressDrawable(d);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }
}
