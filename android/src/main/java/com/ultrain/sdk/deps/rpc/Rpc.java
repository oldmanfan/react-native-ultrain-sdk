package com.ultrain.sdk.deps.rpc;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.ultrain.sdk.deps.crypto.ec.UltrainPrivateKey;
import com.ultrain.sdk.deps.crypto.ec.UltrainPublicKey;
import com.ultrain.sdk.deps.data.remote.model.api.GetRequiredKeys;
import com.ultrain.sdk.deps.data.remote.model.api.GetTableRequest;
import com.ultrain.sdk.deps.data.remote.model.chain.Action;
import com.ultrain.sdk.deps.data.remote.model.chain.PackedTransaction;
import com.ultrain.sdk.deps.data.remote.model.chain.SignedTransaction;
import com.ultrain.sdk.deps.data.remote.model.types.TypeChainId;
import com.ultrain.sdk.deps.rpc.service.Generator;
import com.ultrain.sdk.deps.rpc.service.RpcService;

import static com.ultrain.sdk.deps.util.Consts.TX_EXPIRATION_IN_MILSEC;

/**
 * @author redli
 * <p>
 * ULTRAIN RPC插件
 * <p>
 * JAVA直接调用即可；
 * <p>
 * ANDROID必须在线程中调用，推荐方式如下：
 * <p>
 * final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
 * <p>
 * threadPoolExecutor.execute(new Runnable() {
 * @Override public void run() {
 * PushTxnResponse txnResponse = rpc.pushTransaction("sakuyatest13", "test", "smallred1111", SAMPLE_PRIV_KEY_FOR_TEST, args2);
 * System.out.println();
 * System.out.println("PushTransaction: " + gson.toJson(txnResponse));
 * threadPoolExecutor.shutdown();
 * }
 * });
 * <p>
 * ANDROID 版本使用注意项（Android P 限制了明文流量的网络请求，非加密的流量请求都会被系统禁止掉）配置如下：
 * <p>
 * 在主项目中配置：
 * 1、AndroidManifest：
 * <p>
 * <application
 * android:networkSecurityConfig="@xml/network_security_config">
 * </application>
 * <p>
 * 2、在res目录下创建xml文件夹并在xml目录下创建network_security_config.xml，内容如下：
 * <p>
 * <network-security-config>
 * <base-config cleartextTrafficPermitted="true" />
 * </network-security-config>
 */

public class Rpc {

    private final RpcService rpcService;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public Rpc(String baseUrl) {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        rpcService = Generator.createService(RpcService.class, baseUrl);
    }


    private String[] getAccountPermission(String account, String pl) {
        return new String[]{account + "@" + pl};
    }

    private String[] getAccountPermission(String account) {
        return getAccountPermission(account, "active");
    }

    private String getTimeAfterHeadBlockTime(String headBlockTime, int diffInMilSec) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse(headBlockTime);

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add( Calendar.MILLISECOND, diffInMilSec);
            date = c.getTime();

