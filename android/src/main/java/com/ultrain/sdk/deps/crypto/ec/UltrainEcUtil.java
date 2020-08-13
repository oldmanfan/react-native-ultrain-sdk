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
package com.ultrain.sdk.deps.crypto.ec;

import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

import com.ultrain.sdk.deps.crypto.digest.Ripemd160;
import com.ultrain.sdk.deps.crypto.digest.Sha256;
import com.ultrain.sdk.deps.crypto.util.Base58;
import com.ultrain.sdk.deps.crypto.util.BitUtils;
import com.ultrain.sdk.deps.util.RefValue;
import com.ultrain.sdk.deps.util.StringUtils;

/**
 * Created by swapnibble on 2018-02-02.
 */

public class UltrainEcUtil {

    public static final String PREFIX_K1 = "K1";
    public static final String PREFIX_R1 = "R1";

//    public static byte[] decodeUltrainCrypto(String base58Data, RefValue<CurveParam> curveParamRef, RefValue<Long> checksumRef ){
//
//        final byte[] retKeyData;
//
//        final String typePrefix;
//        if ( base58Data.startsWith( ULTRAIN_PREFIX ) ) {
//
//            if ( base58Data.startsWith( PREFIX_K1, ULTRAIN_PREFIX.length())) {
//                typePrefix = PREFIX_K1;
//            }
//            else
//            if ( base58Data.startsWith( PREFIX_R1, ULTRAIN_PREFIX.length())) {
//                typePrefix = PREFIX_R1;
//            }
//            else {
//                typePrefix = null;
//            }
//
//            retKeyData = getBytesIfMatchedRipemd160( base58Data.substring( ULTRAIN_PREFIX.length() ), typePrefix, checksumRef);
//        }
//        else{
//            typePrefix = null;
//            retKeyData = getBytesIfMatchedSha256( base58Data, checksumRef );
//        }
//
//        if ( curveParamRef != null) {
//            curveParamRef.data = EcTools.getCurveParam( PREFIX_R1.equals( typePrefix ) ? CurveParam.SECP256_R1 : CurveParam.SECP256_K1);
//        }
//
//        return retKeyData;
//    }

    public static byte[] extractFromRipemd160( String base58Data ) {
        byte[] data= Base58.decode( base58Data );
        if ( data[0] == data.length) {
            return Arrays.copyOfRange(data, 2, data.length );
        }

        return null;
    }

//    public static byte[] getBytesIfMatchedRipemd160(String base58Data, String prefix, RefValue<Long> checksumRef ){
//        byte[] prefixBytes = StringUtils.isEmpty(prefix) ? new byte[0] : prefix.getBytes();
//
//        byte[] data= Base58.decode( base58Data.substring( prefixBytes.length));
//
//        byte[] toHashData = new byte[data.length - 4 + prefixBytes.length];
//        System.arraycopy( data, 0, toHashData, 0, data.length - 4); // key data
//
//        System.arraycopy( prefixBytes, 0, toHashData, data.length - 4, prefixBytes.length);
//
//        Ripemd160 ripemd160 = Ripemd160.from( toHashData); //byte[] data, int startOffset, int length
//        long checksumByCal = BitUtils.uint32ToLong( ripemd160.bytes(), 0);
//        long checksumFromData= BitUtils.uint32ToLong(data, data.length - 4 );
//        if ( checksumByCal != checksumFromData ) {
//            throw new IllegalArgumentException("Invalid format, checksum mismatch");
//        }
//
//        if ( checksumRef != null ){
//            checksumRef.data = checksumFromData;
//        }
//
//        return Arrays.copyOfRange(data, 0, data.length - 4);
//    }

    public static byte[] getBytesIfMatchedRipemd160(String base58Data, String prefix, RefValue<Long> checksumRef ){
        byte[] prefixBytes = StringUtils.isEmpty(prefix) ? new byte[0] : prefix.getBytes();

        byte[] data= Base58.decode( base58Data );

        byte[] toHashData = new byte[data.length - 4 + prefixBytes.length];
        System.arraycopy( data, 0, toHashData, 0, data.length - 4); // key data

        System.arraycopy( prefixBytes, 0, toHashData, data.length - 4, prefixBytes.length);

        Ripemd160 ripemd160 = Ripemd160.from( toHashData); //byte[] data, int startOffset, int length
        long checksumByCal = BitUtils.uint32ToLong( ripemd160.bytes(), 0);
        long checksumFromData= BitUtils.uint32ToLong(data, data.length - 4 );
        if ( checksumByCal != checksumFromData ) {
            throw new IllegalArgumentException("Invalid format, checksum mismatch");
        }

        if ( checksumRef != null ){
            checksumRef.data = checksumFromData;
        }

        return Arrays.copyOfRange(data, 0, data.length - 4);
    }

