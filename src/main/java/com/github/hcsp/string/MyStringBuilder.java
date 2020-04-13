package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MyStringBuilder {
    ArrayList<Character> myStringBuilder;

    public MyStringBuilder() {
        myStringBuilder = new ArrayList<>();
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        myStringBuilder.add(ch);
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String string = new String(bytes, charsetName);
        char[] chars = string.toCharArray();
        for (char Char : chars) {
            myStringBuilder.add(Char);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        myStringBuilder.add(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        myStringBuilder.remove(index);
        return this;
    }

    @Override
    public String toString() {
        char[] content = new char[myStringBuilder.size()];
        for (int i = 0; i < myStringBuilder.size(); i++) {
            content[i] = myStringBuilder.get(i);
        }
        return new String(content);
    }
}
