package com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.actions;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.actions.types.UserActionTarget;

public class ActionsBottomSheet extends BaseListBottomSheet {

    private UserActionTarget userActionTarget;

    public void setUserActionTarget(UserActionTarget userActionTarget) {
        this.userActionTarget = userActionTarget;
    }

    @Override
    protected BaseListAdapter getItemsAdapter() {
        return new ActionsListAdapter(getItems(), this, userActionTarget);
    }

}
