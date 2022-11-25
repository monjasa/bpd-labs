package org.monjasa.lab05.config.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommandLineOption {

    KEY_GENERATION("keygen"),
    SIGN("sign"),
    VERIFY("verify"),
    FILE_PATH("file"),
    OUTPUT_FILE_PATH("output"),
    KEY_PATH("key");

    private final String name;

}
