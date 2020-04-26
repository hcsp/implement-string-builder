package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;

public class MyStringBuilder {
    private char[] str;
    private int size;
    private int charCount;

    public MyStringBuilder() {
        size = 16;
        str = new char[16];
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        String str = String.valueOf(ch);
        while (resizeRequired(str)) {
            resizeBuffer(str);
        }
        this.str[charCount++] = ch;
        return this;
    }

    public MyStringBuilder append(String str) {
        while (resizeRequired(str)) {
            resizeBuffer(str);
        }
        addString(str);
        updateCharCount(str.length());
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return append(new String(bytes, charsetName));
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        String str = String.valueOf(ch);
        while (resizeRequired(str)) {
            resizeBuffer(str);
        }
        System.arraycopy(this.str, index, this.str, index + 1, this.charCount - index);
        this.str[index] = ch;
        updateCharCount(str.length());
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if ((index < 0) || (index >= this.charCount)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        System.arraycopy(this.str, index + 1, this.str, index, this.charCount - index - 1);
        this.charCount--;
        return this;
    }

    @Override
    public String toString() {
        return new String(this.str, 0, this.charCount);
    }

    public void resizeBuffer(String input) {
        int oldSize = size;
        size <<= 1;
        char[] newStr = new char[size];

        System.arraycopy(this.str, 0, newStr, 0, oldSize);

        this.str = newStr;
    }

    private boolean resizeRequired(String newInput) {
        return this.charCount + newInput.length() > this.size;
    }

    private void updateCharCount(int charCount) {
        this.charCount += charCount;
    }

    private void addString(String str) {
        System.arraycopy(str.toCharArray(), 0,
                this.str, this.charCount, str.length());
    }

    public static void main(String[] args) {
    }
}
