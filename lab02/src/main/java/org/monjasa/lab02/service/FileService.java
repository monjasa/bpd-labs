package org.monjasa.lab02.service;

public interface FileService {

    byte[] readBytes(String filePath);

    String readString(String filePath);

    void writeResult(String result);

}
