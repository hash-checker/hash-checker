package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.List;

import javax.inject.Inject;

public class LibrariesWebLinksBottomSheet extends WebLinksBottomSheet {

    @Inject
    ThemeHelper themeHelper;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    protected ThemeHelper themeHelper() {
        return themeHelper;
    }

    @NonNull
    @Override
    List<WebLink> getLinks() {
        return WebLink.getLibrariesLinks();
    }

}
