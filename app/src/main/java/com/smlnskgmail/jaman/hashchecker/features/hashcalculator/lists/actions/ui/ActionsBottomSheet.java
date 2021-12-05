package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.Action;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.List;

abstract class ActionsBottomSheet extends BaseListBottomSheet<Action> {

    @NonNull
    @Override
    protected BaseListAdapter<Action> getItemsAdapter() {
        Fragment parentFragment = getParentFragmentManager().findFragmentByTag(BaseFragment.CURRENT_FRAGMENT_TAG);
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
    protected abstract ThemeConfig themeHelper();

    @NonNull
    protected abstract List<Action> getActions();

}
