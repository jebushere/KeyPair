package com.demo;

import java.util.Objects;
import java.util.Optional;

public final class KeyPair<K, V> {

    private final Pair<K, V>[] pairs;

    public KeyPair() {
        pairs = new Pair[10];
    }

    public V get(K key) {
        assert Objects.nonNull(key);
        for (Pair<K, V> pair : pairs) {
            if (Objects.nonNull(pair.getKey()) && pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public void put(K key, V value) {
        assert Objects.nonNull(key);
        Optional<Integer> index = Optional.empty();
        for (int i = 0; i < pairs.length; i++) {
            Pair<K, V> pair = pairs[i];
            if (Objects.isNull(pairs[i]) || pair.getKey().equals(key)) {
                index = Optional.of(i);
                break;
            }
        }
        pairs[index.orElseThrow(() -> new RuntimeException("Maximum size reached"))] = new Pair<>(key, value);
    }

}

final class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<K, V> pair = (Pair<K, V>) o;
        return !Objects.equals(key, pair.key);
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
