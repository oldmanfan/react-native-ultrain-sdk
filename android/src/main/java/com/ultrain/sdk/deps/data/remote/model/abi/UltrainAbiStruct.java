package com.ultrain.sdk.deps.data.remote.model.abi;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by swapnibble on 2017-12-22.
 */

public class UltrainAbiStruct {

    @Expose
    public String name;

    @Expose
    public String base;

    @Expose
    public List<UltrainAbiField> fields;

    @Override
    public String toString() {
        return "Struct name: " + name + ", base: " + base ;
    }
}
