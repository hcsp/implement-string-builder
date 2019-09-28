package com.github.hcsp.string;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public final class MyStringBuilder extends MyAbstractStringBuilder {
    public MyStringBuilder() {
    }

    @Override
    public Appendable append(CharSequence csq) {
        return null;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) {
        return null;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        try {
            super.append(ch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        char[] chars = new char[0];
        try {
            chars = new String(bytes, charsetName).toCharArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (char c : chars
        ) {
            try {
                super.append(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        super.insert(index, ch);
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        super.deleteCharAt(index);
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public char charAt(int index) {
        return value[index];
    }


    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > count) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (start > end) {
            throw new StringIndexOutOfBoundsException(start - end);
        }
        return new String(value, start, end);
    }


}

abstract class MyAbstractStringBuilder implements Appendable, CharSequence {
    char[] value = new char[0];
    int count = 0;

    public MyAbstractStringBuilder append(char c) throws IOException {
        ensureCapacityEnough(count + 1);
        value[count++] = c;
        return this;
    }

    private void ensureCapacityEnough(int minSize) {
        if (minSize - value.length > 0) {
            //简单的扩容
            value = Arrays.copyOf(value, newCapacity(minSize));
        }

    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int newCapacity(int minCapacity) {
        int newCapacity = (value.length << 1) + 2;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
                ? hugeCapacity(minCapacity)
                : newCapacity;
    }

    private int hugeCapacity(int minCapacity) {
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
        return Math.max(minCapacity, MAX_ARRAY_SIZE);
    }

    public MyAbstractStringBuilder insert(int index, char ch) {
        if (index < 0 || index > value.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        ensureCapacityEnough(value.length + 1);
        //将index之后的元素通过复制，向后移动一位
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count++;
        return this;
    }

    public MyAbstractStringBuilder deleteCharAt(int index) {
        if (index < 0 || index > value.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        //向前一位复制数组实现删除功能
        System.arraycopy(value, index + 1, value, index, count - index - 1);
        count--;
        return this;
    }

    public abstract String toString();
}
