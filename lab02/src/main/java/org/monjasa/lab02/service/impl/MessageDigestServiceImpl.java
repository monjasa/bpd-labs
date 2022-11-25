package org.monjasa.lab02.service.impl;

import org.monjasa.lab02.service.MessageDigestService;

import javax.inject.Singleton;
import java.util.stream.IntStream;

@Singleton
public class MessageDigestServiceImpl implements MessageDigestService {

    private static final int LENGTH_APPEND_SIZE_BYTES = 8;

    private static final int BLOCK_SIZE_BYTES = 64;

    private static final int INIT_A = 0x67452301;

    private static final int INIT_B = 0xEFCDAB89;

    private static final int INIT_C = 0x98BADCFE;

    private static final int INIT_D = 0x10325476;

    private static final int[] SHIFT_VALUES = {
            7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
            5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,
            4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,
            6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21
    };

    private static final int[] T_VALUES;

    static {
        double pow = Math.pow(2, 32);
        T_VALUES = IntStream.range(0, 64)
                .mapToLong(i -> (long) (pow * Math.abs(Math.sin(i + 1))))
                .mapToInt(value -> (int) value)
                .toArray();
    }

    @Override
    public byte[] digest(byte[] message) {
        int messageLengthBytes = message.length;
        int blockCount = (messageLengthBytes + LENGTH_APPEND_SIZE_BYTES) / BLOCK_SIZE_BYTES + 1;
        int totalLengthBytes = blockCount * BLOCK_SIZE_BYTES;
        byte[] paddingBytes = new byte[totalLengthBytes - messageLengthBytes];
        paddingBytes[0] = (byte) 0b10000000;

        long messageLengthBits = (long) messageLengthBytes * 8;
        for (int i = 0; i < LENGTH_APPEND_SIZE_BYTES; i++) {
            paddingBytes[paddingBytes.length - LENGTH_APPEND_SIZE_BYTES + i] = (byte) (messageLengthBits % 256);
            messageLengthBits = messageLengthBits / 256;
        }

        int a = INIT_A;
        int b = INIT_B;
        int c = INIT_C;
        int d = INIT_D;
        int[] buffer = new int[16];

        for (int i = 0; i < blockCount; i++) {
            int index = i * 64;
            for (int j = 0; j < 64; j++, index++) {
                buffer[j >>> 2] = ((int)((index < messageLengthBytes) ? message[index] : paddingBytes[index - messageLengthBytes]) << 24) | (buffer[j >>> 2] >>> 8);
            }

            int aa = a;
            int bb = b;
            int cc = c;
            int dd = d;

            for (int j = 0; j < 64; j++) {
                int f = switch (j / 16) {
                    case 0 -> f(b, c, d);
                    case 1 -> g(b, c, d);
                    case 2 -> h(b, c, d);
                    case 3 -> i(b, c, d);
                    default -> throw new IllegalStateException("Unexpected value: " + j / 16);
                };

                int bufferIndex = switch (j / 16) {
                    case 0 -> j;
                    case 1 -> (5 * j + 1) % 16;
                    case 2 -> (3 * j + 5) % 16;
                    case 3 -> (7 * j) % 16;
                    default -> throw new IllegalStateException("Unexpected value: " + j / 16);
                };

                int temp = b + Integer.rotateLeft(a + f + buffer[bufferIndex] + T_VALUES[j], SHIFT_VALUES[j]);

                a = d;
                d = c;
                c = b;
                b = temp;
            }

            a = a + aa;
            b = b + bb;
            c = c + cc;
            d = d + dd;
        }

        byte[] digest = new byte[16];
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int n = switch (i) {
                case 0 -> a;
                case 1 -> b;
                case 2 -> c;
                case 3 -> d;
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };

            for (int j = 0; j < 4; j++) {
                digest[count++] = (byte) n;
                n = n >>> 8;
            }
        }

        return digest;
    }

    @Override
    public int getDigestLength() {
        return 16;
    }

    private int f(int b, int c, int d) {
        return (b & c) | (~b & d);
    }

    private int g(int b, int c, int d) {
        return (b & d) | (c & ~d);
    }

    private int h(int b, int c, int d) {
        return b ^ c ^ d;
    }

    private int i(int b, int c, int d) {
        return c ^ (b | ~d);
    }
}
