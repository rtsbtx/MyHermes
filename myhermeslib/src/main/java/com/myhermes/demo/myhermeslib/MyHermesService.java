package com.myhermes.demo.myhermeslib;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyHermesService extends Service {

    IBinder iBinder = new IMyAidlInterface.Stub() {

        private Map<Class, Object> class_object = new ConcurrentHashMap<>();

        @Override
        public void sendMessageToServer(Request request, Response response) {

            if(request.getMethodId() == null){
                try {
                    Object objInstance = MyHermesManager
                            .getSingleton()
                            .getRegisterClas()
                            .getMethod("getSingleton", null)
                            .invoke(null, null);

                    class_object.put(MyHermesManager
                            .getSingleton()
                            .getRegisterClas(), objInstance);

                    response.setReturnData(null);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else{

                Object objInstance = class_object.get(MyHermesManager
                        .getSingleton()
                        .getRegisterClas());

                try {
                    Method[] ms = MyHermesManager.getSingleton().getRegisterClas().getMethods();
                    for(Method m : ms){
                        MethodId annotation = m.getAnnotation(MethodId.class);
                        if(annotation == null) continue;
                        if(annotation.value().equals(request.getMethodId())){
                            Object returnObj =  m.invoke(objInstance, request.getMethodParams());
                            response.setReturnData(returnObj);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public MyHermesService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }



}
