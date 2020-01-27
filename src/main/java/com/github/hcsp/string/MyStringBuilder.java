package com.github.hcsp.string;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] values = new char[0]; // 初始为空
    private static int DEFAULT_CAPACITY = 32; //默认初始分配32字节
    private int length = 0; //初始长度
    //检测容量是否足够，不足的情况下自动扩容
    private void ensureCapacity(){
        if (values.length==0 || length + 1 > values.length){
            values = Arrays.copyOf(values, Math.max((int) (values.length*1.5), DEFAULT_CAPACITY));
        }
    }

    public MyStringBuilder() {
        values = new char[DEFAULT_CAPACITY];
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        this.ensureCapacity();
        this.values[length] = ch;
        length++;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        for (byte b : bytes){
            byteBuffer.put(b);
        }
        byteBuffer.flip();
        CharBuffer charBuffer = charset.decode(byteBuffer);
        for (char ch : charBuffer.array()){
            append(ch);
        }

        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        ensureCapacity();
        if (index==length){
            this.append(ch);
        }else {
            char[] newValues = new char[values.length];
            char[] leftArr = Arrays.copyOfRange(values, 0, index);
            char[] rightArr = Arrays.copyOfRange(values, index, length);
            //通过系统方法实现复制
            System.arraycopy(leftArr,  0, newValues, 0, leftArr.length);
            newValues[index] = ch;
            System.arraycopy(rightArr, 0, newValues, index+1, rightArr.length);
            this.values = newValues;
            length++;
        }
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (index<=length-1){
            char[] leftArr = Arrays.copyOfRange(values, 0, index);
            char[] rightArr = Arrays.copyOfRange(values, index+1, length);
            char[] newValues = new char[values.length];
            //通过系统方法实现复制
            System.arraycopy(leftArr, 0, newValues, 0, leftArr.length);
            System.arraycopy(rightArr, 0, newValues, leftArr.length, rightArr.length);
            this.values = newValues;
            length--;
        }

        return this;
    }

    @Override
    public String toString() {
        return new String(Arrays.copyOfRange(values, 0, length));
    }

}
