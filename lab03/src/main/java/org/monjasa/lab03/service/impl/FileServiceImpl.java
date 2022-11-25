package org.monjasa.lab03.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab03.service.FileService;

import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
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
