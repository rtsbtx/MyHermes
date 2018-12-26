package com.myhermes.demo.myhermes.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myhermes.demo.myhermes.R;
import com.myhermes.demo.myhermeslib.IMyAidlInterface;
import com.myhermes.demo.myhermeslib.MyHermesManager;
import com.myhermes.demo.myhermeslib.MyHermesService;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClassName("com.myhermes.testmyhermesmainprocess", MyHermesService.class.getName());
        MyHermesManager.getSingleton().connectMainProcess(getApplication(), intent, new MyHermesManager.CallBack() {
            @Override
            public void onConnectedSuccess(IMyAidlInterface iMyAidlInterface) {
                ISC isc = MyHermesManager.getSingleton().getSingleton(ISC.class, iMyAidlInterface);
                String str = isc.doTest("test");

                System.out.println("str : " + str);
            }
        });

    }

}
