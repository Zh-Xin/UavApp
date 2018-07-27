package com.zx.sdl.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zx.sdl.R;
import com.zx.sdl.permission.PermissionsChecker;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 0;//请求码

    private String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    private ImageView product_iv;

    private TextView product_name_tv;
    private TextView version_tv;

    private Button quick_start_btn;
    private Button control_btn;
    private Button learn_btn;
    private Button album_btn;
    private Button support_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViews();
        initOnClick();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPermission();
    }

    private void getPermission() {
        PermissionsChecker permissionsChecker = new PermissionsChecker(this);
        if (permissionsChecker.lacksPermissions(PERMISSIONS))
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        else {
            if (!isLocationEnabled()) //判断是否开启了位置服务
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    private void initOnClick() {
        quick_start_btn.setOnClickListener(this);
        control_btn.setOnClickListener(this);
        learn_btn.setOnClickListener(this);
        album_btn.setOnClickListener(this);
        support_btn.setOnClickListener(this);
    }

    private void findViews() {
        product_iv = findViewById(R.id.product_iv);
        product_name_tv = findViewById(R.id.product_name_tv);
        version_tv = findViewById(R.id.version_tv);
        quick_start_btn = findViewById(R.id.quick_start_btn);
        control_btn = findViewById(R.id.control_btn);
        learn_btn = findViewById(R.id.learn_btn);
        album_btn = findViewById(R.id.album_btn);
        support_btn = findViewById(R.id.support_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quick_start_btn:
                startActivity(new Intent(WelcomeActivity.this, QuickStartActivity.class));
                break;
            case R.id.control_btn:
                startActivity(new Intent(WelcomeActivity.this, ControlActivity.class));
                break;
            case R.id.learn_btn:
                startActivity(new Intent(WelcomeActivity.this, LearnActivity.class));
                break;
            case R.id.album_btn:
                startActivity(new Intent(WelcomeActivity.this, SnapshotActivity.class));
                break;
            case R.id.support_btn:
                startActivity(new Intent(WelcomeActivity.this, SupportActivity.class));
                break;
        }
    }

    private boolean isLocationEnabled() {
        int locationMode = 0;
        try {
            locationMode = Settings.Secure.getInt(getContentResolver(),
                    Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }

}
