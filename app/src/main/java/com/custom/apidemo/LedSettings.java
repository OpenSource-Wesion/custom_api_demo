package com.custom.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LedSettings extends Activity {
    private static final String TAG = LedSettings.class.getSimpleName();
    private RadioGroup color0Led_group, color1Led_Group, color2Led_Group;
    private RadioButton color0_heartbeatButton, color0_onButton, color0_offButton, color0_timerButton;
    private RadioButton color1_heartbeatButton, color1_onButton, color1_offButton, color1_timerButton;
    private RadioButton color2_heartbeatButton, color2_onButton, color2_offButton, color2_timerButton;

    private static final int LED_WHITE = 0;
    private static final int LED_RED = 1;
    private static final int LED_BLUE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledsettings_main);

        color0Led_group = (RadioGroup)findViewById(R.id.color0Led_GroupId);
        color0_heartbeatButton = (RadioButton)findViewById(R.id.color0_heartbeatButtonId);
        color0_onButton = (RadioButton)findViewById(R.id.color0_onButtonId);
        color0_offButton = (RadioButton)findViewById(R.id.color0_offButtonId);
        color0_timerButton = (RadioButton)findViewById(R.id.color0_timerButtonId);

        color1Led_Group = (RadioGroup)findViewById(R.id.color1Led_GroupId);
        color1_offButton = (RadioButton)findViewById(R.id.color1_offButtonId);
        color1_onButton = (RadioButton)findViewById(R.id.color1_onButtonId);
        color1_heartbeatButton = (RadioButton)findViewById(R.id.color1_heartbeatButtonId);
        color1_timerButton = (RadioButton)findViewById(R.id.color1_timerButtonId);

        color2Led_Group = (RadioGroup)findViewById(R.id.color2Led_GroupId);
        color2_offButton = (RadioButton)findViewById(R.id.color2_offButtonId);
        color2_onButton = (RadioButton)findViewById(R.id.color2_onButtonId);
        color2_heartbeatButton = (RadioButton)findViewById(R.id.color2_heartbeatButtonId);
        color2_timerButton = (RadioButton)findViewById(R.id.color2_timerButtonId);

        int color0Mode = MainApp.getCustomApi().getLedMode(LED_WHITE);
        Log.d(TAG, "color0Mode " + color0Mode);
        if(0 == color0Mode)
            color0_offButton.setChecked(true);
        else if(1 == color0Mode)
            color0_onButton.setChecked(true);
        else if(2 == color0Mode)
            color0_timerButton.setChecked(true);
        else if(3 == color0Mode)
            color0_heartbeatButton.setChecked(true);

        int color1Mode = MainApp.getCustomApi().getLedMode(LED_RED);
        Log.d(TAG, "color1Mode " + color1Mode);
        if(0 == color1Mode)
            color1_offButton.setChecked(true);
        else if(1 == color1Mode)
            color1_onButton.setChecked(true);
        else if(2 == color1Mode)
            color1_timerButton.setChecked(true);
        else if(3 == color1Mode)
            color1_heartbeatButton.setChecked(true);

        int color2Mode = MainApp.getCustomApi().getLedMode(LED_BLUE);
        Log.d(TAG, "color2Mode " + color2Mode);
        if(0 == color2Mode)
            color2_offButton.setChecked(true);
        else if(1 == color2Mode)
            color2_onButton.setChecked(true);
        else if(2 == color2Mode)
            color2_timerButton.setChecked(true);
        else if(3 == color2Mode)
            color2_heartbeatButton.setChecked(true);

        RadioGroupListener listener = new RadioGroupListener();
        color0Led_group.setOnCheckedChangeListener(listener);
        color1Led_Group.setOnCheckedChangeListener(listener);
        color2Led_Group.setOnCheckedChangeListener(listener);

    }
    class RadioGroupListener implements OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(group == color0Led_group) {
                if (checkedId == color0_offButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_WHITE, 0);
                } else if (checkedId == color0_onButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_WHITE, 1);
                } else if (checkedId == color0_timerButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_WHITE, 2);
                } else if (checkedId == color0_heartbeatButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_WHITE, 3);
                }
            } else if(group == color1Led_Group) {
                if (checkedId == color1_offButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_RED, 0);
                } else if (checkedId == color1_onButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_RED, 1);
                } else if (checkedId == color1_timerButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_RED, 2);
                } else if (checkedId == color1_heartbeatButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_RED, 3);
                }
            } else if(group == color2Led_Group) {
                if (checkedId == color2_offButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_BLUE, 0);
                } else if (checkedId == color2_onButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_BLUE, 1);
                } else if (checkedId == color2_timerButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_BLUE, 2);
                } else if (checkedId == color2_heartbeatButton.getId()) {
                    MainApp.getCustomApi().setLedMode(LED_BLUE, 3);
                }
            }
        }
    }
}
