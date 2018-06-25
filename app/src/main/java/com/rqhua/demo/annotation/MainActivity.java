package com.rqhua.demo.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rqhua.demo.annotation.demo1.MyBindView;
import com.rqhua.demo.annotation.demo1.MyKnife;
import com.rqhua.demo.javatest.ReflectParam;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @MyBindView(R.id.text)

    @BindView(R.id.text)
    TextView textView;


    @ReflectParam("asfasd")
    String name;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyKnife.bind(this);
        ButterKnife.bind(this);
        textView.setText("测试一下");
        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: " + name);
    }
}
