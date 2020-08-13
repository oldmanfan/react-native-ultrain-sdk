package com.ultrain.sdk.deps.data.remote.model.api.actions;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/14
 */
public class ActionTrace {
    /**
     * receipt : {"receiver":"smallred1111","act_digest":"5a2f7d6000cc7fc4ad0bf75e28014fc927a5b0502cefa13b00f58953cf98f4a7","global_sequence":14291919,"recv_sequence":1,"auth_sequence":[["sakuya",685]],"code_sequence":1,"abi_sequence":1}
     * act : {"account":"ultrainio.token","name":"transfer","authorization":[{"actor":"sakuya","permission":"active"}],"data":{"from":"sakuya","to":"smallred1111","quantity":"100.0000 TOK","memo":""},"hex_data":"0000000018afa1c110420849dd188dc440420f000000000004544f4b0000000000"}
     * context_free : false
     * elapsed : 12
     * console :
     * trx_id : 9059e1e5d5b4b1b7c8d633ed57373fe56d25989d6d9e6fc2ccb02af2eae81687
     * block_num : 14286896
     * block_time : 2019-03-13T03:02:39.500
     * producer_block_id : 00da00309a6c552c67c785c612a8ad79fe41a1e3d9bfc9fff08dddf7c8812556
     * account_ram_deltas : []
     * except : null
     * inline_traces : []
     */

    @Expose
    private Receipt receipt;

    @Expose
    private Act act;

    @Expose
    private boolean context_free;

    @Expose
    private int elapsed;

    @Expose
    private String console;

    @Expose
    private String trx_id;

    @Expose
    private int block_num;

    @Expose
    private String block_time;

    @Expose
    private String producer_block_id;

    @Expose
    private Object except;

    @Expose
    private List<Object> account_ram_deltas;

    @Expose
    private List<Object> inline_traces;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public boolean isContext_free() {
        return context_free;
    }

    public void setContext_free(boolean context_free) {
        this.context_free = context_free;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(String trx_id) {
        this.trx_id = trx_id;
    }

    public int getBlock_num() {
        return block_num;
    }

    public void setBlock_num(int block_num) {
        this.block_num = block_num;
    }

    public String getBlock_time() {
        return block_time;
    }

    public void setBlock_time(String block_time) {
        this.block_time = block_time;
    }

    public String getProducer_block_id() {
        return producer_block_id;
    }

    public void setProducer_block_id(String producer_block_id) {
        this.producer_block_id = producer_block_id;
    }

    public Object getExcept() {
        return except;
    }

    public void setExcept(Object except) {
        this.except = except;
    }

    public List<Object> getAccount_ram_deltas() {
        return account_ram_deltas;
    }

    public void setAccount_ram_deltas(List<Object> account_ram_deltas) {
        this.account_ram_deltas = account_ram_deltas;
    }

    public List<Object> getInline_traces() {
        return inline_traces;
    }

    public void setInline_traces(List<Object> inline_traces) {
        this.inline_traces = inline_traces;
    }
}
