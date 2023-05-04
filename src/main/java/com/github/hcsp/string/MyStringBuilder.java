package com.github.hcsp.string;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyStringBuilder {
    List<Character> myCharList;

    public MyStringBuilder() {
        myCharList = new ArrayList<>();
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        myCharList.add(ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        myCharList.addAll(new String(bytes, Charset.forName(charsetName)).chars()
                .mapToObj(e -> (char) e).collect(Collectors.toList()));
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        myCharList.add(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        myCharList.remove(index);
        return this;
    }

    @Override
    public String toString() {
        return  myCharList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

    }
}
