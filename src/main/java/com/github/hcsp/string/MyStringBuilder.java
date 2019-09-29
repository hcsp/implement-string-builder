package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MyStringBuilder {
    char[] value;
    int count;

    public MyStringBuilder() {
        value = new char[16];
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        if (count + 1 - value.length > 0) {
            value = Arrays.copyOf(value,
                    count + 1);
        }
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            char[] charArray = new String(bytes, charsetName).toCharArray();
            for (char ch : charArray) {
                this.append(ch);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        if (count + 1 - value.length > 0) {
            value = Arrays.copyOf(value,
                    count + 1);
        }
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count += 1;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        System.arraycopy(value, index + 1, value, index, count - index - 1);
        count--;
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
