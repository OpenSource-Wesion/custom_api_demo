package com.custom.apidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class I2cSettings extends Activity implements OnClickListener {
    private static final String TAG = "I2cSettings";
    private Context mContext;
    Button i2c_read_btn, i2c_write_btn;
    EditText etAddr, etValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c_main);
        mContext = this;
        etAddr = (EditText)findViewById(R.id.et_addr);
        etValue = (EditText)findViewById(R.id.et_val);

        i2c_read_btn = (Button)findViewById(R.id.i2c_read_btn);
        i2c_write_btn = (Button)findViewById(R.id.i2c_write_btn);

        i2c_read_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInteger(etAddr.getText().toString())) {
                    etValue.setText(String.valueOf(MainApp.getCustomApi().i2cReadByteData(4, 0x41, Integer.valueOf(etAddr.getText().toString()))));
                }
            }
        });

        i2c_write_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInteger(etAddr.getText().toString()) && isInteger(etValue.getText().toString())) {
                    MainApp.getCustomApi().i2cWriteByteData(4, 0x41, Integer.valueOf(etAddr.getText().toString()), Integer.valueOf(etValue.getText().toString()));
                    etValue.setText(String.valueOf(MainApp.getCustomApi().i2cReadByteData(4, 0x41, Integer.valueOf(etAddr.getText().toString()))));
                }
            }
        });
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Override
    public void onClick(View v) {
    }
}
