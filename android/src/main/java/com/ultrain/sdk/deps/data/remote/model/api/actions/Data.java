package com.ultrain.sdk.deps.data.remote.model.api.actions;


import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/14
 */
public class Data {
    /**
     * from : sakuya
     * to : smallred1111
     * quantity : 100.0000 TOK
     * memo :
     */

    @Expose
    private String from;

    @Expose
    private String to;

    @Expose
    private String quantity;

    @Expose
    private String memo;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
