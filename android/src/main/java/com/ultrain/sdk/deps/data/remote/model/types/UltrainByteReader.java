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

public class UltrainByteReader implements UltrainType.Reader{

   private byte[] _buf;
   private int _index;

   public UltrainByteReader(byte[] buf) {
      _buf = buf;
      _index = 0;
   }

   public UltrainByteReader(byte[] buf, int index) {
      _buf = buf;
      _index = index;
   }

   @Override
   public byte get() throws UltrainType.InsufficientBytesException {
      checkAvailable(1);
      return _buf[_index++];
   }


   @Override
   public int getShortLE() throws UltrainType.InsufficientBytesException {
      checkAvailable(2);
      return (((_buf[_index++] & 0xFF)) | ((_buf[_index++] & 0xFF) << 8)) & 0xFFFF;
   }

   @Override
   public int getIntLE() throws UltrainType.InsufficientBytesException {
      checkAvailable(4);
      return ((_buf[_index++] & 0xFF)) | ((_buf[_index++] & 0xFF) << 8) | ((_buf[_index++] & 0xFF) << 16)
            | ((_buf[_index++] & 0xFF) << 24);
   }


   @Override
   public long getLongLE() throws UltrainType.InsufficientBytesException {
      checkAvailable(8);
      return ((_buf[_index++] & 0xFFL)) | ((_buf[_index++] & 0xFFL) << 8) | ((_buf[_index++] & 0xFFL) << 16)
            | ((_buf[_index++] & 0xFFL) << 24) | ((_buf[_index++] & 0xFFL) << 32) | ((_buf[_index++] & 0xFFL) << 40)
            | ((_buf[_index++] & 0xFFL) << 48) | ((_buf[_index++] & 0xFFL) << 56);
   }

   @Override
   public byte[] getBytes(int size) throws UltrainType.InsufficientBytesException {
      checkAvailable(size);
      byte[] bytes = new byte[size];
      System.arraycopy(_buf, _index, bytes, 0, size);
      _index += size;
      return bytes;
   }

   @Override
   public String getString() throws UltrainType.InsufficientBytesException {
      int size = (int)(getVariableUint() & 0x7FFFFFFF); // put 에서 variable uint 로 넣음.
      byte[] bytes = getBytes(size);
      return new String(bytes);
   }

   @Override
   public long getVariableUint() throws UltrainType.InsufficientBytesException {

      long v = 0;
      byte b, by = 0;
      do {
         b = get();
         v |= ( b & 0x7F) << by;
         by +=7;
      }
      while ( (b & 0x80) != 0 );

      return v;
   }


   private void checkAvailable(int num) throws UltrainType.InsufficientBytesException {
      if (_buf.length - _index < num) {
         throw new UltrainType.InsufficientBytesException();
      }
   }
}
