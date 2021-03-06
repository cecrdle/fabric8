package io.fabric8.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    public void notEmptyTest() {
        
        Assert.assertTrue(Strings.notEmpty("Some text"));
        Assert.assertTrue(Strings.notEmpty(" "));
        Assert.assertFalse(Strings.notEmpty(""));
        Assert.assertFalse(Strings.notEmpty(null));
        
    }
    
    @Test
    public void nullIfEmptyTest() {
        
        Assert.assertNotNull(Strings.nullIfEmpty("Some text"));
        Assert.assertNotNull(Strings.nullIfEmpty(" "));
        Assert.assertNull(Strings.nullIfEmpty(""));
        Assert.assertNull(Strings.nullIfEmpty(null));        
        
    }
    
    @Test
    public void emptyIfNullTest() {
        
        Assert.assertEquals("Some text", Strings.emptyIfNull("Some text"));
        Assert.assertEquals(" ", Strings.emptyIfNull(" "));
        Assert.assertEquals("", Strings.emptyIfNull(""));
        Assert.assertEquals("", Strings.emptyIfNull(null));        
        
    }
    
    @Test
    public void defaultIfEmptyTest() {
        
        Assert.assertEquals("Some text", Strings.defaultIfEmpty("Some text", "default"));
        Assert.assertEquals(" ", Strings.defaultIfEmpty(" ", "default"));
        Assert.assertEquals("default", Strings.defaultIfEmpty("", "default"));
        Assert.assertEquals("default", Strings.defaultIfEmpty(null, "default"));
        
    }
    
    @Test
    public void splitAsListTest() {
        
        List<String> list = Strings.splitAsList("a,b, c ,d", ",");
        
        Assert.assertTrue(list.size() == 4);
        
        Assert.assertEquals("a", list.get(0));
        Assert.assertEquals("b", list.get(1));
        Assert.assertEquals(" c ", list.get(2));
        Assert.assertEquals("d", list.get(3));
        
        list = Strings.splitAsList("a,b,c;,d,", ";");

        Assert.assertTrue(list.size() == 2);

        list = Strings.splitAsList("a,b,c;,d,", "~");

        Assert.assertTrue(list.size() == 1);

        list = Strings.splitAsList(null, "~");

        Assert.assertTrue(list.size() == 0);

        list = Strings.splitAsList("", null);

        Assert.assertTrue(list.size() == 0);

        try {
            Strings.splitAsList("a,b,c,d", null);
            Assert.fail("Expected NullPointerException but nothing happened");
        } catch(NullPointerException e) {
            //OK
        }
        
    }
    
    @Test
    public void splitAndTrimAsListTest() {
        
        List<String> list = Strings.splitAndTrimAsList("  a  ,  b  ,  c  ,  d  ", ",");
        
        Assert.assertTrue(list.size() == 4);

        Assert.assertEquals("a", list.get(0));
        Assert.assertEquals("b", list.get(1));
        Assert.assertEquals("c", list.get(2));
        Assert.assertEquals("d", list.get(3));
        
        list = Strings.splitAndTrimAsList("a,b,c;,d,", ";");

        Assert.assertTrue(list.size() == 2);

        list = Strings.splitAndTrimAsList("a,b,c;,d,", "~");

        Assert.assertTrue(list.size() == 1);

        list = Strings.splitAndTrimAsList(null, "~");

        Assert.assertTrue(list.size() == 0);

        list = Strings.splitAndTrimAsList("", null);

        Assert.assertTrue(list.size() == 0);

        try {
            Strings.splitAndTrimAsList("a,b,c,d", null);
            Assert.fail("Expected NullPointerException but nothing happened");
        } catch(NullPointerException e) {
            //OK
        }
        
    }
    
    @Test
    public void joinTest() {
        
        Assert.assertEquals("a,b,3,d,1.2,f,null", Strings.join(",", "a", "b", 3, "d", 1.2d, "f", null));
        Assert.assertEquals("a b 3d1.2fnull", Strings.join("", "a", " b ", 3, "d", 1.2d, "f", null));
        Assert.assertEquals("", Strings.join(""));
        
    }
    
    @Test
    public void joinNotNullTest() {
        
        Assert.assertEquals("a,b,3,d,1.2,f", Strings.joinNotNull(",", "a", "b", 3, "d", 1.2d, "f", null));
        Assert.assertEquals("a b 3d1.2f", Strings.joinNotNull("", "a", " b ", 3, "d", 1.2d, "f", null));
        Assert.assertEquals("", Strings.joinNotNull(""));     
        
    }
    
    @Test
    public void toStringTest() {
        
        Assert.assertEquals("foobar", Strings.toString("foobar"));
        Assert.assertEquals("12345", Strings.toString(12345));
        Assert.assertEquals("null", Strings.toString(null));
        
    }
    
    @Test
    public void unquoteTest() {
        
        Assert.assertEquals("foobar", Strings.unquote("\"foobar\""));
        Assert.assertEquals("\"foobar\"", Strings.unquote("\"\"foobar\"\""));
        Assert.assertEquals("'foobar'", Strings.unquote("'foobar'"));
        Assert.assertNull(Strings.unquote(null));
        
    }
    
    @Test
    public void isNullOrBlankTest() {
        
        Assert.assertTrue(Strings.isNullOrBlank(null));
        Assert.assertTrue(Strings.isNullOrBlank(""));
        Assert.assertTrue(Strings.isNullOrBlank(" "));
        Assert.assertTrue(Strings.isNullOrBlank("   "));
        Assert.assertFalse(Strings.isNullOrBlank("foobar"));       
        
    }
    
    @Test
    public void isNotBlankTest() {
        
        Assert.assertFalse(Strings.isNotBlank(null));
        Assert.assertFalse(Strings.isNotBlank(""));
        Assert.assertFalse(Strings.isNotBlank(" "));
        Assert.assertFalse(Strings.isNotBlank("   "));
        Assert.assertTrue(Strings.isNotBlank("foobar"));      
        
    }
    
    @Test
    public void parseDelimitedStringTest() {
        
        List<String> list = Strings.parseDelimitedString("a,b,3, d ,some space ,f", ",");

        Assert.assertTrue(list.size() == 6);

        Assert.assertEquals("a", list.get(0));
        Assert.assertEquals("b", list.get(1));
        Assert.assertEquals("3", list.get(2));
        Assert.assertEquals("d", list.get(3));
        Assert.assertEquals("some space", list.get(4));
        Assert.assertEquals("f", list.get(5));

        list = Strings.parseDelimitedString("a,b,3, d ,some space ,f", ",", false);

        Assert.assertTrue(list.size() == 6);

        Assert.assertEquals("a", list.get(0));
        Assert.assertEquals("b", list.get(1));
        Assert.assertEquals("3", list.get(2));
        Assert.assertEquals(" d ", list.get(3));
        Assert.assertEquals("some space ", list.get(4));
        Assert.assertEquals("f", list.get(5));

    }
    
    
}
