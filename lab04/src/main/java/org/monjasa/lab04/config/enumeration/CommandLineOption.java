package org.monjasa.lab04.config.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommandLineOption {

    KEY_GENERATION("keygen"),
    DECRYPT("decrypt"),
    FILE_PATH("file"),
    OUTPUT_FILE_PATH("output"),
    KEY_PATH("key");

    private final String name;

}
