package com.myhermes.testmyhermesmainprocess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myhermes.demo.myhermeslib.MyHermesManager;

public class MainProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHermesManager.getSingleton().registerSingletonClass(SingletonClass.class);

//        System.out.println(SingletonClass.getSingleton().doTest("MainProcessActivity"));

    }

}
