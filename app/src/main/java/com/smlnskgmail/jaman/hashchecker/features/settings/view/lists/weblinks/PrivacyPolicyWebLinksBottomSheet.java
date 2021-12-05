package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PrivacyPolicyWebLinksBottomSheet extends WebLinksBottomSheet {

    @Inject
    public ThemeConfig themeConfig;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    protected ThemeConfig themeHelper() {
        return themeConfig;
    }

    @NonNull
    @Override
    protected List<WebLink> getLinks() {
        return Collections.singletonList(WebLink.PRIVACY_POLICY);
    }

}
