package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder() {
        this(16);
    }

    public MyStringBuilder(int capacity) {
        value = new char[capacity];
    }

    private void ensureEnoughCapacity(int minimumCapacity) {
        if (minimumCapacity > value.length) {
            value = Arrays.copyOf(value, value.length * 2);
        }
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureEnoughCapacity(count + 1);
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        char[] toBeAppended = new String(bytes, Charset.forName(charsetName)).toCharArray();
        int len = toBeAppended.length;
        ensureEnoughCapacity(count + len);
        System.arraycopy(toBeAppended, 0, value, count, len);
        count += len;
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        ensureEnoughCapacity(count + 1);
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count++;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (index < 0 || index >= count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        System.arraycopy(value, index + 1, value, index, count - index - 1);
        count--;
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
