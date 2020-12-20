package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import java.util.List;

public class LibrariesWebLinksBottomSheet extends WebLinksBottomSheet {

    @Override
    @androidx.annotation.NonNull
    List<WebLink> getLinks() {
        return WebLink.getLibrariesLinks();
    }

}
