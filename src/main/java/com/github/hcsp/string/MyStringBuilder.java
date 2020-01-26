package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;

public class MyStringBuilder {
    private char[] chars;
    private int capacity;
    private int count;

    public MyStringBuilder() {
        chars = new char[8];
        this.capacity = 8;
        this.count = 0;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        if (this.count + 1 > this.capacity) {
            ensureCapacity(count + 1);
        }
        chars[count] = ch;
        count += 1;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        char[] decodeBytes = new char[0];
        try {
            decodeBytes = new String(bytes, charsetName).toCharArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ((this.count + decodeBytes.length) > this.capacity) {
            ensureCapacity(this.count + decodeBytes.length);
        }
        System.arraycopy(decodeBytes, 0, this.chars, count, decodeBytes.length);
        this.count += decodeBytes.length;
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        if (this.count + 1 >= this.capacity) {
            ensureCapacity(this.count + 1);
        }
        char[] newChars = new char[this.capacity];
        System.arraycopy(this.chars, 0, newChars, 0, index);
        System.arraycopy(this.chars, index, newChars, index + 1, this.count - index);
        newChars[index] = ch;
        this.chars = newChars;
        this.count += 1;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        char[] newChars = new char[this.capacity];
        System.arraycopy(this.chars, 0, newChars, 0, index);
        System.arraycopy(this.chars, index + 1, newChars, index, this.count - index);
        this.chars = newChars;
        this.count -= 1;
        return this;
    }

    @Override
    public String toString() {
        int length = this.count;
        char[] newChars = new char[length];
        System.arraycopy(chars, 0, newChars, 0, length);
        return String.valueOf(newChars);
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > this.capacity) {
            char[] newChars = new char[minimumCapacity];
            System.arraycopy(this.chars, 0, newChars, 0, count);
            this.chars = newChars;
            this.capacity = minimumCapacity;
        }
    }
}
