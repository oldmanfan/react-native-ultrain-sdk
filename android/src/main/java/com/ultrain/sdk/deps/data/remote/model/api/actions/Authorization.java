package com.ultrain.sdk.deps.data.remote.model.api.actions;

import com.google.gson.annotations.Expose;

/**
 * @author redli
 * @date 2019/3/14
 */

public class Authorization {
    /**
     * actor : sakuya
     * permission : active
     */

    @Expose
    private String actor;

    @Expose
    private String permission;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
