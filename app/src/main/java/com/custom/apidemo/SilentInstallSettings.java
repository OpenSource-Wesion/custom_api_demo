package com.custom.apidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.custom.ICustomApiCallBack;

public class SilentInstallSettings extends Activity implements View.OnClickListener {
    private static final String TAG = "SilentInstallSettings";
    private Button btnInstall;
    private Button btnInstallLaunch;
    private Button btnUninstall;
    private EditText etApkPath;
    private EditText etPkgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.silentinstall_main);

        etApkPath = (EditText)findViewById(R.id.et_apkPath);
        etPkgName = (EditText)findViewById(R.id.et_pkgName);

        btnInstall = (Button) findViewById(R.id.btn_install);
        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ret = MainApp.getCustomApi().silentInstall(etApkPath.getText().toString());
                Log.e(TAG, "btnInstall ret " + ret);
                if (ret) {
                    Toast.makeText(SilentInstallSettings.this, "install finish success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SilentInstallSettings.this, "install finish error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnInstallLaunch = (Button) findViewById(R.id.btn_installLaunch);
        btnInstallLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainApp.getCustomApi().silentInstall(etApkPath.getText().toString(), true, new ICustomApiCallBack() {
                    @Override
                    public void silentInstallCallBack(String s, boolean b) {
                        Toast.makeText(SilentInstallSettings.this, "silentInstallCallBack :" + s + " " + b, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void silentUninstallCallBack(String s, int i) {
                    }

                });
            }
        });

        btnUninstall = (Button) findViewById(R.id.btn_uninstall);
        btnUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ret = MainApp.getCustomApi().silentUnInstall(etPkgName.getText().toString());
                Log.e(TAG, "btnUninstall ret " + ret);
                if (ret) {
                    Toast.makeText(SilentInstallSettings.this, "uninstall finish success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SilentInstallSettings.this, "uninstall finish error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}