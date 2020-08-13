package com.ultrain.sdk.deps.data.remote.model.api;

import com.google.gson.annotations.Expose;

import com.ultrain.sdk.deps.data.remote.model.types.TypeName;
import com.ultrain.sdk.deps.util.StringUtils;

/**
 * Created by swapnibble on 2018-04-16.
 */
public class GetRequestForCurrency {
    @Expose
    protected boolean json = false;

    @Expose
    protected TypeName code;

    @Expose
    protected String symbol;

    public GetRequestForCurrency(String tokenContract, String symbol){
        this.code = new TypeName(tokenContract);
        this.symbol = StringUtils.isEmpty(symbol) ? null : symbol;
    }
}
