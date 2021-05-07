package com.smlnskgmail.jaman.hashchecker.di.components;

import com.smlnskgmail.jaman.hashchecker.di.modules.DatabaseHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.LangHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.SettingsHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        DatabaseHelperModule.class,
        SettingsHelperModule.class,
        LangHelperModule.class,
})
public interface AppComponent {


}
