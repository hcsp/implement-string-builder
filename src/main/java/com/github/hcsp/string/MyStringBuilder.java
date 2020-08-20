package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] chars;
    private int count = 0;

    public MyStringBuilder() {
        chars = new char[16];
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        char[] newChars = ensureCapacity(count + 1);
        if (newChars == null) {
            chars[count] = ch;
        } else {
            newChars[count] = ch;
            chars = newChars;
        }
        count++;
        return this;
    }

    private char[] ensureCapacity(int capacity) {
        return capacity - chars.length > 0 ? Arrays.copyOf(chars, (chars.length << 1)) : null;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String s = new String(bytes, charsetName);
        char[] newChars = new char[chars.length + s.length()];
        System.arraycopy(chars, 0, newChars, 0, count);
        System.arraycopy(s.toCharArray(), 0, newChars, count, s.length());
        chars = newChars;
        count += s.length();
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        rangeCheck(index);
        System.arraycopy(chars, index, chars, index + 1, count - index);
        chars[index] = ch;
        count++;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        rangeCheck(index);
        System.arraycopy(chars, index + 1, chars, index, count - index - 1);
        count--;
        return this;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > count - 1) {
            throw new StringIndexOutOfBoundsException("offset " + index + ",length " + count);
        }
    }

    @Override
    public String toString() {
        return new String(chars, 0, count);
    }
}
