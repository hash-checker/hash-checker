package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseBottomSheetListHolder extends RecyclerView.ViewHolder {

    private TextView tvItemTitle;

    private ImageView ivItemPrimaryIcon;
    private ImageView ivItemAdditionalIcon;

    private BaseBottomSheetListAdapter listAdapter;

    private Context context;

    protected BaseBottomSheetListHolder(@NonNull View itemView,
                                        @NonNull BaseBottomSheetListAdapter listAdapter) {
        super(itemView);
        this.listAdapter = listAdapter;

        // Context with current theme
        context = this.listAdapter.getBottomSheet().getContext();
        tvItemTitle = itemView.findViewById(R.id.tv_item_list_title);
        ivItemPrimaryIcon = itemView.findViewById(R.id.iv_item_list_icon);
        ivItemAdditionalIcon = itemView.findViewById(R.id.iv_item_list_additional_icon);
    }

    protected void bind(@NonNull final ListItemMarker listItemMarker) {
        itemView.setOnClickListener(v -> callItemClick());
        tvItemTitle.setText(context.getText(listItemMarker.getTitleTextResId()));
        int primaryIconResId = listItemMarker.getPrimaryIconResId();
        if (primaryIconResId != -1) {
            ivItemPrimaryIcon.setImageResource(listItemMarker.getPrimaryIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context, ivItemPrimaryIcon.getDrawable());
        }
        ivItemPrimaryIcon.setVisibility(getConditionToPrimaryIconVisibleState() ? View.VISIBLE : View.GONE);
        int additionalIconResId = listItemMarker.getAdditionalIconResId();
        if (additionalIconResId != -1) {
            ivItemAdditionalIcon.setImageResource(listItemMarker.getAdditionalIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context, ivItemAdditionalIcon.getDrawable());
        }
        ivItemAdditionalIcon.setVisibility(getConditionToAdditionalIconVisibleState() ? View.VISIBLE : View.GONE);
    }

    protected boolean getConditionToPrimaryIconVisibleState() {
        return false;
    }

    protected boolean getConditionToAdditionalIconVisibleState() {
        return false;
    }

    protected void callItemClick() {}

    protected ImageView getIvItemAdditionalIcon() {
        return ivItemAdditionalIcon;
    }

    @NonNull
    protected BaseBottomSheetListAdapter getListAdapter() {
        return listAdapter;
    }

    protected Context getContext() {
        return context;
    }

}
