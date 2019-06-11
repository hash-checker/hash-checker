package com.smlnskgmail.jaman.hashchecker.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.components.actions.OnAppResume;
import com.smlnskgmail.jaman.hashchecker.components.actions.OnNavigationListener;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseFragment extends Fragment implements OnNavigationListener, OnAppResume {

    private ActionBar actionBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        onPostInitialize();
    }

    protected abstract void initializeUI(@NonNull View contentView);

    void onPostInitialize() {}

    @Override
    public void onResume() {
        super.onResume();
        onAppResume();
    }

    @Override
    public void onAppResume() {
        if (actionBar == null) {
            actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        }
        UIUtils.setActionBarTitle(actionBar, getActionBarTitleResId());
        if (setBackActionIcon()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(getContext(),
                    getBackActionIconResId()));
        } else {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(getMenuResId(), menu);
    }

    protected abstract int getMenuResId();

    protected abstract int[] getMenuItemsIds();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (setBackActionIcon()) {
            if (item.getItemId() == android.R.id.home) {
                getActivity().onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected abstract int getActionBarTitleResId();

    protected boolean setBackActionIcon() {
        return false;
    }

    protected int getBackActionIconResId() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void onBack() {
        UIUtils.removeFragment(getActivity().getSupportFragmentManager(), this);
    }

}
