package com.ultrain.sdk.deps.rpc.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

import com.ultrain.sdk.deps.data.remote.model.api.GetRequiredKeys;
import com.ultrain.sdk.deps.data.remote.model.api.GetTableRequest;
import com.ultrain.sdk.deps.data.remote.model.chain.PackedTransaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 * @author espritblock http://eblock.io
 *
 */
public interface RpcService {

	@GET("/v1/chain/get_chain_info")
	Call<JsonObject> getChainInfo();

	@POST("/v1/chain/get_block_info")
	Call<JsonObject> getBlock(@Body Map<String, String> requestFields);

	@POST("/v1/chain/abi_json2bin")
	Call<JsonObject> abiJsonToBin(@Body Map<String, Object> requestFields);

	@POST("/v1/chain/push_tx")
	Call<JsonObject> pushTransaction(@Body PackedTransaction body);

	@POST("/v1/chain/get_required_keys")
	Call<JsonObject> getRequiredKeys(@Body GetRequiredKeys requiredKeys);

	@POST("/v1/chain/get_account_info")
	Call<JsonObject> getAccount(@Body Map<String, String> requestFields);

	@POST("/v1/chain/get_table_records")
	Call<JsonObject> getTableRows(@Body GetTableRequest body);

	@POST("/v1/chain/get_currency_balance")
	Call<JsonArray> getCurrencyBalance(@Body Map<String, Object> requestFields);

	@POST("/v1/chain/get_trans_fee")
	Call<JsonObject> getTransferFee(@Body Map<String, Long> block_height);

	@POST("/v1/chain/get_account_exist")
	Call<JsonObject> getAccountExist(@Body Map<String, String> requestFields);

}
