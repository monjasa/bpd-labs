package org.monjasa.lab03.config.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommandLineOption {

    DECRYPT("decrypt"),
    FILE_PATH("file"),
    OUTPUT_FILE_PATH("output"),
    KEY("key");

    private final String name;

}
