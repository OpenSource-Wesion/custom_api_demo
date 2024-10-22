package com.custom.apidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GpioSettings extends Activity {
    private static final String TAG = "GpioSettings";
    private Context mContext;
    Button read_gpio_btn;
    TextView read_gpio_tv;
    Button write_gpio_1_btn, write_gpio_0_btn;
    Button set_gpio_in_btn, set_gpio_out_btn;
    Button req_gpio_btn, free_gpio_btn;
    TextView gpio_showInfo;
    EditText gpio_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpio_main);
        mContext = this;
        gpio_text = (EditText)findViewById(R.id.gpio_text);

        req_gpio_btn = (Button)findViewById(R.id.request_gpio_btn);
        free_gpio_btn = (Button)findViewById(R.id.free_gpio_btn);
        set_gpio_in_btn = (Button)findViewById(R.id.set_gpio_in_btn);
        set_gpio_out_btn = (Button)findViewById(R.id.set_gpio_out_btn);
        read_gpio_btn = (Button)findViewById(R.id.read_gpio_btn);
        read_gpio_tv = (TextView)findViewById(R.id.read_gpio_tv);
        write_gpio_0_btn = (Button)findViewById(R.id.write_gpio_0_btn);
        write_gpio_1_btn = (Button)findViewById(R.id.write_gpio_1_btn);
        gpio_showInfo = (TextView) findViewById(R.id.gpio_showInfo);

        req_gpio_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioRequest(Integer.parseInt(gpio_text.getText().toString()));
                Log.e(TAG, "gpioRequest " + ok);
                gpio_showInfo.setText("gpioRequest " + ok);
            }
        });

        set_gpio_in_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioSetDirection(Integer.parseInt(gpio_text.getText().toString()), "in");
                Log.e(TAG, "gpioSetDirection " + ok);
                gpio_showInfo.setText("gpioSetDirection input " + ok);
            }
        });

        set_gpio_out_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioSetDirection(Integer.parseInt(gpio_text.getText().toString()), "out");
                Log.e(TAG, "gpioSetDirection " + ok);
                gpio_showInfo.setText("gpioSetDirection output " + ok);
            }
        });

        read_gpio_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value  = MainApp.getCustomApi().gpioGetValue(Integer.parseInt(gpio_text.getText().toString()));
                Log.e(TAG, "gpioGetValue " + value);
                gpio_showInfo.setText("gpioGetValue " + value);
            }
        });

        write_gpio_0_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioSetValue(Integer.parseInt(gpio_text.getText().toString()), 0);
                Log.e(TAG, "gpioSetValue 0 " + ok);
                gpio_showInfo.setText("gpioSetValue 0 " + ok);
            }
        });

        write_gpio_1_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioSetValue(Integer.parseInt(gpio_text.getText().toString()), 1);
                Log.e(TAG, "gpioSetValue 1 " + ok);
                gpio_showInfo.setText("gpioSetValue 1 " + ok);
            }
        });

        free_gpio_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = MainApp.getCustomApi().gpioFree(Integer.parseInt(gpio_text.getText().toString()));
                Log.e(TAG, "gpioFree " + ok);
                gpio_showInfo.setText("gpioFree " + ok);
            }
        });

    }

}
