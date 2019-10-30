package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListMarker;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

public abstract class BaseBottomSheetListHolder extends RecyclerView.ViewHolder {

    private final TextView tvItemTitle;

    private final ImageView ivItemPrimaryIcon;
    private final ImageView ivItemAdditionalIcon;

    private final Context context;

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
        tvItemTitle.setText(listMarker.getTitle(context));
        int primaryIconResId = listMarker.getPrimaryIconResId();
        if (primaryIconResId != -1) {
            ivItemPrimaryIcon.setImageResource(listMarker.getPrimaryIconResId());
            UITools.applyAccentColorToImage(context, ivItemPrimaryIcon.getDrawable());
        }
        ivItemPrimaryIcon.setVisibility(getConditionToPrimaryIconVisibleState() ? View.VISIBLE : View.GONE);
        int additionalIconResId = listMarker.getAdditionalIconResId();
        if (additionalIconResId != -1) {
            ivItemAdditionalIcon.setImageResource(listMarker.getAdditionalIconResId());
            UITools.applyAccentColorToImage(context, ivItemAdditionalIcon.getDrawable());
        }
        ivItemAdditionalIcon.setVisibility(getConditionToAdditionalIconVisibleState() ? View.VISIBLE : View.INVISIBLE);
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
