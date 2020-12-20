package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import java.util.Collections;
import java.util.List;

public class PrivacyPolicyWebLinksBottomSheet extends WebLinksBottomSheet {

    @Override
    @androidx.annotation.NonNull
    List<WebLink> getLinks() {
        return Collections.singletonList(
                WebLink.PRIVACY_POLICY
        );
    }

}
