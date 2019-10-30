package com.smlnskgmail.jaman.hashchecker.components.containers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptiveRecyclerView extends RecyclerView {

    private View viewEmptyMessage;

    private final AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        private void checkItemsAvailable() {
            Adapter adapter = getAdapter();
            if (adapter != null && viewEmptyMessage != null) {
                if (adapter.getItemCount() == 0) {
                    viewEmptyMessage.setVisibility(View.VISIBLE);
                    setVisibility(GONE);
                } else {
                    viewEmptyMessage.setVisibility(View.GONE);
                    setVisibility(VISIBLE);
                }
            }
        }

        @Override
        public void onChanged() {
            checkItemsAvailable();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkItemsAvailable();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkItemsAvailable();
        }
    };

    public AdaptiveRecyclerView(Context context) {
        super(context);
    }

    public AdaptiveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Nullable
    public View getViewEmptyMessage() {
        return viewEmptyMessage;
    }

    public void setEmptyMessageView(@NonNull View emptyMessage) {
        this.viewEmptyMessage = emptyMessage;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(adapterDataObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
            adapterDataObserver.onChanged();
        }
    }

}
