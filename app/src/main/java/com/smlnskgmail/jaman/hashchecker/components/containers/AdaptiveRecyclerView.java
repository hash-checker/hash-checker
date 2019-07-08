package com.smlnskgmail.jaman.hashchecker.components.containers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class AdaptiveRecyclerView extends RecyclerView {

    private View emptyMessage;

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        private void checkItemsAvailable() {
            Adapter adapter = getAdapter();
            if (adapter != null && emptyMessage != null) {
                if (adapter.getItemCount() == 0) {
                    emptyMessage.setVisibility(View.VISIBLE);
                    setVisibility(GONE);
                } else {
                    emptyMessage.setVisibility(View.GONE);
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
    public View getEmptyMessage() {
        return emptyMessage;
    }

    public void setEmptyMessageView(@NonNull View emptyMessage) {
        this.emptyMessage = emptyMessage;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(emptyObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
            emptyObserver.onChanged();
        }
    }

}
