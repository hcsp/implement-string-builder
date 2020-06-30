package com.github.hcsp.string;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] value = new char[16];
    private int capacity = 16;
    private int endIndex = 0;

    public MyStringBuilder() {
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacity(endIndex + 1);
        value[endIndex] = ch;
        endIndex++;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        CharsetDecoder decoder = charset.newDecoder();
        try {
            CharBuffer decode = decoder.decode(ByteBuffer.wrap(bytes));
            ensureCapacity(endIndex + decode.length() + 1);
            decode.chars().forEach((ch) -> {
                System.out.println((char) ch);
                value[endIndex++] = (char) ch;
            });
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        if (index >= endIndex) {
            ensureCapacity(index + 1);
            value[index] = ch;
            endIndex = index + 1;
        } else {
            ensureCapacity(endIndex + 1);
            char oldValue = value[index];
            value[index] = ch;
            endIndex++;
            for (int i = (index + 1); i < endIndex; i++) {
                char curr = value[i];
                value[i] = oldValue;
                oldValue = curr;
            }
        }
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (index < endIndex) {
            for (int i = index; i < endIndex; i++) {
                value[i] = value[i + 1];
            }
            endIndex--;
        }
        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, endIndex);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            capacity = capacity << 1;
            value = Arrays.copyOf(value, capacity);
        }
    }
}
