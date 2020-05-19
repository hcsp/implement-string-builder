package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MyStringBuilder {

    private char[] value;

    private int count;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyStringBuilder() {
        value = new char[16];
    }

    public MyStringBuilder(int capacity) {
        value = new char[capacity];
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
        if (minimumCapacity - value.length > 0) {
            value = Arrays.copyOf(value,
                    newCapacity(minimumCapacity));
        }
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int newCapacity = (value.length << 1) + 2;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
                ? hugeCapacity(minCapacity)
                : newCapacity;
    }

    private int hugeCapacity(int minCapacity) {
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
        return Math.max(minCapacity, MAX_ARRAY_SIZE);
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacityInternal(count + 1);
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间的关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        // 给定的字节数组是按指定的编码方式encode后的，
        // 需要再原路decode成字符串，该字符串此后按照当前项目默认的编码方式进行编解码
        String string = new String(bytes, charsetName);
        append(string);
        return this;
    }

    // 在末尾添加字符串
    public MyStringBuilder append(String str) {
        if (str == null)
            return this;
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {}

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {}

    @Override
    public String toString() {
        return null;
    }

    public static void main(String[] args) {

    }
}
