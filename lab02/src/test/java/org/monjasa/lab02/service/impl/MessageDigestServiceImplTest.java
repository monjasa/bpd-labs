package org.monjasa.lab02.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.monjasa.lab02.mapper.StringByteArrayMapper;
import org.monjasa.lab02.service.MessageDigestService;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class MessageDigestServiceImplTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/md5.csv")
    void testDigest(String message, String expectedMessageDigest) {
        MessageDigestService messageDigestService = new MessageDigestServiceImpl();

        String messageDigest = StringByteArrayMapper.toHexString(messageDigestService.digest(message.getBytes()));

        log.info(String.format("H(%s) = %s", message, messageDigest));
        assertThat(messageDigest).isEqualTo(expectedMessageDigest);
    }
}
