package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItem;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import static com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItem.DEFAULT_ICON_VALUE;

public abstract class BaseListHolder<T extends ListItem> extends RecyclerView.ViewHolder {

    private final TextView tvItemTitle;

    private final ImageView ivItemPrimaryIcon;
    private final ImageView ivItemAdditionalIcon;

    private final Context context;

    protected BaseListHolder(
            @NonNull View itemView,
            @NonNull Context themeContext
    ) {
        super(itemView);

        // Context with current theme
        context = themeContext;
        tvItemTitle = itemView.findViewById(R.id.tv_item_list_title);
        ivItemPrimaryIcon = itemView.findViewById(R.id.iv_item_list_icon);
        ivItemAdditionalIcon = itemView.findViewById(
                R.id.iv_item_list_additional_icon
        );
    }

    protected void bind(
            @NonNull final T listItem
    ) {
        itemView.setOnClickListener(v -> callItemClick());
        tvItemTitle.setText(listItem.getTitle(context));
        int primaryIconResId = listItem.getPrimaryIconResId();
        if (primaryIconResId != DEFAULT_ICON_VALUE) {
            ivItemPrimaryIcon.setImageResource(
                    listItem.getPrimaryIconResId()
            );
            UIUtils.applyAccentColorToImage(
                    context,
                    ivItemPrimaryIcon.getDrawable()
            );
        }
        ivItemPrimaryIcon.setVisibility(
                getConditionToPrimaryIconVisibleState()
                        ? View.VISIBLE
                        : View.GONE
        );
        int additionalIconResId = listItem.getAdditionalIconResId();
        if (additionalIconResId != DEFAULT_ICON_VALUE) {
            ivItemAdditionalIcon.setImageResource(
                    listItem.getAdditionalIconResId()
            );
            UIUtils.applyAccentColorToImage(
                    context,
                    ivItemAdditionalIcon.getDrawable()
            );
        }
        ivItemAdditionalIcon.setVisibility(
                getConditionToAdditionalIconVisibleState()
                        ? View.VISIBLE
                        : View.INVISIBLE
        );
    }

    protected boolean getConditionToPrimaryIconVisibleState() {
        return false;
    }

    protected boolean getConditionToAdditionalIconVisibleState() {
        return false;
    }

    protected void callItemClick() {

    }

    protected ImageView getIvItemAdditionalIcon() {
        return ivItemAdditionalIcon;
    }

    protected Context getContext() {
        return context;
    }

}
