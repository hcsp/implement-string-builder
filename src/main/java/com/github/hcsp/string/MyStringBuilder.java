package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;

public class MyStringBuilder {
    private final int MAX_LENGTH = Short.MAX_VALUE;
    private char[] chars = new char[MAX_LENGTH];
    private int length = 0;

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        if (chars.length == MAX_LENGTH) {
            chars = new char[MAX_LENGTH << 1];
        }
        chars[length++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            String bytesString = new String(bytes, charsetName);

            if (chars.length + bytes.length > MAX_LENGTH) {
                chars = new char[MAX_LENGTH << 1];
            }
            String result = String.valueOf(chars).substring(0, length) + bytesString;
            chars = result.toCharArray();
            length += chars.length;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        if (chars.length == MAX_LENGTH) {
            chars = new char[MAX_LENGTH << 1];
        }
        if (index < length) {
            String head = String.valueOf(chars).substring(0, index);
            String tail = String.valueOf(chars).substring(index);
            chars = (head + ch + tail).toCharArray();
            length += 1;
        }
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (index < length) {
            String head = String.valueOf(chars).substring(0, index);
            String tail = String.valueOf(chars).substring(index + 1);
            chars = (head + tail).toCharArray();
            length -= 1;
        }
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(chars).substring(0, length);
    }
}
