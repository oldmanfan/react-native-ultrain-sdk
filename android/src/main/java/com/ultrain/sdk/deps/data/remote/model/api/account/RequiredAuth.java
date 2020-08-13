package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/29
 */
public class RequiredAuth {
    /**
     * threshold : 1
     * keys : [{"key":"UTR6WHbXoUckcHNmwiVH2PiNV4mL4hJLyxegXGShM7dFrJ9DVQFZ7","weight":1}]
     * accounts : []
     * waits : []
     */

    @Expose
    private int threshold;
    @Expose
    private List<Keys> keys;
    @Expose
    private List<Object> accounts;
    @Expose
    private List<Object> waits;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public List<Keys> getKeys() {
        return keys;
    }

    public void setKeys(List<Keys> keys) {
        this.keys = keys;
    }

    public List<Object> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Object> accounts) {
        this.accounts = accounts;
    }

    public List<Object> getWaits() {
        return waits;
    }

    public void setWaits(List<Object> waits) {
        this.waits = waits;
    }
}
