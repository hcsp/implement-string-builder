package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyStringBuilder {
    ArrayList<Character> myStringBuilderList;

    public MyStringBuilder() {
        myStringBuilderList = new ArrayList<>();
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        myStringBuilderList.add(ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String byteString = new String(bytes, charsetName);
        char[] chars = byteString.toCharArray();
        for (char charItem : chars) {
            myStringBuilderList.add(charItem);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        myStringBuilderList.add(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        myStringBuilderList.remove(index);
        return this;
    }

    @Override
    public String toString() {
        return myStringBuilderList.stream().map(Objects::toString).collect(Collectors.joining(""));
    }
}
