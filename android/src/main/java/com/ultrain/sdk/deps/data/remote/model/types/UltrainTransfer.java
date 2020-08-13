/*
 * Copyright (c) 2017-2018 PlayerOne.
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ultrain.sdk.deps.data.remote.model.types;


import com.google.gson.annotations.Expose;

import java.util.Map;

import com.ultrain.sdk.deps.crypto.util.HexUtils;

/**
 * Created by swapnibble on 2017-09-15.
 */

public class UltrainTransfer implements UltrainType.Packer {
    @Expose
    private TypeAccountName from;

    @Expose
    private TypeAccountName to;

    @Expose
    private TypeAsset quantity;

    @Expose
    private String memo;

    public UltrainTransfer(Map<String, Object> agrs) {

    }

    public UltrainTransfer(String from, String to, long quantity, String memo ) {
        this( new TypeAccountName(from), new TypeAccountName(to), quantity, memo );
    }

    public UltrainTransfer(TypeAccountName from, TypeAccountName to, long quantity, String memo ) {
        this.from = from;
        this.to = to;
        this.quantity = new TypeAsset(quantity);
        this.memo = memo != null ? memo : "";
    }

    public String getActionName() {
        return "transfer";
//        return "test";
    }


    @Override
    public void pack(UltrainType.Writer writer) {

        from.pack(writer);
        to.pack(writer);

        writer.putLongLE(quantity.getAmount());

        writer.putString(memo);
    }

    public String getAsHex() {
        UltrainType.Writer writer = new UltrainByteWriter(128);
        pack(writer);

        return HexUtils.toHex( writer.toBytes() );
    }
}
