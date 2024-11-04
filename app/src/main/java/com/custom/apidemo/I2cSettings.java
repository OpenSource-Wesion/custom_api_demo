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

import java.util.regex.Pattern;

public class I2cSettings extends Activity implements OnClickListener {
    private static final String TAG = "I2cSettings";
    Button btnI2cRead, btnI2cWrite;
    EditText etBus, etAddr, etReg, etValue;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c_main);
        etBus = (EditText)findViewById(R.id.et_bus);
        etAddr = (EditText)findViewById(R.id.et_addr);
        etReg = (EditText)findViewById(R.id.et_reg);
        etValue = (EditText)findViewById(R.id.et_val);
        tvResult = (TextView)findViewById(R.id.tv_result);

        btnI2cRead = (Button)findViewById(R.id.i2c_read_btn);
        btnI2cWrite = (Button)findViewById(R.id.i2c_write_btn);

        btnI2cRead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String bus = etBus.getText().toString();
                String addr = etAddr.getText().toString();
                String reg = etReg.getText().toString();
                if(isInteger(bus) && isInteger(addr) && isInteger(reg)) {
                    int ret = MainApp.getCustomApi().i2cReadByteData(Integer.valueOf(bus), Integer.valueOf(addr), Integer.valueOf(reg));
                    etValue.setText(String.valueOf(ret));
                    if(ret > 0) {
                        tvResult.setText("read success");
                    } else {
                        tvResult.setText("read failed");
                    }
                } else {
                    Log.e(TAG, "btnI2cRead parameter error");
                }
            }
        });

        btnI2cWrite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String bus = etBus.getText().toString();
                String addr = etAddr.getText().toString();
                String reg = etReg.getText().toString();
                String val = etValue.getText().toString();
                if(isInteger(bus) && isInteger(addr) && isInteger(reg) && isInteger(val)) {
                    MainApp.getCustomApi().i2cWriteByteData(Integer.valueOf(bus), Integer.valueOf(addr), Integer.valueOf(reg), Integer.valueOf(val));
                    int ret = MainApp.getCustomApi().i2cReadByteData(Integer.valueOf(bus), Integer.valueOf(addr), Integer.valueOf(reg));
                    etValue.setText(String.valueOf(ret));
                    if(ret > 0 && (ret == Integer.valueOf(val))) {
                        tvResult.setText("write success");
                    } else {
                        tvResult.setText("write failed");
                    }
                } else {
                    Log.e(TAG, "btnI2cWrite parameter error");
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
