package com.example.ass4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup deviceGroup;
    RadioButton selectedDevice;

    CheckBox checkNotifications, checkDarkMode, checkLocation, checkBackup;

    Button btnSubmit;

    TextView tvDevice, tvFeature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceGroup = findViewById(R.id.deviceGroup);

        checkNotifications = findViewById(R.id.checkNotifications);
        checkDarkMode = findViewById(R.id.checkDarkMode);
        checkLocation = findViewById(R.id.checkLocation);
        checkBackup = findViewById(R.id.checkBackup);


        tvDevice = findViewById(R.id.tvDevice);
        tvFeature = findViewById(R.id.tvFeature);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {

            int selectedId = deviceGroup.getCheckedRadioButtonId();
            String device = "";
            String feature = "";

            if (selectedId != -1) {
                RadioButton selectedDevice = findViewById(selectedId);
                device = selectedDevice.getText().toString();
            }

            if (checkNotifications.isChecked()) {
                feature += "Notifications, ";
            }

            if (checkDarkMode.isChecked()) {
                feature += "Dark Mode, ";
            }

            if (checkLocation.isChecked()) {
                feature += "Location Services, ";
            }

            if (checkBackup.isChecked()) {
                feature += "Cloud Backup, ";
            }

            if (feature.endsWith(", ")) {
                feature = feature.substring(0, feature.length() - 2);
            }

            String deviceText = "Selected Device: " + device;
            String featureText = "Selected Feature: " + feature;

            SpannableString deviceSpan = new SpannableString(deviceText);
            deviceSpan.setSpan(
                    new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                    17,
                    deviceText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            SpannableString featureSpan = new SpannableString(featureText);
            featureSpan.setSpan(
                    new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                    18,
                    featureText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            tvDevice.setText(deviceSpan);
            tvFeature.setText(featureSpan);

        });
    }
}