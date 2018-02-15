package com.phillipvm.gridlayout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        int numId = view.getId();
        String ourId = view.getResources().getResourceEntryName(numId).toString();
        String packageName = getPackageName();
        int resource = getResources().getIdentifier(ourId,"raw",packageName);
        MediaPlayer mp = MediaPlayer.create(this,resource);
        if(!mp.isPlaying()){
            mp.start();
        }
        else if(mp.isPlaying()){
            mp.pause();
        }
    }
}
