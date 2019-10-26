package com.github.hcsp.string;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyStringBuilder {
    char[] value;
    int size;

    public MyStringBuilder() {
        value = new char[16];
        int size = 0;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        return insert(size, ch);
    }

    private void resize(int newCapacity) {
        char[] newValue = Arrays.copyOf(value, newCapacity);
        value = newValue;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        char[] appendCh = charset.decode(bb).array();
        int appendLength = appendCh.length;
        enSureCapacity(appendLength);
        System.arraycopy(appendCh, 0, value, size, appendLength);
        size += appendLength;
        return this;
    }

    //     在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        if (index < 0 || index > value.length) {
            throw new IllegalArgumentException("非法索引");
        }
        enSureCapacity(1);
        for (int i = value.length - 1; i > index; i--) {
            value[i] = value[i - 1];
        }
        value[index] = ch;
        size++;
        return this;
    }

    private void enSureCapacity(int length) {
        if (length + size > value.length) {
            resize((value.length << 1 > length + size)
                    ? value.length << 1
                    : length + size + 16);
        }
        if (length + size < (value.length >> 2)
                && value.length >> 1 > 16) {
            resize(value.length >> 1);
        }
    }

    //     删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (size == 0) {
            throw new NoSuchElementException("当前字符串为空");
        }
        if (index < 0 || index > value.length - 1) {
            throw new IllegalArgumentException("非法索引");
        }
        for (int i = index; i < value.length - 1; i++) {
            value[i] = value[i + 1];
        }
        size--;
        enSureCapacity(-1);
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, size);
    }
}
