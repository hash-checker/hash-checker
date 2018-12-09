package com.smlnskgmail.jaman.hashchecker.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
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

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.adaptive.AdaptiveTypefaceSpan;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.IBack;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.IResume;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBack, IResume {

    private ActionBar actionBar;

    abstract int getLayoutResId();
    abstract int getTitleResId();
    abstract int getMenuResId();
    abstract int[] getMenuItemIds();
    abstract boolean setBackArrow();
    abstract void initUI(@NonNull View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initUI(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    @Override
    public void resume() {
        if (actionBar == null) {
            actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        }
        actionBar.setTitle(getTitleResId());
        actionBar.setDisplayHomeAsUpEnabled(setBackArrow());
    }

    private void applyFontToMenuItem(@NonNull MenuItem menuItem) {
        Typeface font = ResourcesCompat.getFont(getContext(), R.font.google_sans_regular);
        SpannableString title = new SpannableString(menuItem.getTitle());
        title.setSpan(new AdaptiveTypefaceSpan("", font), 0, title.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(getMenuResId(), menu);
        for (int itemId: getMenuItemIds()) {
            MenuItem item = menu.findItem(itemId);
            applyFontToMenuItem(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (setBackArrow()) {
            if (item.getItemId() == android.R.id.home) {
                getActivity().onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
