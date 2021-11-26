package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.Action;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class ActionSelectActionsBottomSheet extends ActionsBottomSheet {

    @Inject
    public ThemeHelper themeHelper;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    protected ThemeHelper themeHelper() {
        return themeHelper;
    }

    @NonNull
    @Override
    protected List<Action> getActions() {
        return Arrays.asList(
                Action.GENERATE,
                Action.COMPARE,
                Action.EXPORT_AS_TXT
        );
    }

}
