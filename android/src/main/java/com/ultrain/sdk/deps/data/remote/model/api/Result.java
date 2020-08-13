package com.ultrain.sdk.deps.data.remote.model.api;

/**
 * @author redli
 * @date 2019/4/11
 */
public class Result {
    private boolean isSuccess;
    private String message;


    /**
     * 链返回的json字符串
     */
    private String data;

    public Result(boolean isSuccess, String message, String data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
