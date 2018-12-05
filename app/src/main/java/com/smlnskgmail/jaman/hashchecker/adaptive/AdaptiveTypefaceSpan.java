package com.smlnskgmail.jaman.hashchecker.adaptive;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class AdaptiveTypefaceSpan extends TypefaceSpan {

    private final Typeface customType;

    public AdaptiveTypefaceSpan(String family, Typeface type) {
        super(family);
        customType = type;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        applyCustomTypeFace(ds, customType);
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint paint) {
        applyCustomTypeFace(paint, customType);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;

        Typeface old = paint.getTypeface();
        oldStyle = old == null ? 0 : old.getStyle();

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }

}
