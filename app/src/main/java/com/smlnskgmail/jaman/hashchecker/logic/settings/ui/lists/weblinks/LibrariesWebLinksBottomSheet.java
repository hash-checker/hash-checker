package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import androidx.annotation.NonNull;

import java.util.List;

public class LibrariesWebLinksBottomSheet extends WebLinksBottomSheet {

    @NonNull
    @Override
    List<WebLink> getLinks() {
        return WebLink.getLibrariesLinks();
    }

}
