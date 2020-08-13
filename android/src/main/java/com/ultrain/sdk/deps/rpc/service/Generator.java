package com.ultrain.sdk.deps.rpc.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import com.ultrain.sdk.deps.data.remote.HostInterceptor;
import com.ultrain.sdk.deps.data.remote.model.api.Result;
import com.ultrain.sdk.deps.data.util.GsonUltrainTypeAdapterFactory;
import com.ultrain.sdk.deps.rpc.exception.ApiException;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author redli
 * @data
 */
public class Generator {

	private static OkHttpClient httpClient = new OkHttpClient.Builder()
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS)
			.addInterceptor(new HostInterceptor())
			.build();

	private static Gson gson = new GsonBuilder()
			.registerTypeAdapterFactory(new GsonUltrainTypeAdapterFactory())
			.serializeNulls()
			.excludeFieldsWithoutExposeAnnotation().create();

	private static Retrofit.Builder builder = new Retrofit.Builder();

	private static Retrofit retrofit;

	public static <S> S createService(Class<S> serviceClass, String baseUrl) {
		builder.baseUrl(baseUrl);
		builder.addConverterFactory(GsonConverterFactory.create(gson));
		builder.client(httpClient);
		retrofit = builder.build();
		return retrofit.create(serviceClass);
	}

	public static <T> String executeSync(Call<T> call) {
		try {
			Response<T> response = call.execute();
			Gson gson = new Gson();
			if (response.isSuccessful()) {
				String data = gson.toJson(response.body());
				return new Gson().toJson(new Result(true, "操作成功", data));
			} else {
				return new Gson().toJson(new Result(false, "操作失败", response.errorBody().string()));
			}
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}
}
