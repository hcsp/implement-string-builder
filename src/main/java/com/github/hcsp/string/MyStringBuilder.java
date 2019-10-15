package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;

public class MyStringBuilder {
    private StringBuilder stringBuilder;

    public MyStringBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        this.stringBuilder.append(ch);
        return this;
    }

    private static class TranscodingException extends RuntimeException {
        TranscodingException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        try {
            stringBuilder.append(new String(bytes, 0, bytes.length, charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new TranscodingException("输入的编码为：" + charsetName + "的操作发生了异常", e);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        stringBuilder.insert(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        stringBuilder.deleteCharAt(index);
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        MyStringBuilder sb = new MyStringBuilder();
        String s2 = new String("哈哈".getBytes("GBK"), 0, "哈哈".getBytes("GBK").length, "GBK");
        System.out.println(s2);
        sb.append("哈哈哈".getBytes("GBK"), "GBK");
        System.out.println(sb);
    }
}
