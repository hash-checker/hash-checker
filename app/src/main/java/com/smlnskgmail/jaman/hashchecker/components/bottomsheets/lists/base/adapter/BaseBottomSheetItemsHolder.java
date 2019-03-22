package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseBottomSheetItemsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_title)
    protected TextView itemInListTitle;

    @BindView(R.id.item_list_icon)
    protected ImageView itemPrimaryIcon;

    @BindView(R.id.item_list_additional_icon)
    protected ImageView itemAdditionalIcon;

    private BaseBottomSheetItemsAdapter itemsAdapter;

    private Context context;

    protected BaseBottomSheetItemsHolder(@NonNull View itemView,
                                         @NonNull BaseBottomSheetItemsAdapter itemsAdapter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemsAdapter = itemsAdapter;

        // Context with current theme
        context = this.itemsAdapter.getItemsBottomSheet().getContext();
    }

    protected void bind(@NonNull final ListItemMarker listItemMarker) {
        itemInListTitle.setText(context.getText(listItemMarker.getTitleTextResId()));
        int primaryIconResId = listItemMarker.getPrimaryIconResId();
        if (primaryIconResId != -1) {
            itemPrimaryIcon.setImageResource(listItemMarker.getPrimaryIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context, itemPrimaryIcon.getDrawable());
        }
        itemPrimaryIcon.setVisibility(getConditionToPrimaryIconVisibleState()
                ? View.VISIBLE : View.GONE);
        int additionalIconResId = listItemMarker.getAdditionalIconResId();
        if (additionalIconResId != -1) {
            itemAdditionalIcon.setImageResource(listItemMarker.getAdditionalIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context,
                    itemAdditionalIcon.getDrawable());
        }
        itemAdditionalIcon.setVisibility(getConditionToAdditionalIconVisibleState()
                ? View.VISIBLE : View.GONE);
    }

    protected boolean getConditionToPrimaryIconVisibleState() {
        return false;
    }

    protected boolean getConditionToAdditionalIconVisibleState() {
        return false;
    }

    @OnClick
    void onItemSelect() {
        callItemClick();
    }

    protected void callItemClick() {}

    @NonNull
    protected BaseBottomSheetItemsAdapter getItemsAdapter() {
        return itemsAdapter;
    }

    public Context getContext() {
        return context;
    }

}
