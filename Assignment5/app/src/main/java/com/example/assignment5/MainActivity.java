package com.example.assignment5;
import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.POST_NOTIFICATIONS},100);
            }
            tp = findViewById(R.id.timepicker1);
            btn = findViewById(R.id.submit);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hour = tp.getHour();
                    int minute = tp.getMinute();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);
                    Log.d("Time:","hour:"+hour+"minute:"+minute);
                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent(MainActivity.this, AlramSerive.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_IMMUTABLE);
                    if(am!=null){
                        Toast.makeText(getApplicationContext(), "Alarm Set",
                                Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , pendingIntent);
                    }else {
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , pendingIntent);

                    }
                }
            });

            return insets;
        });
    }
}

