package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;

public class MyStringBuilder {
    private char[] chars;

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        if (chars == null) {
            generateNewChars(ch);
        } else {
            appendCharToChars(ch);
        }
        return this;
    }

    private void appendCharToChars(char ch) {
        char[] newChars = new char[chars.length + 1];
        System.arraycopy(chars, 0, newChars, 0, chars.length);
        newChars[chars.length] = ch;
        chars = newChars;
    }

    private void generateNewChars(char ch) {
        chars = new char[1];
        chars[0] = ch;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        String string = new String(bytes, charsetName);
        for (char c :
                string.toCharArray()) {
            append(c);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        char[] newChars = new char[chars.length + 1];
        System.arraycopy(chars, 0, newChars, 0, index);
        newChars[index] = ch;
        System.arraycopy(chars, index, newChars, index + 1, chars.length - index);
        chars = newChars;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        char[] newChars = new char[chars.length - 1];
        System.arraycopy(chars, 0, newChars, 0, index);
        System.arraycopy(chars, index + 1, newChars, index, chars.length - index - 1);
        chars = newChars;
        return this;
    }

    @Override
    public String toString() {
//        return Arrays.toString(chars);  // 如果使用这一行 那么 一万长度的数组会返回三万长度的字符串..... 有点反直觉
        return String.valueOf(this.chars);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("今天天气不错".getBytes("GBK"), "GBK");
        sb.deleteCharAt(2);
        sb.deleteCharAt(2);
        System.out.println(sb.toString());
    }
}
