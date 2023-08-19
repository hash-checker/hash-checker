package com.smlnskgmail.jaman.hashchecker.features.webview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;

import javax.inject.Inject;

public class WebFragment extends BaseFragment {

    @Inject
    public LanguageConfig languageConfig;

    private String url;

    public WebFragment(String url) {
        this.url = url;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView webView = view.findViewById(R.id.browser);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webView.loadUrl(url);

    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
    }


    @Override
    public int getActionBarTitleResId() {
        return R.string.settings_title_privacy_policy;
    }

    @Override
    public boolean setAllowBackAction() {
        return true;
    }

    @Override
    public int getBackActionIconResId() {
        return R.drawable.ic_arrow_back;
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_webview;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.web_view;
    }
}
