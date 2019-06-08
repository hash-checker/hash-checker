package com.smlnskgmail.jaman.hashchecker.components.preferences;

public interface Constants {

    interface Requests {

        int FILE_SELECT_REQUEST = 1;
        int FILE_SELECT_REQUEST_FROM_APP_FILE_MANAGER = 2;

        int PERMISSION_STORAGE_REQUEST = 3;

    }

    interface RequestData {

        String FILE_SELECT_DATA = "file_select_data";

    }

    interface ShortcutActions {

        String ACTION_START_WITH_TEXT_SELECTION = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT_SELECTION";
        String ACTION_START_WITH_FILE_SELECTION = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE_SELECTION";

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

    interface FileNames {

        String BACK_FOLDER = "../";

    }

}
