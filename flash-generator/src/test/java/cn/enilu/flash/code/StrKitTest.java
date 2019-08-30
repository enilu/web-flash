package cn.enilu.flash.code;

import org.junit.Assert;
import org.junit.Test;

public class StrKitTest {

  @Test
  public void testFirstCharToLowerCase() {
    Assert.assertEquals("foo", StrKit.firstCharToLowerCase("foo"));
    Assert.assertEquals("1234", StrKit.firstCharToLowerCase("1234"));
    Assert.assertEquals("bar", StrKit.firstCharToLowerCase("Bar"));
  }

  @Test
  public void testFirstCharToUpperCase() {
    Assert.assertEquals("BAZ", StrKit.firstCharToUpperCase("BAZ"));
    Assert.assertEquals("Foo", StrKit.firstCharToUpperCase("foo"));
  }

  @Test
  public void testIsEmpty() {
    Assert.assertTrue(StrKit.isEmpty(null));
    Assert.assertTrue(StrKit.isEmpty(""));

    Assert.assertFalse(StrKit.isEmpty("1a 2b 3c"));
  }

  @Test
  public void testIsNotEmpty() {
    Assert.assertTrue(StrKit.isNotEmpty(","));

    Assert.assertFalse(StrKit.isNotEmpty(null));
  }

  @Test
  public void testNullToEmpty() {
    Assert.assertEquals("1a 2b 3c", StrKit.nullToEmpty("1a 2b 3c"));
  }

  @Test
  public void testNullToDefault() {
    Assert.assertNull(StrKit.nullToDefault(null, null));

    Assert.assertEquals("3", StrKit.nullToDefault("3", "1a 2b 3c"));
  }

  @Test
  public void testToUnderlineCase() {
    Assert.assertNull(StrKit.toUnderlineCase(null));

    Assert.assertEquals("1", StrKit.toUnderlineCase("1"));
    Assert.assertEquals("foo", StrKit.toUnderlineCase("foo"));
    Assert.assertEquals("bar", StrKit.toUnderlineCase("Bar"));
    Assert.assertEquals("baz", StrKit.toUnderlineCase("BAZ"));
    Assert.assertEquals("a1_b2_c3", StrKit.toUnderlineCase("A1B2C3"));
  }

  @Test
  public void testToCamelCase() {
    Assert.assertNull(StrKit.toCamelCase(null));

    Assert.assertEquals("a\'b\'c", StrKit.toCamelCase("a\'b\'c"));
    Assert.assertEquals("fooBar", StrKit.toCamelCase("foo_bar"));
  }
}
