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


import android.util.Log;

import java.util.Arrays;

/**
 * Created by swapnibble on 2017-09-12.
 */
public class TypeActionName implements UltrainType.Packer {


    private static final String CHAR_MAP = "._0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MAX_NAME_IDX = 21;

    static long char_to_symbol( char c ){
        if (c == 0x2E) // .
            return 0;
        if (c == 0x5F) // _
            return 1;

        if (c >= 0x30 && c <= 0x39) // 0 ~ 9
            return (c - 0x30 + 2);

        if (c >= 0x61 && c <= 0x7a) // a ~ z
            return (c - 0x61 + 12);

        if (c >= 0x41 && c <= 0x5A) // A ~ Z
            return (c - 0x41 + 38);

        return 0xFFL; // ERROR
    }

    static public String action_name_to_string(long hsb, long lsb ) {
        char[] result = new char[MAX_NAME_IDX + 1];
        Arrays.fill( result, '.');

        long sym = 0;
        for( int i = 0; i < MAX_NAME_IDX; ++i ) {
            if (i <= 9) {
                sym = (lsb & 0x3F);
                lsb = lsb >> 6;
            } else if (i == 10) {
                long rb2 = (hsb & 0x3);
                rb2 = rb2 << 4;
                sym = (rb2 | lsb);
                hsb = hsb >> 2;
            } else {
                sym = (hsb & 0x3f);
                hsb = hsb >> 6;
            }
            result[i] = CHAR_MAP.charAt((int)(sym));
        }

        return String.valueOf( result ).replaceAll("[.]+$", ""); // remove trailing dot
    }

    private long mHsb = 0L;
    private long mLsb = 0L;

    public TypeActionName(long hsb, long lsb) {
        mHsb = hsb;
        mLsb = lsb;
    }

    public TypeActionName(String name) {
        if (name == null) {
            mHsb = 0L;
            mLsb = 0L;
        } else {
            int nlen = name.length();
            long temp = 0L;
            for (int i = 0; i < nlen; i++) {
                long sym = char_to_symbol(name.charAt(i));
                if (i <= 9) {
                    temp |= (sym << (6 * i));
                } else if (i == 10) {
                    long rb4 = (sym & 0xF);
                    temp |= (rb4 << (6 * i));
                    mLsb = temp;

                    temp = ((sym & 0x30) >> 4);
                } else {
                    temp |= (sym << (6 * (i - 11) + 2));
                }

                if (nlen <= 10) { mLsb = temp; }
                else { mHsb = temp; }
            }
        }
    }

    @Override
    public void pack(UltrainType.Writer writer) {
        writer.putLongLE(mHsb);
        writer.putLongLE(mLsb);
    }

    @Override
    public String toString() {
        return action_name_to_string(mHsb, mLsb);
    }
}
