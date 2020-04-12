package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public final class MyStringBuilder {
    /**
     * 存储字符
     */
    char[] value;

    /**
     * 统计字符数
     */
    int count;

    public int length() {
        return count;
    }

    public int capacity() {
        return value.length;
    }

    public MyStringBuilder() {
        this(16);
    }

    public MyStringBuilder(int capacity) {
        this.value = new char[capacity];
    }

    private void ensureCapacityInternal(int minimumCapacity) {
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
        return (newCapacity <= 0 || Integer.MAX_VALUE - 8 - newCapacity < 0)
                ? hugeCapacity(minCapacity)
                : newCapacity;
    }

    private int hugeCapacity(int minCapacity) {
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
        return (minCapacity > Integer.MAX_VALUE - 8)
                ? minCapacity : Integer.MAX_VALUE - 8;
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
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String str = new String(bytes, charsetName);
        if (str == null) {
            return appendNull();
        }
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    private MyStringBuilder appendNull() {
        int c = count;
        ensureCapacityInternal(c + 4);
        final char[] value = this.value;
        value[c++] = 'n';
        value[c++] = 'u';
        value[c++] = 'l';
        value[c++] = 'l';
        count = c;
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        ensureCapacityInternal(count + 1);
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count += 1;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if ((index < 0) || (index >= count)) {
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
