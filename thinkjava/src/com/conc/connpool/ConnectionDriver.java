package com.conc.connpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                TimeUnit.SECONDS.sleep(2);
            }
            return null;
        }
    }

}
