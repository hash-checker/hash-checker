package com.smlnskgmail.jaman.hashchecker.generator.support;

import androidx.annotation.Nullable;

public interface HashGeneratorTarget {

    void onHashGenerationComplete(@Nullable String hashValue);

}
