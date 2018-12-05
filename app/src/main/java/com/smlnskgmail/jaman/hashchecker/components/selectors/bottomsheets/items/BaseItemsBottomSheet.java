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

    @BindView(R.id.items_list) protected RecyclerView types;

    private List<? extends IBottomSheetItem> items;

    public abstract boolean hideMarks();

    @Nullable
    public IHashTypeSelectListener callback() {
        return null;
    }

    @Nullable
    public String getSelectedTypeAsString() {
        return null;
    }

    public void setItems(@NonNull List<? extends IBottomSheetItem> items) {
        this.items = items;
    }

    @Override
    public void initUI() {
        types.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemsAdapter itemsAdapter = new ItemsAdapter(items) {
            @Override
            public IHashTypeSelectListener getCallback() {
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
            public boolean hideMarks() {
                return BaseItemsBottomSheet.this.hideMarks();
            }
        };
        types.setAdapter(itemsAdapter);
    }

}
