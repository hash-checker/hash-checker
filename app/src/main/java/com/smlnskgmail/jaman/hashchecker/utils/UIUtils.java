package com.smlnskgmail.jaman.hashchecker.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class UIUtils {

    private UIUtils() {

    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        fragmentManager.popBackStackImmediate();
    }


}
