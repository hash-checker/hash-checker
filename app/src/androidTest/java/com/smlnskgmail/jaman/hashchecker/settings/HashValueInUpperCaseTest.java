package com.smlnskgmail.jaman.hashchecker.settings;

import com.smlnskgmail.jaman.hashchecker.R;

public class HashValueInUpperCaseTest extends BaseSettingsTest {

    @Override
    public void runTest() throws InterruptedException {
        updateBooleanValue(
                activityTestRule.getActivity().getString(R.string.key_upper_case),
                true
        );
        generateHashValueFromText();
        textEquals(TEST_RESULT.toUpperCase(), R.id.et_field_generated_hash);
    }

}
