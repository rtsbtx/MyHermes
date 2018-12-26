package com.myhermes.testmyhermesmainprocess;

import com.myhermes.demo.myhermeslib.MethodId;

public class SingletonClass {

    private SingletonClass(){};

    private static SingletonClass singletonClass = null;

    public static synchronized SingletonClass getSingleton(){
        if(singletonClass == null) singletonClass = new SingletonClass();
        return singletonClass;
    }

    @MethodId(value = "midDoTest")
    public String doTest(String testParam){
        System.out.println("调用了SingletonClass的doTest方法");
        return testParam;
    }

}
