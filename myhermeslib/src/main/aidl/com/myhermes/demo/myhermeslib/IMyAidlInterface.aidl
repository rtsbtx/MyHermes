// IMyAidlInterface.aidl
package com.myhermes.demo.myhermeslib;

// Declare any non-default types here with import statements

import com.myhermes.demo.myhermeslib.Request;
import com.myhermes.demo.myhermeslib.Response;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void sendMessageToServer(in Request request, out Response response);

}
