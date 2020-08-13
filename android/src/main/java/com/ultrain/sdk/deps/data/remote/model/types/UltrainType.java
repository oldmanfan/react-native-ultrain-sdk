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

import java.util.Collection;

/**
 * Created by swapnibble on 2017-09-12.
 */

public interface UltrainType {
    class InsufficientBytesException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    interface Packer {
        void pack(UltrainType.Writer writer);
    }

    interface Unpacker {
        void unpack(UltrainType.Reader reader) throws UltrainType.InsufficientBytesException;
    }

    interface Reader {
        byte get() throws UltrainType.InsufficientBytesException;
        int getShortLE() throws UltrainType.InsufficientBytesException;
        int getIntLE() throws UltrainType.InsufficientBytesException;
        long getLongLE() throws UltrainType.InsufficientBytesException;
        byte[] getBytes(int size) throws UltrainType.InsufficientBytesException;
        String getString() throws UltrainType.InsufficientBytesException;

        long getVariableUint() throws UltrainType.InsufficientBytesException;
    }

    interface Writer {
        void put(byte b);
        void putShortLE(short value);
        void putIntLE(int value);
        void putLongLE(long value);
        void putBytes(byte[] value);
        void putString(String value);
        byte[] toBytes();
        int length();

        void putCollection(Collection<? extends Packer> collection);

        void putVariableUInt(long val);
    }
}
