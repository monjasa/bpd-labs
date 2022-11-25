package org.monjasa.lab02.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab02.service.FileService;

import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Singleton
public class FileServiceImpl implements FileService {

    public static final String OUTPUT_FILE_PATH = "output.txt";

    @SneakyThrows
    @Override
    public byte[] readBytes(String filePath) {
        return Files.readAllBytes(Path.of(filePath));
    }

    @SneakyThrows
    @Override
    public String readString(String filePath) {
        return Files.lines(Path.of(filePath))
                .findFirst()
                .orElse("");
    }

    @SneakyThrows
    @Override
    public void writeResult(String result) {
        Files.writeString(Paths.get(OUTPUT_FILE_PATH), result);
    }
}
