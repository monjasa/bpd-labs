package org.monjasa.lab02.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringByteArrayMapper {

    public static String toHexString(byte[] byteArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte value : byteArray) {
            stringBuilder.append(String.format("%02x", value));
        }
        return stringBuilder.toString();
    }

    public static byte[] toByteArray(String message) {
        int[] intArray = IntStream.range(0, message.length() / 2)
                .map(i -> Integer.valueOf(message.substring(2 * i, 2 * (i + 1)), 16))
                .toArray();
        
        byte[] byteArray = new byte[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            byteArray[i] = (byte) intArray[i];
        }

        return byteArray;
    }
}
