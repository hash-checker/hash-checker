package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.Action;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class SourceSelectActionsBottomSheet extends ActionsBottomSheet {

    @Inject
    public ThemeConfig themeConfig;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    protected ThemeConfig themeHelper() {
        return themeConfig;
    }

    @NonNull
    @Override
    protected List<Action> getActions() {
        return Arrays.asList(
                Action.TEXT,
                Action.FILE,
                Action.FOLDER
        );
    }

}
