package com.github.hcsp.string;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyStringBuilder {
    private List<Character> characterList;

    public MyStringBuilder() {
        characterList = new ArrayList<>();
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        characterList.add(ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        char[] chars = new String(bytes, Charset.forName(charsetName)).toCharArray();
        for (char ch : chars) {
            characterList.add(ch);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        List<Character> head = characterList.subList(0, index);
        List<Character> stern = characterList.subList(index, characterList.size());
        characterList = new ArrayList<>();
        characterList.addAll(head);
        characterList.add(ch);
        characterList.addAll(stern);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        List<Character> head = characterList.subList(0, index);
        List<Character> stern = characterList.subList(index + 1, characterList.size());
        characterList = new ArrayList<>();
        characterList.addAll(head);
        characterList.addAll(stern);
        return this;
    }

    @Override
    public String toString() {
        return characterList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
