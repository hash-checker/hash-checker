package com.smlnskgmail.jaman.hashchecker.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.components.CustomTypefaceSpan;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnAppResume;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnNavigationListener;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements OnNavigationListener, OnAppResume {

    private ActionBar actionBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initializeUI(view);
        onPostInitialize();
    }

    public abstract void initializeUI(@NonNull View contentView);

    public void onPostInitialize() {}

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
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(getMenuResId(), menu);
        for (int itemId: getMenuItemsIds()) {
            MenuItem item = menu.findItem(itemId);
            applyFontToMenuItem(item);
        }
    }

    public abstract int getMenuResId();
    public abstract int[] getMenuItemsIds();

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

    private void applyFontToMenuItem(@NonNull MenuItem menuItem) {
        Typeface font = UIUtils.getAppFont(getContext());
        SpannableString title = new SpannableString(menuItem.getTitle());
        title.setSpan(new CustomTypefaceSpan("", font), 0, title.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(title);
    }

    public abstract int getActionBarTitleResId();

    public boolean setBackActionIcon() {
        return false;
    }

    public int getBackActionIconResId() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    public abstract int getLayoutResId();

    @Override
    public void onBack() {
        UIUtils.removeFragment(getActivity().getSupportFragmentManager(), this);
    }

}
