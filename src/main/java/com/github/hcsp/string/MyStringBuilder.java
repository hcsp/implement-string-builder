package com.github.hcsp.string;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class MyStringBuilder {
    String s = "";

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        s += ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            Charset charset = Charset.forName(charsetName);
            CharsetDecoder charsetDecoder = charset.newDecoder();
            this.s = charsetDecoder.decode(ByteBuffer.wrap(bytes)).toString();
            return this;
        } catch (Exception e) {
            throw new RuntimeException("字符集错误");
        }
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        s = s.substring(0, index) + String.valueOf(ch) + s.substring(index);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        s = s.substring(0, index) + s.substring(index + 1);
        return this;
    }

    @Override
    public String toString() {
        return s;
    }
}
