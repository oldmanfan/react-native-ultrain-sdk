package com.ultrain.sdk.deps.data.remote.model.api.actions;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/29
 */
public class Actions {

    /**
     * actions : [{"global_action_seq":16911538,"account_action_seq":121,"block_num":16905805,"block_time":"2019-03-28T09:47:56.000","action_trace":{"receipt":{"receiver":"sakuyatest13","act_digest":"3718bb6aac536325575b2fe649bbb50dc4bdd72e21a10e02619a9479e9749fef","global_sequence":16911538,"recv_sequence":707,"auth_sequence":[["smallred1111",110]],"code_sequence":36,"abi_sequence":9},"act":{"account":"sakuyatest13","name":"test","authorization":[{"actor":"smallred1111","permission":"active"}],"data":{"index":123321,"msg":"testtest"},"hex_data":"b9e1010000000000087465737474657374"},"context_free":false,"elapsed":132,"console":"index: 123321 msg: testtest ","trx_id":"a46ecb00aacfcf10741016d0ddb763741e8f464d8f224a71d5696f21f9ebbbfb","block_num":16905805,"block_time":"2019-03-28T09:47:56.000","producer_block_id":"0101f64d00369be7bbb211b8fe15b2f69dfef604c063de1061c0cf91c6a61a00","account_ram_deltas":[],"except":null,"inline_traces":[]}}]
     * last_irreversible_block : 17021383
     */

    @Expose
    private int last_irreversible_block;

    @Expose
    private List<Action> actions;

    public int getLast_irreversible_block() {
        return last_irreversible_block;
    }

    public void setLast_irreversible_block(int last_irreversible_block) {
        this.last_irreversible_block = last_irreversible_block;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
