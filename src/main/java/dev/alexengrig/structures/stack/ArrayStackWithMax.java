package dev.alexengrig.structures.stack;

import dev.alexengrig.structures.annotation.O;

import java.util.Comparator;

public class ArrayStackWithMax<E extends Comparable<E>> extends ArrayStack<E> implements StackWithMax<E> {
    protected final Comparator<E> comparator;
    protected final ArrayStack<E> maxStack;

    public ArrayStackWithMax() {
        this(Comparator.naturalOrder());
    }

    public ArrayStackWithMax(Comparator<E> comparator) {
        this.comparator = comparator;
        this.maxStack = createMaxStack();
    }

    protected ArrayStack<E> createMaxStack() {
        return new ArrayStack<>();
    }

    @O("1")
    @Override
    public E max() {
        requireNonEmpty();
        return maxStack.top();
    }

    @O("1")
    @Override
    public void push(E value) {
        super.push(value);
        if (maxStack.empty() || comparator.compare(value, maxStack.top()) >= 0) {
            maxStack.push(value);
        }
    }

    @O("1")
    @Override
    public E pop() {
        E target = super.pop();
        if (target.equals(maxStack.top())) {
            maxStack.pop();
        }
        return target;
    }

    @O("1")
    @Override
    public E top() {
        E value = super.top();
        if (comparator.compare(value, maxStack.top()) > 0) {
            return maxStack.top();
        }
        return value;
    }
}
