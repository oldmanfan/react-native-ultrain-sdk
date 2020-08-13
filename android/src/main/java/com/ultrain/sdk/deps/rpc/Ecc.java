package com.ultrain.sdk.deps.rpc;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import com.ultrain.sdk.deps.crypto.digest.Sha256;
import com.ultrain.sdk.deps.crypto.ec.EcDsa;
import com.ultrain.sdk.deps.crypto.ec.EcSignature;
import com.ultrain.sdk.deps.crypto.ec.UltrainPrivateKey;
import com.ultrain.sdk.deps.data.remote.model.types.UltrainByteWriter;
import com.ultrain.sdk.deps.rpc.utils.EccTool;

/**
 * @author redli
 * @date 2019/3/28
 */
public class Ecc {

    /**
     * 生成私钥
     * @param seed
     * @return
     */
    public static String seedPrivate(String seed) {
        return EccTool.seedPrivate(seed);
    }


    /**
     * 通过私钥获取公钥
     * @param privateKey
     * @return
     */
    public static String getPublicKey(String privateKey) {
        return new UltrainPrivateKey(privateKey).getPublicKey().toString();
    }

    public static String sign(String message, String privateKey) {
        UltrainPrivateKey pk = new UltrainPrivateKey(privateKey);
//        UltrainByteWriter writer = new UltrainByteWriter(255);
//        writer.putString(message);
        Sha256 hash = Sha256.from(message.getBytes());
        Log.d("ECC-Sign", hash.toString());
        EcSignature signature = EcDsa.sign(hash, pk);
        return signature.toString();
    }
}
