package com.example.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    CountDownTimer appTimer;
    Button goStopBtn;
    int mProgress;

    long timeToFinish;

    int minutes = 0;
    long seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);
        goStopBtn = findViewById(R.id.button);

        seekBar.setMax(300);




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;
                Log.e("Legeza", String.valueOf(progress));
                long quotient = progress % 60;

                    minutes =  (progress / 60);

                    seconds = quotient;

                String time = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

                textView.setText(time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        goStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekBar.setEnabled(false);

                appTimer = new CountDownTimer((mProgress * 1000), 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                        Log.e("Error", "Millis to finish" + millisUntilFinished/1000);

                        minutes = (int)millisUntilFinished/1000/60;

                        seconds = (int)(millisUntilFinished/1000)%60;

                        String time = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
                        textView.setText(time);
                    }

                    @Override
                    public void onFinish() {

                        seekBar.setEnabled(true);
                    }
                };

                Log.e("Progress", String.valueOf(mProgress));
                appTimer.start();

            }
        });





    }
}
