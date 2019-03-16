package com.smlnskgmail.jaman.hashchecker.support.preferences;

public interface Constants {

    interface Requests {

        int FILE_SELECT_REQUEST = 1;

    }

    interface ShortcutActions {

        String ACTION_START_WITH_TEXT_SELECTION
                = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT_SELECTION";
        String ACTION_START_WITH_FILE_SELECTION
                = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE_SELECTION";

    }

    interface ShortcutIds {

        String SHORTCUT_TEXT_ID = "shortcut_text";
        String SHORTCUT_FILE_ID = "shortcut_file";

    }

    interface Tags {

        String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT";
        String CURRENT_BOTTOM_SHEET_TAG = "CURRENT_BOTTOM_SHEET_TAG";

    }

    interface Data {

        String URI_FROM_EXTERNAL_APP = "com.smlnskgmail.jaman.hashchecker.URI_FROM_EXTERNAL_APP";

    }

}
