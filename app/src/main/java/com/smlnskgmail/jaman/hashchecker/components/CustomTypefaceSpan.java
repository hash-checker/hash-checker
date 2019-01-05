package com.smlnskgmail.jaman.hashchecker.components;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface customType;

    public CustomTypefaceSpan(@NonNull String family, @NonNull Typeface type) {
        super(family);
        customType = type;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint textPaint) {
        applyCustomTypeFace(textPaint, customType);
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint textPaint) {
        applyCustomTypeFace(textPaint, customType);
    }

    private static void applyCustomTypeFace(@NonNull Paint paint, @NonNull Typeface typeface) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        oldStyle = old == null ? 0 : old.getStyle();
        int fake = oldStyle & ~typeface.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }

}
