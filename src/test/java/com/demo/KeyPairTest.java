package com.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class KeyPairTest {

    @Test(expected = AssertionError.class)
    public void putByNullKeyFailure() {
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        keyPair.put(null, 123);
    }

    @Test(expected = AssertionError.class)
    public void getByNullFailure() {
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        keyPair.get(null);
    }

    @Test
    public void putByNullValueSuccess() {
        final String key = "Test1";
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        keyPair.put(key, null);
        Assert.assertNull(keyPair.get(key));
    }

    @Test
    public void putByKeyValueSuccess() {
        final String key = "Test1";
        final Integer value = 123;
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        keyPair.put(key, value);
        Assert.assertEquals(value, keyPair.get(key));
    }

    @Test
    public void putByDuplicateKeyValueSuccess() {
        final String key = "Test1";
        final Integer value = 123;
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        keyPair.put(key, 1);
        keyPair.put(key, value);
        Assert.assertEquals(value, keyPair.get(key));
    }

    @Test
    public void putMoreThanArraySizeBySameKeyValueSuccess() {
        final String key = "Test1";
        final Integer value = 123;
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        IntStream.range(0, 115).forEach(i -> keyPair.put(key, value));
        Assert.assertEquals(value, keyPair.get(key));
    }

    @Test(expected = RuntimeException.class)
    public void putMoreThanArraySizeByDifferentKeyValueSuccess() {
        final Integer value = 123;
        KeyPair<String, Integer> keyPair = new KeyPair<>();
        IntStream.range(0, 115).forEach(i -> keyPair.put(Integer.toString(i), value));
    }

}
