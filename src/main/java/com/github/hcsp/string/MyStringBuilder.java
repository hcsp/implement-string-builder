package com.github.hcsp.string;

public class MyStringBuilder {
    public MyStringBuilder() {}

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {}

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {}

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {}

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {}

    @Override
    public String toString() {
        return null;
    }
}
