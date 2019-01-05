package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.ListItemElement;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.utils.Preferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BottomSheetItemsAdapter extends RecyclerView.Adapter<BottomSheetItemsAdapter.ItemsHolder> {

    class ItemsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_list_icon) protected ImageView bottomSheetItemIcon;
        @BindView(R.id.item_list_additional_icon) protected ImageView bottomSheetItemAdditionalIcon;

        ItemsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void onItemSelect() {
            HashTypes hashType = (HashTypes) bottomSheetItemsList.get(getAdapterPosition());
            boolean visible = bottomSheetItemAdditionalIcon.getVisibility() == View.VISIBLE;
            bottomSheetItemAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
            getOnItemSelectedCallback().onSelect(hashType);
            getBottomSheet().dismissAllowingStateLoss();
        }

        void bind(@NonNull final ListItemElement listItemElement) {
            final Context context = itemView.getContext();
            final HashTypes hashType = (HashTypes) listItemElement;
            ((TextView) itemView.findViewById(R.id.item_list_title)).setText(hashType.getTypeAsString(context));
            if (hideAdditionalIcon()) {
                bottomSheetItemAdditionalIcon.setVisibility(getSelectedTypeAsString().equals(hashType.getTypeAsString(context)) ? View.VISIBLE : View.INVISIBLE);
            } else {
                bottomSheetItemAdditionalIcon.setVisibility(Preferences.typeIsSelected(context, hashType.getPreferenceKey()) ? View.VISIBLE : View.INVISIBLE);
            }
        }

    }

    private List<? extends ListItemElement> bottomSheetItemsList;

    public abstract OnHashTypeSelectListener getOnItemSelectedCallback();
    public abstract String getSelectedTypeAsString();
    public abstract BaseItemsBottomSheet getBottomSheet();
    public abstract boolean hideAdditionalIcon();

    BottomSheetItemsAdapter(@NonNull List<? extends ListItemElement> bottomSheetItemsList) {
        this.bottomSheetItemsList = bottomSheetItemsList;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        holder.bind(bottomSheetItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return bottomSheetItemsList.size();
    }

}
