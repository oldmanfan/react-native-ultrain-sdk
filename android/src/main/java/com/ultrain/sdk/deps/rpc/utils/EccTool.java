package com.ultrain.sdk.deps.rpc.utils;

import java.math.BigInteger;

import com.ultrain.sdk.deps.rpc.exception.EException;

/**
 * @author redli
 * @date 2019-06-04
 */
public class EccTool {

    /**
     * seedPrivate
     *
     * @param seed
     * @return
     */
    public static String seedPrivate(String seed) {
        if (seed == null || seed.length() == 0) {
            throw new EException("args_empty", "args is empty");
        }
        byte[] a = { (byte) 0x80 };
        byte[] b = new BigInteger(Sha.SHA256(seed)).toByteArray();
        byte[] private_key = ByteUtils.concat(a, b);
        byte[] checksum = Sha.SHA256(private_key);
        checksum = Sha.SHA256(checksum);
        byte[] check = ByteUtils.copy(checksum, 0, 4);
        byte[] pk = ByteUtils.concat(private_key, check);
        return Base58.encode(pk);
    }

}
