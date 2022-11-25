package org.monjasa.lab03.service;

import java.nio.file.Path;

public interface FileService {

    byte[] readBytes(String pathString);

    Path writeBytes(String pathString, byte[] bytes);

}