            return sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return headBlockTime;
        }
    }

    private List<UltrainPublicKey> getKeys(List<String> required_keys) {
        if ( null == required_keys ){
            return new ArrayList<>();
        }

        ArrayList<UltrainPublicKey> retKeys = new ArrayList<>(required_keys.size());
        for ( String pubKey: required_keys ){
            retKeys.add( new UltrainPublicKey( pubKey));
        }

        return retKeys;
    }


    /**
     * 获得链信息
     *
     * @return
     */
    public String getChainInfo() {
        return Generator.executeSync(rpcService.getChainInfo());
    }

    /**
     * 获得区块信息
     *
     * @param blockNumberOrId 区块ID或者高度
     * @return
     */
    public String getBlock(String blockNumberOrId) {
        return Generator.executeSync(rpcService.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId)));
    }

    /**
     * 获得账户信息
     *
     * @param account 账户名称
     * @return
     */
    public String getAccount(String account) {
        return Generator.executeSync(rpcService.getAccount(Collections.singletonMap("account_name", account)));
    }

    /**
     * 获取表数据
     *
     * @param scope 作用域
     * @param code  合约名
     * @param table 表名
     * @return
     */
    public String getTableRows(String scope, String code, String table) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table)));
    }

    /**
     * 获取表数据
     *
     * @param scope 作用域
     * @param code  合约名
     * @param table 表名
     * @param limit 限制
     * @return
     */
    public String getTableRows(String scope, String code, String table, int limit) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table, limit)));
    }

    /**
     * 获取表数据
     *
     * @param scope      作用域
     * @param code       合约名
     * @param table      表名
     * @param lowerBound 第一个元素
     * @return
     */
    public String getTableRows(String scope, String code, String table, String lowerBound) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table, lowerBound)));
    }

    /**
     * 获取表数据
     *
     * @param scope      作用域
     * @param code       合约名
     * @param table      表名
     * @param lowerBound 第一个元素
     * @param upperBound 最后一个元素
     * @param limit      限制 默认10
     * @return
     */
    public String getTableRows(String scope, String code, String table, String lowerBound, String upperBound, int limit) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table, lowerBound, upperBound, limit)));
    }

    /**
     * 获取表数据
     *
     * @param scope   作用域
     * @param code    合约名
     * @param table   表名
     * @param limit   限制 默认10
     * @param reverse 反转
     * @return
     */
    public String getTableRows(String scope, String code, String table, int limit, boolean reverse) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table, limit, reverse)));
    }

    /**
     * 获取表数据
     * @param scope 作用域
     * @param code 合约名
     * @param table 表名
     * @param indexPos 索引的位置使用的，公认的参数primary，secondary，tertiary，fourth，fifth，sixth，seventh，eighth，ninth，tenth
     * @param keyType 为key_type index_position指定的键的类型（例如 - uint64_t或name）
     * @param encodeType ""
     * @param lowerBound 过滤结果以返回第一个元素，该元素不小于set中提供的值
     * @param upperBound 过滤结果以返回大于set中提供的值的第一个元素
     * @param limit 限制 默认10
     * @return
     */
    public String getTableRows(String scope, String code, String table,
                               int indexPos, String keyType, String encodeType, String lowerBound, String upperBound, int limit) {
        return Generator.executeSync(rpcService.getTableRows(new GetTableRequest(scope, code, table, indexPos, keyType, encodeType, lowerBound, upperBound, limit)));
    }

    /**
     * 获得交易信息
     *
     * @param account 账户名称
     * @param code    合约名
     * @param symbol  象征
     * @return
     */
    public String getCurrencyBalance(String account, String code, String symbol) {
        Map<String, Object> requestFields = new HashMap<>(3);
        requestFields.put("account", account);
        requestFields.put("code", code);
        requestFields.put("symbol", symbol);
        return Generator.executeSync(rpcService.getCurrencyBalance(requestFields));
    }


    /**
     * json序列化为二进制十六进制
     *
     * @param code   合约名
     * @param action 合约方法
     * @param args   合约方法参数集
     * @return
     */
    public String abiJsonToBin(String code, String action, Map<String, Object> args) {
        Map<String, Object> requestFields = new HashMap<>(3);
        requestFields.put("code", code);
        requestFields.put("action", action);
        requestFields.put("args", args);
        return Generator.executeSync(rpcService.abiJsonToBin(requestFields));
    }

    /**
     * 获取签署事务所需的密钥
     *
     * @param code       合约名
     * @param action     合约方法
     * @param account    用户名
     * @param privateKey 用户私钥
     * @param args       合约方法参数集
     * @return
     */
    public String getRequiredKeys(String code, String action, String account, String privateKey, String pl, Map<String, Object> args) {

        Gson gson = new Gson();

        JsonObject infoData = gson.fromJson(getChainInfo(), JsonObject.class);
        if (!infoData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(infoData);
        }
        if (null == infoData.get("data")) {
            return gson.toJson(infoData);
        }
        JsonObject info = gson.fromJson(infoData.get("data").getAsString(), JsonObject.class);
        if (null == info.get("head_block_id")) {
            return gson.toJson(infoData);
        }
        String headBlockId = info.get("head_block_id").getAsString();
        if (null == info.get("head_block_time")) {
            return gson.toJson(infoData);
        }
        String headBlockTime = info.get("head_block_time").getAsString();


        JsonObject abiJsonToBinData = gson.fromJson(abiJsonToBin(code, action, args), JsonObject.class);
        if (!abiJsonToBinData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(abiJsonToBinData);
        }
        if (null == abiJsonToBinData.get("data")) {
            return gson.toJson(abiJsonToBinData);
        }
        JsonObject abiJsonToBin = gson.fromJson(abiJsonToBinData.get("data").getAsString(), JsonObject.class);
        if (null == abiJsonToBin.get("binargs")) {
            return gson.toJson(abiJsonToBinData);
        }
        String binargs = abiJsonToBin.get("binargs").getAsString();

        Action action1 = new Action(code, action);
        action1.setAuthorization(getAccountPermission(account, pl));
        action1.setData(binargs);

        SignedTransaction signedTransaction = new SignedTransaction();
        signedTransaction.addAction(action1);
        signedTransaction.putSignatures(new ArrayList<String>());

        signedTransaction.setReferenceBlock(headBlockId);
        signedTransaction.setExpiration(getTimeAfterHeadBlockTime(headBlockTime, TX_EXPIRATION_IN_MILSEC));

        UltrainPrivateKey ultrainPrivateKey = new UltrainPrivateKey(privateKey);
        List<String> pubKeys = new ArrayList<>();
        pubKeys.add(ultrainPrivateKey.getPublicKey().toString());

        GetRequiredKeys requiredKeys = new GetRequiredKeys(signedTransaction, pubKeys);

        return Generator.executeSync(rpcService.getRequiredKeys(requiredKeys));
    }

    /**
     * 发起交易
     *
     * @param code       合约名
     * @param action     合约方法
     * @param account    用户名
     * @param privateKey 用户私钥
     * @param pl         账号权限, active或owner
     * @param args       合约方法参数集
     * @return
     */
    public String pushTransaction(String code, String action, String account, String privateKey, String pl,  Map<String, Object> args) {
        Gson gson = new Gson();

        JsonObject infoData = gson.fromJson(getChainInfo(), JsonObject.class);
        if (!infoData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(infoData);
        }
        if (null == infoData.get("data")) {
            return gson.toJson(infoData);
        }
        JsonObject info = gson.fromJson(infoData.get("data").getAsString(), JsonObject.class);
        if (null == info.get("head_block_id")) {
            return gson.toJson(infoData);
        }
        String headBlockId = info.get("head_block_id").getAsString();
        if (null == info.get("head_block_time")) {
            return gson.toJson(infoData);
        }
        String headBlockTime = info.get("head_block_time").getAsString();
        if (null == info.get("chain_id")) {
            return gson.toJson(infoData);
        }
        String chainId = info.get("chain_id").getAsString();


        JsonObject abiJsonToBinData = gson.fromJson(abiJsonToBin(code, action, args), JsonObject.class);
        if (!abiJsonToBinData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(abiJsonToBinData);
        }
        if (null == abiJsonToBinData.get("data")) {
            return gson.toJson(abiJsonToBinData);
        }
        JsonObject abiJsonToBin = gson.fromJson(abiJsonToBinData.get("data").getAsString(), JsonObject.class);
        if (null == abiJsonToBin.get("binargs")) {
            return gson.toJson(abiJsonToBinData);
        }
        String binargs = abiJsonToBin.get("binargs").getAsString();

        Action action1 = new Action(code, action);
        action1.setAuthorization(getAccountPermission(account, pl));
        action1.setData(binargs);

        SignedTransaction txnBeforeSign = new SignedTransaction();
        txnBeforeSign.addAction(action1);
        txnBeforeSign.putSignatures(new ArrayList<String>());

        txnBeforeSign.setReferenceBlock(headBlockId);
        txnBeforeSign.setExpiration(getTimeAfterHeadBlockTime(headBlockTime, TX_EXPIRATION_IN_MILSEC));

        UltrainPrivateKey ultrainPrivateKey = new UltrainPrivateKey(privateKey);
        TypeChainId typeChainId = new TypeChainId(chainId);
        final SignedTransaction signedTransaction = new SignedTransaction(txnBeforeSign);
        signedTransaction.sign(ultrainPrivateKey, typeChainId);

        PackedTransaction packedTransaction = new PackedTransaction(signedTransaction);

        return Generator.executeSync(rpcService.pushTransaction(packedTransaction));
    }

    public String getAccountExists(String accountName) {
        Map<String, String> requestFields = new HashMap<>();
        requestFields.put("account_name", accountName);
        return Generator.executeSync(rpcService.getAccountExist(requestFields));
    }

    public String getTransferFee(long block_height) {
        Map<String, Long> requestFields = new HashMap<>(1);
        requestFields.put("block_height", block_height);
        return Generator.executeSync(rpcService.getTransferFee(requestFields));
    }

    public String signTransaction(String code, String action, String account, String privateKey, String pl,  Map<String, Object> args) {
        Gson gson = new Gson();

        JsonObject infoData = gson.fromJson(getChainInfo(), JsonObject.class);
        if (!infoData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(infoData);
        }
        if (null == infoData.get("data")) {
            return gson.toJson(infoData);
        }
        JsonObject info = gson.fromJson(infoData.get("data").getAsString(), JsonObject.class);
        if (null == info.get("head_block_id")) {
            return gson.toJson(infoData);
        }
        String headBlockId = info.get("head_block_id").getAsString();
        if (null == info.get("head_block_time")) {
            return gson.toJson(infoData);
        }
        String headBlockTime = info.get("head_block_time").getAsString();
        if (null == info.get("chain_id")) {
            return gson.toJson(infoData);
        }
        String chainId = info.get("chain_id").getAsString();


        JsonObject abiJsonToBinData = gson.fromJson(abiJsonToBin(code, action, args), JsonObject.class);
        if (!abiJsonToBinData.get("isSuccess").getAsBoolean()) {
            return gson.toJson(abiJsonToBinData);
        }
        if (null == abiJsonToBinData.get("data")) {
            return gson.toJson(abiJsonToBinData);
        }
        JsonObject abiJsonToBin = gson.fromJson(abiJsonToBinData.get("data").getAsString(), JsonObject.class);
        if (null == abiJsonToBin.get("binargs")) {
            return gson.toJson(abiJsonToBinData);
        }
        String binargs = abiJsonToBin.get("binargs").getAsString();



        Action action1 = new Action(code, action);
        action1.setAuthorization(getAccountPermission(account, pl));
        action1.setData(binargs);

        SignedTransaction txnBeforeSign = new SignedTransaction();
        txnBeforeSign.addAction(action1);
        txnBeforeSign.putSignatures(new ArrayList<String>());
        txnBeforeSign.setReferenceBlock(headBlockId);
        txnBeforeSign.setExpiration(getTimeAfterHeadBlockTime(headBlockTime, TX_EXPIRATION_IN_MILSEC));

        TypeChainId typeChainId = new TypeChainId(chainId);
        UltrainPrivateKey ultrainPrivateKey = new UltrainPrivateKey(privateKey);
        final SignedTransaction signedTransaction = new SignedTransaction(txnBeforeSign);
        signedTransaction.sign(ultrainPrivateKey, typeChainId);

        return signedTransaction.getSignatures().get(0);
    }

    public String transfer(String from, String to, String quantity, String memo, String account, String privateKey) {
        Map<String, Object> params = new HashMap<>();
        params.put("from", from);
        params.put("to", to);
        params.put("quantity", quantity);
        params.put("memo", memo);

        return this.pushTransaction("utrio.token", "transfer", account, privateKey, "active", params);
    }
}
