package com.smlnskgmail.jaman.hashchecker.ui;

import android.app.Activity;
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

import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppResumeTarget;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseFragment extends Fragment implements AppBackClickTarget, AppResumeTarget {

    public static final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT";

    private ActionBar actionBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        langHelper().applyLanguage(view.getContext());
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    protected abstract LanguageConfig langHelper();

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
        actionBar.setTitle(getActionBarTitleResId());
        if (setAllowBackAction()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(
                    ContextCompat.getDrawable(
                            getContext(),
                            getBackActionIconResId()
                    )
            );
        } else {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(getMenuResId(), menu);
    }

    protected abstract int getMenuResId();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (setAllowBackAction() && item.getItemId() == android.R.id.home) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected abstract int getActionBarTitleResId();

    protected boolean setAllowBackAction() {
        return false;
    }

    protected int getBackActionIconResId() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void appBackClick() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            UIUtils.removeFragment(activity.getSupportFragmentManager(), this);
        }
    }

}
