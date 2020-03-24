package com.smlnskgmail.jaman.hashchecker.runner;

import android.os.Bundle;
import android.util.Log;

import androidx.test.runner.AndroidJUnitRunner;

import com.smlnskgmail.jaman.hashchecker.logic.logs.L;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class AndroidJacocoTestRunner extends AndroidJUnitRunner {

    @Override
    public void finish(int resultCode, Bundle results) {
        try {
            Class rt = Class.forName("org.jacoco.agent.rt.RT");
            Method getAgent = rt.getMethod("getAgent");
            Method dump = getAgent.getReturnType().getMethod("dump", boolean.class);
            Object agent = getAgent.invoke(null);
            dump.invoke(agent, false);
        } catch (Throwable e) {
            final String trace = Log.getStackTraceString(e);
            try {
                System.out.write(trace.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e1) {
                L.e(e1);
            }
        }
        super.finish(resultCode, results);
    }

}
