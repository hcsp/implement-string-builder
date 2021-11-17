package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyStringBuilder {
    public final List<Character> charList = new ArrayList<>();

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        charList.add(charList.size(), ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            String data = new String(bytes, charsetName);
            for (Character ch : data.toCharArray()) {
                append(ch);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        charList.add(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        charList.remove(index);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Character ch : charList) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}
