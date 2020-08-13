package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class Keys {
    /**
     * key : ULTRAIN6WHbXoUckcHNmwiVH2PiNV4mL4hJLyxegXGShM7dFrJ9DVQFZ7
     * weight : 1
     */

    @Expose
    private String key;
    @Expose
    private int weight;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
