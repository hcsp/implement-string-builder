package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStringBuilderTest {
    MyStringBuilder sb = new MyStringBuilder();

    @Test
    public void canAppendALotOfChars() {
        for (int i = 0; i < 10000; i++) {
            sb.append('a');
        }
        Assertions.assertEquals(10000, sb.toString().length());
    }

    @Test
    public void canAppendBytes() throws UnsupportedEncodingException {
        sb.append("今天天气不错".getBytes("GBK"), "GBK");
        Assertions.assertEquals("今天天气不错", sb.toString());
    }

    @Test
    public void canInsertChar() throws UnsupportedEncodingException {
        sb.append("今天天气不错".getBytes("GBK"), "GBK");
        sb.insert(2, '哈');
        Assertions.assertEquals("今天哈天气不错", sb.toString());
    }

    @Test
    public void canDeleteChar() throws UnsupportedEncodingException {
        sb.append("今天天气不错".getBytes("GBK"), "GBK");
        sb.deleteCharAt(2);
        sb.deleteCharAt(2);
        Assertions.assertEquals("今天不错", sb.toString());
    }
}
