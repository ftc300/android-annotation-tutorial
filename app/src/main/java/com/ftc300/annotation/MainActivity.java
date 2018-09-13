package com.ftc300.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.binder.Binding;
import com.ftc300.lib.annotations.BindView;
import com.ftc300.lib.annotations.OnClick;
import com.ftc300.lib.annotations.FTC300_CLASS;
import com.ftc300.lib.annotations.FTC300_RUNTIME;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @FTC300_CLASS("ftc300_class")
    String ftcClass;

    @FTC300_RUNTIME("ftc300_runtime")
    String ftcRuntime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);
        Log.d("MainActivity",TextUtils.concat("FtcClass:",ftcClass,",FtcSource:",ftcRuntime).toString());
        Toast.makeText(this, TextUtils.concat("FtcClass:",ftcClass,",FtcSource:",ftcRuntime),Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.bt_1)
    void bt1Click(View v) {
        tvContent.setText("Button 1 Clicked");
    }

    @OnClick(R.id.bt_2)
    void bt2Click(View v) {
        tvContent.setText("Button 2 Clicked");
    }







}
