package com.ultrain.sdk.deps.data.remote.model.api.tablerows;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class Rows {

    /**
     * account : wb
     * balance : 6.5000 SAK
     * used : 223
     */

    @Expose
    private String account;
    @Expose
    private String balance;
    @Expose
    private int used;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
