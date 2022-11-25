package org.monjasa.lab05.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab05.service.FileService;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    @Override
    @SneakyThrows
    public byte[] readBytes(String pathString) {
        return Files.readAllBytes(Path.of(pathString));
    }

    @Override
    @SneakyThrows
    public Path writeBytes(String pathString, byte[] bytes) {
        return Files.write(Path.of(pathString), bytes);
    }
}
