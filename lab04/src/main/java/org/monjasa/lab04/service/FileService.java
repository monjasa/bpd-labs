package org.monjasa.lab04.service;

import java.nio.file.Path;

public interface FileService {

    byte[] readBytes(String pathString);

    Path writeBytes(String pathString, byte[] bytes);

}
