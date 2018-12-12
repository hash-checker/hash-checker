package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.IBottomSheetItem;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.IHashTypeSelectListener;

import java.util.List;

import butterknife.BindView;

public abstract class BaseItemsBottomSheet extends BaseBottomSheet {

    @BindView(R.id.recycler_bottom_sheet_items) protected RecyclerView bottomSheetItems;

    private List<? extends IBottomSheetItem> bottomSheetItemsList;

    public abstract boolean hideAdditionalIcons();

    @Nullable
    public IHashTypeSelectListener callback() {
        return null;
    }

    @Nullable
    public String getSelectedTypeAsString() {
        return null;
    }

    public void setBottomSheetItemsList(@NonNull List<? extends IBottomSheetItem> bottomSheetItemsList) {
        this.bottomSheetItemsList = bottomSheetItemsList;
    }

    @Override
    public void initUI() {
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        BottomSheetItemsAdapter bottomSheetItemsAdapter = new BottomSheetItemsAdapter(bottomSheetItemsList) {
            @Override
            public IHashTypeSelectListener getOnItemSelectedCallback() {
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

}
