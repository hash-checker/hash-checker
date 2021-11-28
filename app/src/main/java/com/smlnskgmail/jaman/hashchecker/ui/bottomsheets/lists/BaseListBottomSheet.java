package com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

public abstract class BaseListBottomSheet<T extends ListItem> extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.bottom_sheet_list_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }
        RecyclerView rvItems = view.findViewById(R.id.rv_bottom_sheet_list_items);
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItems.setAdapter(getItemsAdapter());
    }

    @NonNull
    protected abstract BaseListAdapter<T> getItemsAdapter();

    @Nullable
    public String key() {
        return getClass().getCanonicalName();
    }

}
