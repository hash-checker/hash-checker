package com.smlnskgmail.jaman.hashchecker.components.vibrator;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;

public class Vibrator {

    private static final int VIBRATION_LENGTH = 30;

    private final android.os.Vibrator vibrator;

    public Vibrator(Context context) {
        vibrator = (android.os.Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void vibrate() {
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                        VibrationEffect.createOneShot(
                                VIBRATION_LENGTH,
                                VibrationEffect.DEFAULT_AMPLITUDE
                        )
                );
            } else {
                vibrator.vibrate(VIBRATION_LENGTH);
            }
        }
    }

}
