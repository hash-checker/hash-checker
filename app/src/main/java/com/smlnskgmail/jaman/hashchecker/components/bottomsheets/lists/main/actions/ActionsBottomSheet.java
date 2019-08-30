package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.UserActionTarget;

import java.util.ArrayList;
import java.util.List;

public class ActionsBottomSheet extends BaseListBottomSheet {

    private final List<ListMarker> actions = new ArrayList<>();
    private UserActionTarget userActionTarget;

    public void setActions(List<Action> actions) {
        this.actions.addAll(actions);
    }

    public void setUserActionTarget(UserActionTarget userActionTarget) {
        this.userActionTarget = userActionTarget;
    }

    @Override
    protected BaseBottomSheetListAdapter getItemsAdapter() {
        return new ActionsBottomSheetListAdapter(actions, this, userActionTarget);
    }

}
