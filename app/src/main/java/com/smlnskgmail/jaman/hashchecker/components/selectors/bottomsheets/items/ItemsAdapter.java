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
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.IBottomSheetItem;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator.IHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.utils.Preferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {

    class ItemsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon) protected ImageView icon;
        @BindView(R.id.done) protected ImageView done;

        ItemsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void onItemSelect() {
            HashTypes hashType = (HashTypes) items.get(getAdapterPosition());
            boolean visible = done.getVisibility() == View.VISIBLE;
            done.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
            getCallback().onSelect(hashType);
            getBottomSheet().dismissAllowingStateLoss();
        }

        void bind(@NonNull final IBottomSheetItem IBottomSheetItem) {
            final Context context = itemView.getContext();
            final HashTypes hashType = (HashTypes) IBottomSheetItem;
            ((TextView) itemView.findViewById(R.id.type)).setText(hashType.getTypeAsString());
            if (hideMarks()) {
                done.setVisibility(getSelectedTypeAsString().equals(hashType.getTypeAsString()) ? View.VISIBLE : View.INVISIBLE);
            } else {
                done.setVisibility(Preferences.typeIsSelected(context, hashType.getKey()) ? View.VISIBLE : View.INVISIBLE);
            }
        }

    }

    public abstract IHashTypeSelectListener getCallback();
    public abstract String getSelectedTypeAsString();
    public abstract BaseItemsBottomSheet getBottomSheet();
    public abstract boolean hideMarks();

    private List<? extends IBottomSheetItem> items;

    ItemsAdapter(@NonNull List<? extends IBottomSheetItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
