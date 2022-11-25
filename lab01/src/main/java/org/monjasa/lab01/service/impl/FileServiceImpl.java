package org.monjasa.lab01.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab01.service.FileService;

import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Paths;

@Singleton
public class FileServiceImpl implements FileService {

    public static final String OUTPUT_FILE_PATH = "result.csv";

    @SneakyThrows
    @Override
    public void writeResultToOutputFile(String result) {
        Files.writeString(Paths.get(OUTPUT_FILE_PATH), result);
    }
}
