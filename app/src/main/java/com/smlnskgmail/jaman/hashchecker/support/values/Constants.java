package com.smlnskgmail.jaman.hashchecker.support.values;

import android.view.inputmethod.EditorInfo;

public interface Constants {

    String URI_FROM_EXTERNAL_APP = "com.smlnskgmail.jaman.hashchecker.URI_FROM_EXTERNAL_APP";

    interface Text {

        int TEXT_MULTILINE = EditorInfo.TYPE_CLASS_TEXT
                | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE;
        int TEXT_MULTILINE_LINES_COUNT = 3;

        int TEXT_SINGLE_LINE = EditorInfo.TYPE_CLASS_TEXT;
        int TEXT_SINGLE_LINE_LINES_COUNT = 1;

    }

}
