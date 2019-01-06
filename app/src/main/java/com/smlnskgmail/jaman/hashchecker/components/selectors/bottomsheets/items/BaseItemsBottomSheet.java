package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.ListItemElementMarker;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.OnHashTypeSelectListener;

import java.util.List;

import butterknife.BindView;

public abstract class BaseItemsBottomSheet extends BaseBottomSheet {

    @BindView(R.id.bottom_sheet_items)
    protected RecyclerView bottomSheetItems;

    private List<? extends ListItemElementMarker> bottomSheetItemsList;

    public void setBottomSheetItemsList(@NonNull List<? extends ListItemElementMarker> bottomSheetItemsList) {
        this.bottomSheetItemsList = bottomSheetItemsList;
    }

    @Override
    public void initUI() {
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        BottomSheetItemsAdapter bottomSheetItemsAdapter = new BottomSheetItemsAdapter(bottomSheetItemsList) {
            @Override
            public OnHashTypeSelectListener getOnItemSelectedCallback() {
                return callback();
            }

            @Override
            public String getSelectedTypeAsString() {
                return BaseItemsBottomSheet.this.getSelectedTypeAsString();
            }

            @Override
            public BaseItemsBottomSheet getBottomSheet() {
                return BaseItemsBottomSheet.this;
            }

            @Override
            public boolean hideAdditionalIcon() {
                return BaseItemsBottomSheet.this.hideAdditionalIcons();
            }
        };
        bottomSheetItems.setAdapter(bottomSheetItemsAdapter);
    }

    @Nullable
    public OnHashTypeSelectListener callback() {
        return null;
    }

    @Nullable
    public String getSelectedTypeAsString() {
        return null;
    }

    public abstract boolean hideAdditionalIcons();

}
