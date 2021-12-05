package com.smlnskgmail.jaman.hashchecker.settings;

import com.smlnskgmail.jaman.hashchecker.R;

public class HashValueInLowerCaseTest extends BaseSettingsTest {

    @Override
    public void runTest() throws InterruptedException {
        updateBooleanValue(
                activityTestRule.getActivity().getString(R.string.key_upper_case),
                false
        );
        generateHashValueFromText();
        textEquals(TEST_RESULT, R.id.et_field_generated_hash);
    }

}
