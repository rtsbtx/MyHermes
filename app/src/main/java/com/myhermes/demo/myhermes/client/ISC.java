package com.myhermes.demo.myhermes.client;

import com.myhermes.demo.myhermeslib.MethodId;

public interface ISC {

    @MethodId(value = "midDoTest")
    String doTest(String testParam);

}
