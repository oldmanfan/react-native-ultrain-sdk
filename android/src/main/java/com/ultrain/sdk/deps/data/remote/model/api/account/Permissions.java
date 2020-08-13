package com.ultrain.sdk.deps.data.remote.model.api.account;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/29
 */
public class Permissions {
    /**
     * perm_name : active
     * parent : owner
     * required_auth : {"threshold":1,"keys":[{"key":"UTR6WHbXoUckcHNmwiVH2PiNV4mL4hJLyxegXGShM7dFrJ9DVQFZ7","weight":1}],"accounts":[],"waits":[]}
     */

    @Expose
    private String perm_name;
    @Expose
    private String parent;
    @Expose
    private RequiredAuth required_auth;

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public RequiredAuth getRequired_auth() {
        return required_auth;
    }

    public void setRequired_auth(RequiredAuth required_auth) {
        this.required_auth = required_auth;
    }
}
