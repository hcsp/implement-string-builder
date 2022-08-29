package com.github.hcsp.string;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MyStringBuilder {
    //参考的AbstractStringBuilder类
    char[] value;
    int count;

    public MyStringBuilder() {
        value = new char[16];
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacityInternal(count + 1);
        value[count++] = ch;
        return this;
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        //minimumCapacity是需要的最小容量，当它比value的length大时，说明需要扩容了
        if (minimumCapacity - value.length > 0) {
            value = Arrays.copyOf(value,
                    newCapacity(minimumCapacity));
        }
    }

    private int newCapacity(int minimumCapacity) {
        int newCapacity = (value.length << 1) + 2;
        if (newCapacity - minimumCapacity < 0) {
            newCapacity = minimumCapacity;
        }
        return newCapacity;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        //将byte->char
        Charset cs = Charset.forName(charsetName);
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        char[] chars = cb.array();

        //加到数组末尾
        int len = chars.length;
        ensureCapacityInternal(count + len);
        System.arraycopy(chars, 0, value, count, len);
        count += len;
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int offset, char ch) {
        ensureCapacityInternal(count + 1);
        System.arraycopy(value, offset, value, offset + 1, count - offset);
        value[offset] = ch;
        count += 1;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        System.arraycopy(value, index + 1, value, index, count - index - 1);
        count -= 1;
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
