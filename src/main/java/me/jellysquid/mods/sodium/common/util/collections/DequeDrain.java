package me.jellysquid.mods.sodium.common.util.collections;

import java.util.Iterator;
import java.util.Deque;

public class DequeDrain<T> implements Iterator<T> {
    private final Deque<T> deque;

    public DequeDrain(Deque<T> deque) {
        this.deque = deque;
    }

    @Override
    public boolean hasNext() {
        return !this.deque.isEmpty();
    }

    @Override
    public T next() {
        return this.deque.remove();
    }
}