    public static byte[] getBytesIfMatchedSha256(String base58Data, RefValue<Long> checksumRef ){
        byte[] data= Base58.decode( base58Data );

        // offset 0은 제외, 뒤의 4바이트 제외하고, private key 를 뽑자
        Sha256 checkOne = Sha256.from( data, 0, data.length - 4 );
        Sha256 checkTwo = Sha256.from( checkOne.getBytes() );
        if ( checkTwo.equalsFromOffset( data, data.length - 4, 4)
                || checkOne.equalsFromOffset( data, data.length - 4, 4) ){

            if ( checksumRef != null ){
                checksumRef.data = BitUtils.uint32ToLong( data, data.length - 4);
            }

            return Arrays.copyOfRange(data, 1, data.length - 4);
        }

        throw new IllegalArgumentException("Invalid format, checksum mismatch");
    }

//    public static String encodeUltrainCrypto(byte[] data, CurveParam curveParam ) {
//        boolean isR1 = ( null != curveParam ) && curveParam.isType( CurveParam.SECP256_R1);
//
//        byte[] toHashData = new byte[ data.length + (isR1 ? PREFIX_R1.length() : 0) ];
//        System.arraycopy( data, 0, toHashData, 0, data.length);
//        if ( isR1 ) {
//            System.arraycopy( PREFIX_R1.getBytes(), 0, toHashData, data.length, PREFIX_R1.length());
//        }
//
//        byte[] result = new byte[ data.length + 4 ];
//
//        Ripemd160 ripemd160 = Ripemd160.from( toHashData); //byte[] data, int startOffset, int length
//        byte[] checksumBytes = ripemd160.bytes();
//
//        System.arraycopy( data, 0, result, 0, data.length); // copy source data
//        System.arraycopy( checksumBytes, 0, result, data.length, 4); // copy checksum data
//
//        return ULTRAIN_PREFIX + ( isR1 ? PREFIX_R1 : "") + Base58.encode( result );
//    }

    public static String encodeUltrainCrypto(String prefix, CurveParam curveParam, byte[] data ) {
        String typePart = "";
        if ( curveParam != null ) {
            if ( curveParam.isType( CurveParam.SECP256_K1)) {
                typePart = PREFIX_K1;
            }
            else
            if ( curveParam.isType( CurveParam.SECP256_R1)){
                typePart = PREFIX_R1;
            }
        }

        byte[] toHashData = new byte[ data.length + typePart.length() ];
        System.arraycopy( data, 0, toHashData, 0, data.length);
        if ( typePart.length() > 0 ) {
            System.arraycopy( typePart.getBytes(), 0, toHashData, data.length, typePart.length());
        }

        byte[] dataToEncodeBase58 = new byte[ data.length + 4 ];

        Ripemd160 ripemd160 = Ripemd160.from( toHashData);
        byte[] checksumBytes = ripemd160.bytes();

        System.arraycopy( data, 0, dataToEncodeBase58, 0, data.length); // copy source data
        System.arraycopy( checksumBytes, 0, dataToEncodeBase58, data.length, 4); // copy checksum data


        String result;
        if ( StringUtils.isEmpty( typePart)) {
            result = prefix;
        }
        else {
            result = prefix + ULTRAIN_CRYPTO_STR_SPLITTER + typePart + ULTRAIN_CRYPTO_STR_SPLITTER;
        }

        return result + Base58.encode( dataToEncodeBase58 );
    }




    private static final String ULTRAIN_CRYPTO_STR_SPLITTER = "_";
    public static String[] safeSplitUltrainCryptoString(String cryptoStr ) {
        if ( StringUtils.isEmpty( cryptoStr)) {
            return new String[]{ cryptoStr };
        }

        try {
            return cryptoStr.split( ULTRAIN_CRYPTO_STR_SPLITTER );
        }
        catch (PatternSyntaxException e){
            e.printStackTrace();
            return new String[]{ cryptoStr };
        }
    }

    public static String concatUltrainCryptoStr(String... strData ) {

        String result="";

        for ( int i = 0; i < strData.length; i++) {
            result += strData[i] + ( i < strData.length -1 ? ULTRAIN_CRYPTO_STR_SPLITTER : "");
        }
        return result;
    }

    public static CurveParam getCurveParamFrom(String curveType ) {
        return EcTools.getCurveParam( PREFIX_R1.equals( curveType ) ? CurveParam.SECP256_R1 : CurveParam.SECP256_K1);
    }


//    public static UltrainCryptoProperty getUltrainCryptoProperty( String cryptoStr ) {
//        if ( StringUtils.isEmpty( cryptoStr)) {
//            return new UltrainCryptoProperty( cryptoStr );
//        }
//
//        String[] splitted = null;
//        try {
//            splitted = cryptoStr.split(ULTRAIN_CRYPTO_STR_SPLITTER);
//
//            if ( splitted == null || splitted.length <= 1) {
//                return new UltrainCryptoProperty( cryptoStr );
//            }
//
//            return new UltrainCryptoProperty( splitted[0], null, splitted[1]);
//        }
//    }
}
