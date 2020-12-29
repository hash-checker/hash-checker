package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.Action;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SourceSelectActionsBottomSheet extends ActionsBottomSheet {

    @NotNull
    @Override
    List<Action> getActions() {
        return Arrays.asList(
                Action.TEXT,
                Action.FILE
        );
    }

}
