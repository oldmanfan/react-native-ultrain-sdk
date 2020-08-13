package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class TotalResources {
    /**
     * owner : smallred1111
     * net_weight : 0.1000 TOK
     * cpu_weight : 0.1000 TOK
     * ram_bytes : 15839
     */

    @Expose
    private String owner;
    @Expose
    private String net_weight;
    @Expose
    private String cpu_weight;
    @Expose
    private int ram_bytes;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getCpu_weight() {
        return cpu_weight;
    }

    public void setCpu_weight(String cpu_weight) {
        this.cpu_weight = cpu_weight;
    }

    public int getRam_bytes() {
        return ram_bytes;
    }

    public void setRam_bytes(int ram_bytes) {
        this.ram_bytes = ram_bytes;
    }
}
