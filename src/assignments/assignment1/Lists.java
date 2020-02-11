package assignments.assignment1;

import java.util.LinkedList;

public class Lists {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("t");

        System.out.println(list.remove("g"));


    }

}

/**
 * An interface of a List (Abstract Data Type).
 *
 * @param <E> type of elements which the list contains
 */
interface List<E> {
    /**
     * Check if the list is empty.
     *
     * @return true - if a list doesn't contain any element, false - otherwise
     */
    boolean isEmpty();

    /**
     * Add element E at specific position.
     *
     * @param index   - position of element
     * @param element - an element E to add
     */
    void add(int index, E element);

    /**
     * Add element E to the start of the list.
     *
     * @param element - an element E to add
     */
    void addFirst(E element);

    /**
     * Add element E to the end of the list.
     *
     * @param element - an element E to add
     */
    void addLast(E element);

    /**
     * Delete element E if it exists in the list.
     *
     * @param element - an element to remove from the list
     * @return true - if this element was in the list, false - otherwise
     */
    boolean delete(E element);

    /**
     * Delete element at specific position.
     *
     * @param index - position of an element
     * @return an element E which was removed from the list
     */
    E delete(int index);

    /**
     * Remove the first element from the list.
     *
     * @return an element E which was removed from the list
     */
    E deleteFirst();

    /**
     * Remove the last element from the list.
     *
     * @return an element E which was removed from the list
     */
    E deleteLast();

    /**
     * Replace element at specific position with new element E.
     *
     * @param index   - position of an element
     * @param element - new element E to add
     * @return old element E which was replaced on this position
     */
    E set(int index, E element);

    /**
     * Retrieve element at specific position.
     *
     * @param index - position of an element
     * @return an element E which is placed on this position
     */
    E get(int index);
}

/**
 * Implementation of a List using an dynamic array.
 *
 * @param <E> type of elements which the list contains
 */
class DynamicArrayList<E> implements List<E>{

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public boolean delete(E element) {
        return false;
    }

    @Override
    public E delete(int index) {
        return null;
    }

    @Override
    public E deleteFirst() {
        return null;
    }

    @Override
    public E deleteLast() {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }
}

/**
 * Implementation of a List using nodes.
 *
 * @param <E> type of elements which the list contains
 */
class DoublyLinkedList<E> implements List<E>{

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public boolean delete(E element) {
        return false;
    }

    @Override
    public E delete(int index) {
        return null;
    }

    @Override
    public E deleteFirst() {
        return null;
    }

    @Override
    public E deleteLast() {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }
}