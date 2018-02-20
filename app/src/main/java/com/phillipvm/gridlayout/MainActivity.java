package com.phillipvm.gridlayout;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    int lastResource = 0;
    SeekBar timescroller;

    public void onClick(View view){
        int numId = view.getId();
        String ourId = view.getResources().getResourceEntryName(numId).toString();
        String packageName = getPackageName();
        int resource = getResources().getIdentifier(ourId,"raw",packageName);
        try{
            if(mp.isPlaying() && lastResource == resource){
                mp.pause();
                Log.i("5555555555555555555555", "onClick: ");
            }
            else if(lastResource != resource){ // doesn't matter you want to start or finish if it will be a new audio
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this,resource);
                mp.start();

            }
            else if(!mp.isPlaying() && lastResource == resource){
                mp.start();
            }
            lastResource = resource;

            mp.setLooping(true);
            timescroller.setMax(mp.getDuration());
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timescroller.setProgress(mp.getCurrentPosition());
                }
            },0,1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = new MediaPlayer();

        timescroller = (SeekBar) findViewById(R.id.seekBar);
        timescroller.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mp.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mp.setVolume(0,0);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.setVolume(1,1);
            }
        });
    }
}
