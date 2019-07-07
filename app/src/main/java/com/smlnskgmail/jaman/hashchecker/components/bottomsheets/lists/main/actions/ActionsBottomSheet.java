package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.OnUserActionClickListener;

import java.util.ArrayList;
import java.util.List;

public class ActionsBottomSheet extends BaseListBottomSheet {

    private List<ListItemMarker> actions = new ArrayList<>();
    private OnUserActionClickListener onUserActionClickListener;

    public void setActions(List<Action> actions) {
        this.actions.addAll(actions);
    }

    public void setOnUserActionClickListener(OnUserActionClickListener onUserActionClickListener) {
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected BaseBottomSheetListAdapter getItemsAdapter() {
        return new ActionsBottomSheetListAdapter(actions, this, onUserActionClickListener);
    }

}
