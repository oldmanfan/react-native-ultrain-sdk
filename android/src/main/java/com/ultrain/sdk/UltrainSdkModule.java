package com.ultrain.sdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.common.io.BaseEncoding;
import org.json.JSONObject;

import io.github.novacrypto.bip39.JavaxPBKDF2WithHmacSHA512;
import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;

import com.google.common.hash.HashCode;
import com.ultrain.sdk.deps.rpc.Ecc;
import com.ultrain.sdk.deps.rpc.Rpc;

public class UltrainSdkModule extends ReactContextBaseJavaModule {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    private final ReactApplicationContext reactContext;

    public UltrainSdkModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "UltrainSdkModule";
    }

    // /**
    //  *  暴露给react常亮定义
    //  */
    // @Override
    // public Map<String, Object> getConstants() {
    //     final Map<String, Object> constants = new HashMap<>();
    //     constants.put("APPID", "23123");
    //     return constants;
    // }

/**
 * 和Ecc相关的功能函数
 */
    private void compositeRasKeys(String mnemonic, Promise promise) {
        try {
            String passphrase = "";
            byte[] seed = new SeedCalculator(JavaxPBKDF2WithHmacSHA512.INSTANCE).calculateSeed(mnemonic, passphrase);
            String privateKey = Ecc.seedPrivate(HashCode.fromBytes(seed).toString());
            String publicKey = Ecc.getPublicKey(privateKey);
            WritableMap result = Arguments.createMap();
            result.putString("mnemonics", mnemonic);
            result.putString("privateKey", privateKey);
            result.putString("publicKey", publicKey);
            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(e);
        }
    }
    @ReactMethod
    public void generatePrivate(Promise promise) {
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE)
                .createMnemonic(entropy, sb::append);
        compositeRasKeys(sb.toString(), promise);
    }

    @ReactMethod
    public void seedPrivate(String mnemonic, Promise promise) {
        compositeRasKeys(mnemonic, promise);
    }

    @ReactMethod
    public void getPublicKey(String privateKey, Promise promise) {
        try {
            String publicKey = Ecc.getPublicKey(privateKey);
            promise.resolve(publicKey);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /**
     * 对给定的交易用privateKey进行签名
     */
    @ReactMethod
    public void signTx(final String url, final ReadableMap txInfo, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String contract = txInfo.getString("contract");
                    String action = txInfo.getString("action");
                    String account = txInfo.getString("account");
                    String privateKey = txInfo.getString("privateKey");
                    String pl = txInfo.getString("permissionLevel");
                    ReadableMap params = txInfo.getMap("params");

                    String response = rpc.signTransaction(contract, action, account, privateKey, pl, params.toHashMap());
                    promise.resolve(response);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void sha256(String message, Promise promise) {
        try {
            MessageDigest md = MessageDigest.getInstance( "SHA-256" );
            md.update(message.getBytes());
            byte[] digest = md.digest();
            // Log.d(TAG, "digest of hello world : " + BaseEncoding.base16().lowerCase().encode(digest));
            promise.resolve(BaseEncoding.base16().lowerCase().encode(digest));
        } catch (Exception e) {
            promise.reject(e);
        }
    }
/**
 * 和链交易相关的函数
 */
    @ReactMethod
    public void getAccount(final String url, final String account, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String rsp = rpc.getAccount(account);
                    promise.resolve(rsp);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void getChainInfo(final String url, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String info = rpc.getChainInfo();
                    promise.resolve(info);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void getCurrencyBalance(final String url, final ReadableMap params, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String account = params.getString("account");
                    String contract = params.getString("contract");
                    String symbol = params.getString("symbol");
                    String rsp = rpc.getCurrencyBalance(account, contract, symbol);
                    promise.resolve(rsp);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void getTransferFee(final String url, final int blockHeight, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String rsp = rpc.getTransferFee(blockHeight);
                    promise.resolve(rsp);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void pushTx(final String url, final ReadableMap txInfo, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String contract = txInfo.getString("contract");
                    String action = txInfo.getString("action");
                    String account = txInfo.getString("account");
                    String privateKey = txInfo.getString("privateKey");
                    String pl = txInfo.getString("permissionLevel");
                    ReadableMap params = txInfo.getMap("params");

                    String response = rpc.pushTransaction(contract, action, account, privateKey, pl, params.toHashMap());
                    promise.resolve(response);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    @ReactMethod
    public void transfer(final String url, final ReadableMap txInfo, final Promise promise) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rpc rpc = new Rpc(url);
                    String from = txInfo.getString("from");
                    String to = txInfo.getString("to");
                    String quantity = txInfo.getString("quantity");
                    String memo = txInfo.getString("memo");
                    String actor = txInfo.getString("actor");
                    String privateKey = txInfo.getString("privateKey");

                    String response = rpc.transfer(from, to, quantity, memo, actor, privateKey);
                    promise.resolve(response);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }
}
