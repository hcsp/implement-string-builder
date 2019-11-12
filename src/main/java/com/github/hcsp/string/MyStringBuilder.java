package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class MyStringBuilder {
    char[] value;
    int count;

    public MyStringBuilder() {
        value = new char[2];
        count = 0;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        count += 1;
        checkSize();
        value[count - 1] = ch;
        return this;
    }

    public MyStringBuilder append(char[] chs) {
        for (char ch : chs) {
            append(ch);
        }
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String string = new String(bytes, charsetName);
        this.append(string.toCharArray());
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        char[] charsLeft = Arrays.copyOfRange(value, 0, index);
        char[] charsRight = Arrays.copyOfRange(value, index, count);
        count = 0;
        value = new char[2];
        append(charsLeft);
        append(ch);
        append(charsRight);
        return this;
    }


    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        char[] charsLeft = Arrays.copyOfRange(value, 0, index);
        char[] charsRight = Arrays.copyOfRange(value, index + 1, count);
        count = 0;
        value = new char[2];
        append(charsLeft);
        append(charsRight);
        return this;
    }

    private void checkSize() {
        if (value.length < count + 1) {
            value = Arrays.copyOf(value, value.length << 2);
        }
    }

    @Override
    public String toString() {
        return new String(Arrays.copyOf(value, count));
    }
}
