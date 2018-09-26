package com.example.rizwan.customprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.rizwancircularprogressbar.helper.CircularProgressBarHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup view1 = findViewById(R.id.viewContainer1);//your container where you would like to show this circular progress bar
        ViewGroup view2 = findViewById(R.id.viewContainer2);
        ViewGroup view3 = findViewById(R.id.viewContainer3);
        ViewGroup view4 = findViewById(R.id.viewContainer4);

        setupNewView(view1, 30, 48, 48);
        setupNewView(view2, 60, 60, 60);
        setupNewView(view3, 120, 96, 96);
        setupNewView(view4, 160, 128, 128);

    }

    private void setupNewView(ViewGroup view, int sec, int height, int width) {
        final CircularProgressBarHelper progress = new CircularProgressBarHelper(this, view);
        progress.setWidthAndHeight(width, height);
        progress.setMax(sec);
        progress.startProgressBar();

        findViewById(R.id.checkMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress.isRunning())
                    progress.pauseProgressBar();
                else
                    progress.resumeProgressBar();
            }
        });
    }
}
