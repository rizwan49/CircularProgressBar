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
        ViewGroup view = findViewById(R.id.viewContainer);//your container where you would like to show this circular progress bar
        int THIRTY_SEC = 30;//for count down for THIRTY sec
        final CircularProgressBarHelper progress = new CircularProgressBarHelper(this, THIRTY_SEC, view);

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
