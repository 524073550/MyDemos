package com.xingrong.easypermissionsdemo;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private String[] mStrings = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.apply)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

    }
    @AfterPermissionGranted(1)
    public void checkPermissions() {
        if (EasyPermissions.hasPermissions(MainActivity.this, mStrings)) {
            Toast.makeText(this,"全部授权ok",Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(MainActivity.this, "需要打开相机权限和位置权限", 1, mStrings);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        /*for (String perm : perms) {
            Log.e("onPermissionsGranted ==",perm);
        }
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT)
                .show();*/
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这里需要重新设置Rationale和title，否则默认是英文格式
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置屏幕以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }else if (!EasyPermissions.hasPermissions(this, new String[]{Manifest.permission.CAMERA})) {
            //这里响应的是AppSettingsDialog点击取消按钮的效果
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (!EasyPermissions.hasPermissions(this, new String[]{Manifest.permission.CAMERA})) {
                //这里响应的是AppSettingsDialog点击取消按钮的效果
                finish();
            }
        }
    }
}
