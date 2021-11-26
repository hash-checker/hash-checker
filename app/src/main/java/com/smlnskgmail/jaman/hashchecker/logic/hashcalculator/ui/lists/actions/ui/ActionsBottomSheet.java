package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.Action;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.List;

abstract class ActionsBottomSheet extends BaseListBottomSheet<Action> {

    @NonNull
    @Override
    protected BaseListAdapter<Action> getItemsAdapter() {
        Fragment parentFragment = getParentFragmentManager().findFragmentByTag(
                BaseFragment.CURRENT_FRAGMENT_TAG
        );
        UserActionTarget userActionTarget = parentFragment == null
                ? null
                : (UserActionTarget) parentFragment;
        return new ActionsListAdapter(
                getActions(),
                this,
                userActionTarget,
                themeHelper()
        );
    }

    @NonNull
    protected abstract ThemeHelper themeHelper();

    @NonNull
    protected abstract List<Action> getActions();

}
