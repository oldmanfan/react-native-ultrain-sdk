package com.ultrain.sdk.deps.rpc.exception;

import android.net.ParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import retrofit2.HttpException;

/**
 *
 * @author espritblock http://eblock.io
 *
 */
public class ApiException extends RuntimeException {

	private String TAG = ApiException.class.getSimpleName();

	private static final long serialVersionUID = 1L;

	private ApiError error;

	public ApiException(ApiError apiError) {
		this.error = apiError;
	}

	public ApiException(Throwable e) {
		super(e);
		if (e instanceof SocketException
		|| e instanceof UnknownServiceException) {
			System.out.println("ApiException: " + "网络连接失败,请检查网络");
		} else if (e instanceof HttpException) {
			//   HTTP错误
			System.out.println("ApiException: " + "服务器异常");
		} else if (e instanceof ConnectException
				|| e instanceof UnknownHostException) {
			//   连接错误
			System.out.println("ApiException: " + "网络连接失败,请检查网络");
		} else if (e instanceof InterruptedIOException) {
			//  连接超时
			System.out.println("ApiException: " + "连接超时,请稍后再试");
		} else if (e instanceof JSONException
				|| e instanceof ParseException) {
			//  解析错误
			System.out.println("ApiException: " + "解析服务器响应数据失败");
		} else {
			System.out.println("ApiException: " + "未知错误");
		}
		e.printStackTrace();
	}

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		if (error != null) {
			return error.getMessage();
		}
		return super.getMessage();
	}
}
