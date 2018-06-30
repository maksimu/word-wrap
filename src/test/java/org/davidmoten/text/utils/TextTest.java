package org.davidmoten.text.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextTest {

    @Test
    public void test() {
        checkWrap("hello there", "hello\nthere");
    }
    
    @Test
    public void test2() {
        checkWrap("hello", "hello");
    }
    
    @Test
    public void testEmpty() {
        checkWrap("", "");
    }
    
    @Test
    public void testOneLetter() {
        checkWrap("a", "a");
    }
    
    @Test
    public void testSpaceThenOneLetter() {
        checkWrap(" a", " a");
    }
    
    @Test
    public void testNewLine() {
        checkWrap("hello\nthere", "hello\nthere");
    }
    
    @Test
    public void testCarriageReturnNewLine() {
        checkWrap("hello\r\nthere", "hello\nthere");
    }
    
    @Test
    public void testWhitespaceConservedAfterNewLine() {
        checkWrap("hello\n there", "hello\n there");
    }
    
    @Test
    public void testLongWordForcesBreak() {
        checkWrap("hellothere", "helloth\nere");
    }

    private void checkWrap(String text, String expected) {
        String s = Text.wordWrap(text, 6);
        System.out.println(s);
        assertEquals(expected, s);
    }

}
