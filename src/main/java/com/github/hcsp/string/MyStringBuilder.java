package com.github.hcsp.string;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyStringBuilder {
    List<Character> buffer = new ArrayList<>();

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        buffer.add(ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        char[] chars = new String(bytes, Charset.forName(charsetName)).toCharArray();
        for (char ch : chars
        ) {
            append(ch);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        List<Character> head = buffer.subList(0, index);
        List<Character> tail = buffer.subList(index, buffer.size());
        buffer = new ArrayList<>();
        buffer.addAll(head);
        append(ch);
        buffer.addAll(tail);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        List<Character> head = buffer.subList(0, index);
        List<Character> tail = buffer.subList(index, buffer.size());
        buffer = new ArrayList<>();
        buffer.addAll(head);
        tail.remove(0);
        buffer.addAll(tail);
        return this;
    }

    @Override
    public String toString() {
        return buffer.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
