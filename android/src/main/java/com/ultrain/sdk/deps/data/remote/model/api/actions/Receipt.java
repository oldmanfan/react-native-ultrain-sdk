package com.ultrain.sdk.deps.data.remote.model.api.actions;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/14
 */
public class Receipt {

    /**
     * receiver : smallred1111
     * act_digest : 5a2f7d6000cc7fc4ad0bf75e28014fc927a5b0502cefa13b00f58953cf98f4a7
     * global_sequence : 14291919
     * recv_sequence : 1
     * auth_sequence : [["sakuya",685]]
     * code_sequence : 1
     * abi_sequence : 1
     */

    @Expose
    private String receiver;

    @Expose
    private String act_digest;

    @Expose
    private int global_sequence;

    @Expose
    private int recv_sequence;

    @Expose
    private int code_sequence;

    @Expose
    private int abi_sequence;

    @Expose
    private List<List<Object>> auth_sequence;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAct_digest() {
        return act_digest;
    }

    public void setAct_digest(String act_digest) {
        this.act_digest = act_digest;
    }

    public int getGlobal_sequence() {
        return global_sequence;
    }

    public void setGlobal_sequence(int global_sequence) {
        this.global_sequence = global_sequence;
    }

    public int getRecv_sequence() {
        return recv_sequence;
    }

    public void setRecv_sequence(int recv_sequence) {
        this.recv_sequence = recv_sequence;
    }

    public int getCode_sequence() {
        return code_sequence;
    }

    public void setCode_sequence(int code_sequence) {
        this.code_sequence = code_sequence;
    }

    public int getAbi_sequence() {
        return abi_sequence;
    }

    public void setAbi_sequence(int abi_sequence) {
        this.abi_sequence = abi_sequence;
    }

    public List<List<Object>> getAuth_sequence() {
        return auth_sequence;
    }

    public void setAuth_sequence(List<List<Object>> auth_sequence) {
        this.auth_sequence = auth_sequence;
    }
}
