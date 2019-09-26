package com.alarm.odesa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    // make alarm maneger
    AlarmManager alarm_manager;
    TimePicker alarm_timePicker;
    TextView update_text;
    Context context;
    PendingIntent pending_Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;

        /* initialire  TimePicker*/

        alarm_manager =(AlarmManager)getSystemService(ALARM_SERVICE);
        alarm_timePicker =(TimePicker)findViewById(R.id.timePicker);
        update_text =(TextView)findViewById(R.id.update_text);

        final Calendar calendar =Calendar.getInstance();

        final Intent my_intent =new Intent(this.context,Alarm_Receiver.class);

        Button alarm_on =(Button)findViewById(R.id.alarm_on);





        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calendar.set(Calendar.HOUR_OF_DAY,alarm_timePicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timePicker.getMinute());


                int hour = alarm_timePicker.getHour();
                int minute = alarm_timePicker.getMinute();


                String hour_string =String.valueOf(hour);
                String minute_string=String.valueOf(minute);


                if (hour>12){

                    hour_string=String.valueOf(hour - 12);
                }


                if (minute< 10){

                    minute_string= "0" +String.valueOf(minute);
                }




                set_alarm_text("Alarm set to:"+ hour_string+":"+minute_string);

                my_intent.putExtra("extra","Alarm On");

                pending_Intent= PendingIntent.getBroadcast(MainActivity.this,0,
                        my_intent,pending_Intent.FLAG_UPDATE_CURRENT);



                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pending_Intent);




            }




        });

        Button alarm_off =(Button)findViewById(R.id.alarm_off);

        alarm_off.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                set_alarm_text("off");


                alarm_manager.cancel(pending_Intent);

                my_intent.putExtra("extra"," Alarm off");



                sendBroadcast(my_intent);

            }
        });








    }

    private void set_alarm_text(String output) {

        update_text.setText(output);
    }
}
