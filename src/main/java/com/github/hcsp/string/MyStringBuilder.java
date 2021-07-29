package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder() {
        this.value = new char[16];
        this.count = 0;
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            int newCapacity = (value.length << 1) + 2;
            value = Arrays.copyOf(value, newCapacity);
        }
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacityInternal(count + 1);
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            String string = new String(bytes, charsetName);
            char[] chars = string.toCharArray();
            ensureCapacityInternal(count + chars.length);
            System.arraycopy(chars, 0, value, count, chars.length);
            count += chars.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        ensureCapacityInternal(count);
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count++;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        System.arraycopy(value, index + 1, value, index, count - index);
        count--;
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
