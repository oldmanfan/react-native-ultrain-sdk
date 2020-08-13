package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class NetLimit {

    /**
     * used : 473
     * available : 174555583469
     * max : 174555583942
     */
    @Expose
    private int used;
    @Expose
    private String available;
    @Expose
    private String max;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
