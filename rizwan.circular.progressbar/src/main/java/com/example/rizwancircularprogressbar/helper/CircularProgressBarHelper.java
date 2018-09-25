package com.example.rizwancircularprogressbar.helper;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public CircularProgressBarHelper(Context context, int progressMax, ViewGroup viewGroup) {
        if (progressMax != 0)
            PROGRESS_MAX = progressMax;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.circular_progress_layout, viewGroup);
        this.progressText = view.findViewById(R.id.progressText);
        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        lastMax = PROGRESS_MAX;
        circularProgressBar.setMax(PROGRESS_MAX);
        progressText.setText(String.valueOf(PROGRESS_MAX));

    }


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

    public void startProgressBar() {
        stopProgressBar();
        PROGRESS_MAX = lastMax;
        COUNT = PROGRESS_MAX;
        PREV_COUNT = 0;
        start();
    }

    public void stopProgressBar() {
        if (countDownTimer != null) {
            isRunning = false;
            countDownTimer.cancel();
        }
    }


    public void pauseProgressBar() {
        stopProgressBar();
        PREV_COUNT = lastMax - COUNT;
        if (COUNT >= 1) {
            PROGRESS_MAX = COUNT;
        }
    }


    public void resumeProgressBar() {
        start();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
