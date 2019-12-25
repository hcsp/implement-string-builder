package com.github.hcsp.string;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MyStringBuilder {
    char[] value = new char[10];
    int count = 0;

    public MyStringBuilder() {

    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacityInternal(count + 1);
        value[count++] = ch;
        return this;
    }

    private void ensureCapacityInternal(int minLength) {
        if (minLength - value.length > 0) {
            value = Arrays.copyOf(value, 2 * value.length);
        }
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String string = new String(bytes, charsetName);
        string.chars().forEach(i -> append((char) i));
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int offset, char c) {
        ensureCapacityInternal(count + 1);
        System.arraycopy(value, offset, value, offset + 1, count - offset);
        value[offset] = c;
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
