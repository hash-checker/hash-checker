package com.smlnskgmail.jaman.hashchecker.runner;

import android.os.Bundle;
import android.util.Log;

import androidx.test.runner.AndroidJUnitRunner;

import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class AndroidJacocoTestRunner extends AndroidJUnitRunner {

    @SuppressWarnings({"rawtypes", "unchecked"})
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
                System.out.write(trace.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e1) {
                LogUtils.e(e1);
            }
        }
        super.finish(resultCode, results);
    }

}
