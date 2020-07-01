package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

public abstract class BaseListBottomSheet<T> extends BaseBottomSheet {

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView bottomSheetItems = view.findViewById(
                R.id.rv_bottom_sheet_list_items
        );
        bottomSheetItems.setLayoutManager(
                new LinearLayoutManager(
                        getContext()
                )
        );
        bottomSheetItems.setAdapter(getItemsAdapter());
    }

    protected abstract BaseListAdapter getItemsAdapter();

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_list_items;
    }

}
