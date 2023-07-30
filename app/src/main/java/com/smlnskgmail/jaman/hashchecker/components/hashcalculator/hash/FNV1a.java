package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash;

import java.math.BigInteger;

public class FNV1a {
    private static final BigInteger FNV_OFFSET_BASIS32 = new BigInteger("811c9dc5", 16);
    private static final BigInteger FNV_OFFSET_BASIS64 = new BigInteger("cbf29ce484222325", 16);
    private static final BigInteger FNV_OFFSET_BASIS128 = new BigInteger("6c62272e07bb014262b821756295c58d", 16);
    private static final BigInteger FNV_OFFSET_BASIS256 = new BigInteger("dd268dbcaac550362d98c384c4e576ccc8b1536847b6bbb31023b4c8caee0535", 16);
    private static final BigInteger FNV_OFFSET_BASIS512 = new BigInteger(
            "b86db0b1171f4416dca1e50f309990ac" +
                    "ac87d059c90000000000000000000d21" +
                    "e948f68a34c192f62ea79bc942dbe7ce" +
                    "182036415f56e34bac982aac4afe9fd9", 16
    );
    private static final BigInteger FNV_OFFSET_BASIS1024 = new BigInteger(
            "0000000000000000005f7a76758ecc4d" +
                    "32e56d5a591028b74b29fc4223fdada1" +
                    "6c3bf34eda3674da9a21d90000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "0000000000000000000000000004c6d7" +
                    "eb6e73802734510a555f256cc005ae55" +
                    "6bde8cc9c6a93b21aff4b16c71ee90b3", 16
    );
    private static final BigInteger FNV_PRIME32 = new BigInteger("01000193", 16);
    private static final BigInteger FNV_PRIME64 = new BigInteger("100000001b3", 16);
    private static final BigInteger FNV_PRIME128 = new BigInteger("0000000001000000000000000000013B", 16);
    private static final BigInteger FNV_PRIME256 = new BigInteger("0000000000000000000001000000000000000000000000000000000000000163", 16);
    private static final BigInteger FNV_PRIME512 = new BigInteger(
            "00000000000000000000000000000000" +
                    "00000000010000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000000000000157", 16
    );
    private static final BigInteger FNV_PRIME1024 = new BigInteger(
            "00000000000000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000010000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "00000000000000000000000000000000" +
                    "0000000000000000000000000000018D", 16
    );
    private static final BigInteger MOD32 = new BigInteger("2").pow(32);
    private static final BigInteger MOD64 = new BigInteger("2").pow(64);
    private static final BigInteger MOD128 = new BigInteger("2").pow(128);
    private static final BigInteger MOD256 = new BigInteger("2").pow(256);
    private static final BigInteger MOD512 = new BigInteger("2").pow(512);
    private static final BigInteger MOD1024 = new BigInteger("2").pow(1024);


    private BigInteger hash;

    private String type;

    public void setInstance(String type) {
        this.type = type;
    }

    public void update(byte[] b) {
        switch (this.type) {
            case HashConstants.FNV_1A_32:
                update32(b);
                break;
            case HashConstants.FNV_1A_64:
                update64(b);
                break;
            case HashConstants.FNV_1A_128:
                update128(b);
                break;
            case HashConstants.FNV_1A_256:
                update256(b);
                break;
            case HashConstants.FNV_1A_512:
                update512(b);
                break;
            case HashConstants.FNV_1A_1024:
                update1024(b);
                break;
            default:
                break;
        }
    }

    public void update(byte[] b, int offset, int len) {
        switch (this.type) {
            case HashConstants.FNV_1A_32:
                update32(b, offset, len);
                break;
            case HashConstants.FNV_1A_64:
                update64(b, offset, len);
                break;
            case HashConstants.FNV_1A_128:
                update128(b, offset, len);
                break;
            case HashConstants.FNV_1A_256:
                update256(b, offset, len);
                break;
            case HashConstants.FNV_1A_512:
                update512(b, offset, len);
                break;
            case HashConstants.FNV_1A_1024:
                update1024(b, offset, len);
                break;
            default:
                break;
        }
    }

    private void update32(byte[] b) {
        hash = FNV_OFFSET_BASIS32;

        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME32).mod(MOD32);
        }
    }

    private void update64(byte[] b) {
        hash = FNV_OFFSET_BASIS64;

        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME64).mod(MOD64);
        }
    }

    private void update128(byte[] b) {
        hash = FNV_OFFSET_BASIS128;

        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME128).mod(MOD128);
        }
    }

    private void update256(byte[] b) {
        hash = FNV_OFFSET_BASIS256;
        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME256).mod(MOD256);
        }
    }

    private void update512(byte[] b) {
        hash = FNV_OFFSET_BASIS512;

        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME512).mod(MOD512);
        }
    }

    private void update1024(byte[] b) {
        hash = FNV_OFFSET_BASIS1024;

        for (byte i : b) {
            hash = hash.xor(BigInteger.valueOf((int) i & 0xff));
            hash = hash.multiply(FNV_PRIME1024).mod(MOD1024);
        }
    }

    private void update32(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS32;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME32).mod(MOD32);
        }
    }

    private void update64(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS64;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME64).mod(MOD64);
        }
    }

    private void update128(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS128;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME128).mod(MOD128);
        }
    }

    private void update256(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS256;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME256).mod(MOD256);
        }
    }

    private void update512(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS512;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME512).mod(MOD512);
        }
    }

    private void update1024(byte[] b, int offset, int len) {
        hash = FNV_OFFSET_BASIS1024;

        for (int i = offset; i < offset + len; i++) {
            hash = hash.xor(BigInteger.valueOf((int) b[i] & 0xff));
            hash = hash.multiply(FNV_PRIME1024).mod(MOD1024);
        }
    }

    public void reset() {
        hash = null;
    }

    public String getValue() {
        return hash.toString(16);
    }
}
