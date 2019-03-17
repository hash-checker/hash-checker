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

    @BindView(R.id.item_list_title) protected TextView bottomSheetItemInListTitle;
    @BindView(R.id.item_list_icon) protected ImageView bottomSheetPrimaryIcon;
    @BindView(R.id.item_list_additional_icon) protected ImageView bottomSheetItemAdditionalIcon;

    private BaseBottomSheetItemsAdapter baseBottomSheetItemsAdapter;

    private Context context;

    protected BaseBottomSheetItemsHolder(@NonNull View itemView,
                                         @NonNull BaseBottomSheetItemsAdapter
                                                 baseBottomSheetItemsAdapter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.baseBottomSheetItemsAdapter = baseBottomSheetItemsAdapter;

        // Context with current theme
        context = this.baseBottomSheetItemsAdapter.getBaseItemsBottomSheet().getContext();
    }

    void bind(@NonNull final ListItemMarker listItemMarker) {
        bottomSheetItemInListTitle.setText(context.getText(listItemMarker.getTitleTextResId()));
        int primaryIconResId = listItemMarker.getPrimaryIconResId();
        if (primaryIconResId != -1) {
            bottomSheetPrimaryIcon.setImageResource(listItemMarker.getPrimaryIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context, bottomSheetPrimaryIcon.getDrawable());
        }
        bottomSheetPrimaryIcon.setVisibility(getConditionToPrimaryIconVisibleState()
                ? View.VISIBLE : View.GONE);
        int additionalIconResId = listItemMarker.getAdditionalIconResId();
        if (additionalIconResId != -1) {
            bottomSheetItemAdditionalIcon.setImageResource(listItemMarker.getAdditionalIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context,
                    bottomSheetItemAdditionalIcon.getDrawable());
        }
        bottomSheetItemAdditionalIcon.setVisibility(getConditionToAdditionalIconVisibleState()
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
    protected BaseBottomSheetItemsAdapter getBaseBottomSheetItemsAdapter() {
        return baseBottomSheetItemsAdapter;
    }

    public Context getContext() {
        return context;
    }

}
