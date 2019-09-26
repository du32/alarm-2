package com.alarm.odesa;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService<state> extends Service {


    MediaPlayer media_song;

    int startId;
    boolean isRunning ;





    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("Localservice", "Received start id " + startId + ": " + intent);




        String state = intent.getExtras().getString("extra");
        Log.e("Ringtone state :extra is ", state);
        Log.e("Ringtone state :extra is ",state);

        assert state != null;
        //noinspection SingleStatementInBlock
        switch (state){
            case "alarm on" :
                startId =1;
                break;


            case "alarm off" :
                startId =0;

                Log.e("Start ID is ",state);

                break;

            default:
                startId =0;
                break;


        }
        if (!this.isRunning&& startId ==1){

            Log.e("there is no music ,","and you want start");

            media_song = MediaPlayer.create(this, R.raw.chicken_song);
            media_song.start();

            this.isRunning =true;
            this.startId =0;

        }

        else if (this.isRunning&& startId ==0){
            Log.e("there is music ,","and you want end");

            media_song.stop();
            media_song.reset();

            this.isRunning =false;
            this.startId =0;


        }
        else if (!this.isRunning&& startId ==0){
            Log.e("there is no music ,","and you want end");
            this.isRunning =false;
            this.startId =0;


        }
        else if (this.isRunning&& startId ==1){

            Log.e("there is music ,","and you want start");
            this.isRunning =true;
            this.startId =1;


        }
        else {
            Log.e("else "," somehow you reached this");

        }








        return START_NOT_STICKY;
    }





    @Override
    public void onDestroy() {

        Log.e("on Destroy called ","ta da");

        super.onDestroy();
        this.isRunning = false;
    }}