package com.myhermes.demo.myhermeslib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.RemoteException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class MyHermesManager {

    private MyHermesManager(){};

    private static MyHermesManager myHermesManager = null;

    public static synchronized MyHermesManager getSingleton(){
        if(myHermesManager == null) myHermesManager = new MyHermesManager();
        return myHermesManager;
    }


    //server
    Class<?> registerClas;
    public void registerSingletonClass(Class<?> cls){
        this.registerClas = cls;
    }
    public Class<?> getRegisterClas() {
        return registerClas;
    }





//============================================================================================================





    //client
    public void connectMainProcess(Context ctx, Intent intent, final CallBack callBack){
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                callBack.onConnectedSuccess(iMyAidlInterface);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        ctx.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public <T> T getSingleton(Class<T> interfaceForProxy, IMyAidlInterface iMyAidlInterface){
        try {
            iMyAidlInterface.sendMessageToServer(new Request(null, null), new Response());
            return (T)Proxy.newProxyInstance(interfaceForProxy.getClassLoader(), new Class<?>[]{interfaceForProxy}, new InstanceProxy(iMyAidlInterface));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface CallBack {
        void onConnectedSuccess(IMyAidlInterface iMyAidlInterface);
    }

    static class InstanceProxy implements InvocationHandler {

        IMyAidlInterface iMyAidlInterface;

        public InstanceProxy(IMyAidlInterface iMyAidlInterface) {
            this.iMyAidlInterface = iMyAidlInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Request request = new Request(args, method.getAnnotation(MethodId.class).value());
            Response response = new Response();
            iMyAidlInterface.sendMessageToServer(request, response);
            return response.getReturnData();
        }

    }

}
