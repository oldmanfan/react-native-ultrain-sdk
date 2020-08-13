package com.ultrain.sdk.deps.data.remote.model.abi;

import com.google.gson.annotations.Expose;

/**
 * Created by swapnibble on 2017-12-22.
 */

public class UltrainAbiAction {
    @Expose
    public String ricardian_contract;

    @Expose
    public String name;

    @Expose
    public String type;

    @Expose
    public String ability;  // 'normal' or 'pureview'

    @Override
    public String toString(){
        return "UltrainAction: " + name + ", type: "+ type + ", ability: " + ability ;
    }
}
