package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash;

import java.util.Arrays;

public class Blake2B {
    private final static long[] IV = {
            0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL,
            0xa54ff53a5f1d36f1L, 0x510e527fade682d1L, 0x9b05688c2b3e6c1fL,
            0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
    };

    // CPD-OFF
    private final static byte[][] SIGMA = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3},
            {11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4},
            {7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8},
            {9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13},
            {2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9},
            {12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11},
            {13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10},
            {6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5},
            {10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}
    };
    // CPD-ON

    private final static int BLOCK_LENGTH = 128;
    private static final int ROUNDS = 12;
    private int digestLength = 64;
    private int keyLength = 0;
    private byte[] key = null;
    private byte[] buffer = null;
    private int bufferPos = 0;
    private long[] vector = new long[16];
    private long[] h = null;
    private long t0 = 0L;
    private long t1 = 0L;
    private long f0 = 0L;

    public Blake2B() {
        this(512);
    }

    public Blake2B(int digestSize) {
        if (digestSize < 8 || digestSize > 512 || digestSize % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        buffer = new byte[BLOCK_LENGTH];
        keyLength = 0;
        this.digestLength = digestSize / 8;
        init();
    }

    private void init() {
        if (h == null) {
            h = new long[8];

            h[0] = IV[0] ^ (digestLength | (keyLength << 8) | 0x1010000);
            h[1] = IV[1];
            h[2] = IV[2];
            h[3] = IV[3];
            h[4] = IV[4];
            h[5] = IV[5];
            h[6] = IV[6];
            h[7] = IV[7];
        }
    }

    private void initializeVector() {
        System.arraycopy(h, 0, vector, 0, h.length);
        System.arraycopy(IV, 0, vector, h.length, 4);
        vector[12] = t0 ^ IV[4];
        vector[13] = t1 ^ IV[5];
        vector[14] = f0 ^ IV[6];
        vector[15] = IV[7];
    }

    public void update(byte[] b) {
        for (byte i : b) {
            update(i);
        }
    }

    private void update(byte b) {
        int remainingLength = 0;
        remainingLength = BLOCK_LENGTH - bufferPos;
        if (remainingLength == 0) {
            t0 += BLOCK_LENGTH;
            if (t0 == 0) {
                t1++;
            }
            compress(buffer, 0);
            Arrays.fill(buffer, (byte) 0);
            buffer[0] = b;
            bufferPos = 1;
        } else {
            buffer[bufferPos] = b;
            bufferPos++;
            return;
        }
    }

    public void update(byte[] message, int offset, int len) {
        if (message == null || len == 0) {
            return;
        }
        int remainingLength = 0;

        if (bufferPos != 0) {
            remainingLength = BLOCK_LENGTH - bufferPos;
            if (remainingLength < len) {
                System.arraycopy(message, offset, buffer, bufferPos, remainingLength);
                t0 += BLOCK_LENGTH;
                if (t0 == 0) {
                    t1++;
                }
                compress(buffer, 0);
                bufferPos = 0;
                Arrays.fill(buffer, (byte) 0);
            } else {
                System.arraycopy(message, offset, buffer, bufferPos, len);
                bufferPos += len;
                return;
            }
        }

        int messagePos;
        int blockWiseLastPos = offset + len - BLOCK_LENGTH;
        for (messagePos = offset + remainingLength; messagePos < blockWiseLastPos; messagePos += BLOCK_LENGTH) {
            t0 += BLOCK_LENGTH;
            if (t0 == 0) {
                t1++;
            }
            compress(message, messagePos);
        }
        System.arraycopy(message, messagePos, buffer, 0, offset + len - messagePos);
        bufferPos += offset + len - messagePos;
    }

    private int doFinal(byte[] out, int outOffset) {
        f0 = 0xFFFFFFFFFFFFFFFFL;
        t0 += bufferPos;
        if (bufferPos > 0 && t0 == 0) {
            t1++;
        }
        compress(buffer, 0);
        Arrays.fill(buffer, (byte) 0);
        Arrays.fill(vector, 0L);

        for (int i = 0; i < h.length && (i * 8 < digestLength); i++) {
            byte[] bytes = longToLittleEndian(h[i]);
            if (i * 8 < digestLength - 8) {
                System.arraycopy(bytes, 0, out, outOffset + i * 8, 8);
            } else {
                System.arraycopy(bytes, 0, out, outOffset + i * 8, digestLength - (i * 8));
            }
        }
        Arrays.fill(h, 0L);
        reset();
        return digestLength;
    }

    public String getValue() {
        byte[] out = new byte[64];
        doFinal(out, 0);
        return bytesToHex(out);
    }

    public void reset() {
        bufferPos = 0;
        f0 = 0L;
        t0 = 0L;
        t1 = 0L;
        h = null;
        Arrays.fill(buffer, (byte) 0);
        if (key != null) {
            System.arraycopy(key, 0, buffer, 0, key.length);
            bufferPos = BLOCK_LENGTH;
        }
        init();
    }

    private void compress(byte[] message, int messagePos) {

        initializeVector();

        long[] m = new long[16];
        for (int j = 0; j < 16; j++) {
            m[j] = littleEndianToLong(message, messagePos + j * 8);
        }

        for (int round = 0; round < ROUNDS; round++) {
            mix(m[SIGMA[round][0]], m[SIGMA[round][1]], 0, 4, 8, 12);
            mix(m[SIGMA[round][2]], m[SIGMA[round][3]], 1, 5, 9, 13);
            mix(m[SIGMA[round][4]], m[SIGMA[round][5]], 2, 6, 10, 14);
            mix(m[SIGMA[round][6]], m[SIGMA[round][7]], 3, 7, 11, 15);
            mix(m[SIGMA[round][8]], m[SIGMA[round][9]], 0, 5, 10, 15);
            mix(m[SIGMA[round][10]], m[SIGMA[round][11]], 1, 6, 11, 12);
            mix(m[SIGMA[round][12]], m[SIGMA[round][13]], 2, 7, 8, 13);
            mix(m[SIGMA[round][14]], m[SIGMA[round][15]], 3, 4, 9, 14);
        }

        for (int offset = 0; offset < h.length; offset++) {
            h[offset] = h[offset] ^ vector[offset] ^ vector[offset + 8];
        }
    }

    private void mix(long m1, long m2, int posA, int posB, int posC, int posD) {
        vector[posA] = vector[posA] + vector[posB] + m1;
        vector[posD] = rotateRight(vector[posD] ^ vector[posA], 32);
        vector[posC] = vector[posC] + vector[posD];
        vector[posB] = rotateRight(vector[posB] ^ vector[posC], 24);
        vector[posA] = vector[posA] + vector[posB] + m2;
        vector[posD] = rotateRight(vector[posD] ^ vector[posA], 16);
        vector[posC] = vector[posC] + vector[posD];
        vector[posB] = rotateRight(vector[posB] ^ vector[posC], 63);
    }

    private static int littleEndianToInt(byte[] bs, int off) {
        int n = bs[off] & 0xff;
        int byte2 = bs[off + 1] & 0xff;
        int byte3 = bs[off + 2] & 0xff;
        int byte4 = bs[off + 3] & 0xff;

        n |= byte2 << 8;
        n |= byte3 << 16;
        n |= byte4 << 24;
        return n;
    }

    private static void intToLittleEndian(int n, byte[] bs, int off) {
        bs[off] = (byte) (n);
        int nextOff = off + 1;
        bs[nextOff] = (byte) (n >>> 8);
        nextOff++;
        bs[nextOff] = (byte) (n >>> 16);
        nextOff++;
        bs[nextOff] = (byte) (n >>> 24);
    }

    private static long littleEndianToLong(byte[] bs, int off) {
        int lo = littleEndianToInt(bs, off);
        int hi = littleEndianToInt(bs, off + 4);
        return ((long) (hi & 0xffffffffL) << 32) | (long) (lo & 0xffffffffL);
    }

    private static byte[] longToLittleEndian(long n) {
        byte[] bs = new byte[8];
        longToLittleEndian(n, bs, 0);
        return bs;
    }

    private static void longToLittleEndian(long n, byte[] bs, int off) {
        intToLittleEndian((int) (n & 0xffffffffL), bs, off);
        intToLittleEndian((int) (n >>> 32), bs, off + 4);
    }

    private static long rotateRight(long i, int distance) {
        return Long.rotateRight(i, distance);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
