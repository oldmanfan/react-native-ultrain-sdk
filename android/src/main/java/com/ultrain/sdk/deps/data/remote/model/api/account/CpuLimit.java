package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class CpuLimit {
    /**
     * used : 2278
     * available : 33293833160
     * max : 33293835438
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
