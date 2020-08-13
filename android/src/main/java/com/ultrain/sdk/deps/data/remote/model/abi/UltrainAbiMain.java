package com.ultrain.sdk.deps.data.remote.model.abi;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by swapnibble on 2017-12-22.
 */

public class UltrainAbiMain {

    @Expose
    public List<UltrainAbiTypeDef> types;

    @Expose
    public List<UltrainAbiAction> actions;

    @Expose
    public List<UltrainAbiStruct> structs;

    @Expose
    public List<UltrainAbiTable> tables;
}
