package com.smlnskgmail.jaman.hashchecker.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.components.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.components.states.AppResumeTarget;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

public abstract class BaseFragment extends Fragment implements AppBackClickTarget, AppResumeTarget {

    public static final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT";

    private ActionBar actionBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        onPostInitialize();
    }

    protected abstract void initializeUI(@NonNull View contentView);

    public void onPostInitialize() {}

    @Override
    public void onResume() {
        super.onResume();
        appResume();
    }

    @Override
    public void appResume() {
        if (actionBar == null) {
            actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        }
        UITools.setActionBarTitle(actionBar, getActionBarTitleResId());
        if (setBackActionIcon()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(getContext(), getBackActionIconResId()));
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void appBackClick() {
        UITools.removeFragment(getActivity().getSupportFragmentManager(), this);
    }

}
