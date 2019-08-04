package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListMarker;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseBottomSheetListHolder extends RecyclerView.ViewHolder {

    private TextView tvItemTitle;

    private ImageView ivItemPrimaryIcon;
    private ImageView ivItemAdditionalIcon;

    private Context context;

    protected BaseBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext) {
        super(itemView);

        // Context with current theme
        context = themeContext;
        tvItemTitle = itemView.findViewById(R.id.tv_item_list_title);
        ivItemPrimaryIcon = itemView.findViewById(R.id.iv_item_list_icon);
        ivItemAdditionalIcon = itemView.findViewById(R.id.iv_item_list_additional_icon);
    }

    protected void bind(@NonNull final ListMarker listMarker) {
        itemView.setOnClickListener(v -> callItemClick());
        tvItemTitle.setText(context.getText(listMarker.getTitleTextResId()));
        int primaryIconResId = listMarker.getPrimaryIconResId();
        if (primaryIconResId != -1) {
            ivItemPrimaryIcon.setImageResource(listMarker.getPrimaryIconResId());
            UIUtils.colorizeImageSourceToAccentColor(context, ivItemPrimaryIcon.getDrawable());
        }
        ivItemPrimaryIcon.setVisibility(getConditionToPrimaryIconVisibleState() ? View.VISIBLE : View.GONE);
        int additionalIconResId = listMarker.getAdditionalIconResId();
        if (additionalIconResId != -1) {
            ivItemAdditionalIcon.setImageResource(listMarker.getAdditionalIconResId());
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

    protected Context getContext() {
        return context;
    }

}
