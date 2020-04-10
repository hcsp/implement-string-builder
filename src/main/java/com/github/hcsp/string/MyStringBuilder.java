package com.github.hcsp.string;

import java.nio.charset.Charset;
import java.util.Arrays;

public class MyStringBuilder {

    private char[] value;

    private int count;

    public MyStringBuilder() {
        value = new char[16];
    }

    // 在末尾添加一个字符串
    public MyStringBuilder append(String str) {
        int len = str.length();
        resizeIfNeed(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        resizeIfNeed(count + 1);
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        String str = new String(bytes, Charset.forName(charsetName));
        return append(str);
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        resizeIfNeed(count + 1);
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
        trimSizeIfNeed();
        count--;
        return this;
    }

    @Override
    public String toString() {
        trimSizeIfNeed();
        return new String(value);
    }

    private void resizeIfNeed(int minLength) {
        if (minLength - value.length > 0) {
            value = Arrays.copyOf(value, newCapacity(minLength));
        }
    }

    private void trimSizeIfNeed() {
        if (count < value.length) {
            value = Arrays.copyOf(value, count);
        }
    }

    private int newCapacity(int minLength) {
        int newCapacity = (value.length << 1) + 2;
        if (newCapacity - minLength < 0) {
            newCapacity = minLength;
        }
        return newCapacity;
    }
}
