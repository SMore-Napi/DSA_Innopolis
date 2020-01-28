package week2;

import java.util.EmptyStackException;

public class TestStack {
    public static void main(String[] args) {
        myStack<Integer> sample = new ArrayStack<>(2);
        System.out.println(sample.pop());
        sample.pop();
        sample.pop();
    }
}

class ArrayStack<T> implements myStack<T> {

    T[] storage;
    int capacity;
    int size;

    public ArrayStack(int size) {
        this.size = size;
        capacity = 0;
        storage = (T[]) new Object[size];

    }

    void doubleCapacity() {
        T[] newStorage = (T[]) new Object[2 * capacity];

        for (int i = 0; i < capacity; i++) {
            newStorage[i] = storage[i];
        }
        capacity *= 2;
        storage = newStorage;
    }

    @Override
    public void push(T value) {
        if (size == capacity) {
            doubleCapacity();
        }
        storage[size++] = value;

    }

    @Override
    public T pop() throws EmptyStackException {
        T value = peek();
        size--;
        return value;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return storage[size - 1];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}

interface myStack<T> {
    void push(T value);

    T pop();

    T peek();

    int size();

    boolean isEmpty();

}
