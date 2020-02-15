package assignments.assignment1;

// Uncomment it to use junit tests.
/*
import org.junit.Test;
import static org.junit.Assert.*;
*/


/**
 * @author Roman Soldatov B19-02
 * <p>
 * 2.1 Implementing List ADT
 * <p>
 * Class for testing lists.
 * It requires to import the junit library.
 * <p>
 * Maybe your IDE does not import this library automatically. So, then this class won't compile because of errors.
 * So, just in case I commented it out.
 * Well, you can uncomment the code inside this class and import junit library to check tests.
 */
public class Lists {
    // Here you can choose a list's implementation to test.

    private DynamicArrayList<String> list = new DynamicArrayList<>();
    // private DoublyLinkedList<String> list = new DoublyLinkedList<>();

    // Uncomment it to use junit tests.
    /*

    private void fillList() {
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        list.addLast("4");
        list.addLast("5");
        list.addLast("6");
    }

    // boolean isEmpty()
    @Test
    public void testIsEmptyOnEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmptyOnNonEmptyList() {
        fillList();
        assertFalse(list.isEmpty());
    }

    // void addLast(E element)
    @Test
    public void testAddLast() {
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        list.addLast("4");
        list.addLast("5");
        list.addLast("6");

        String expected = "[1, 2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    // void addFirst(E element)
    @Test
    public void testAddFirst() {
        fillList();
        list.addFirst("7");
        list.addFirst("8");

        String expected = "[8, 7, 1, 2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    // add(int index, E element)
    @Test
    public void testAdd() {
        fillList();
        list.add(3, "7");

        String expected = "[1, 2, 3, 7, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtFirstPosition() {
        fillList();
        list.add(0, "7");

        String expected = "[7, 1, 2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtLastPosition() {
        fillList();
        list.add(6, "7");

        String expected = "[1, 2, 3, 4, 5, 6, 7]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        fillList();
        list.add(-1, "7");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexOutOfSize() {
        fillList();
        list.add(7, "7");
    }

    // boolean delete(E element)
    @Test
    public void testDeleteElement() {
        fillList();
        list.delete("2");

        String expected = "[1, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteNonExistElement() {
        fillList();
        list.delete("8");

        String expected = "[1, 2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteEqualElements() {
        fillList();
        list.add(1, "4");
        list.delete("4");

        String expected = "[1, 2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    // E delete(int index)
    @Test
    public void testDeleteByIndex() {
        fillList();
        list.delete(2);

        String expected = "[1, 2, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteAtFirstPosition() {
        fillList();
        list.delete(0);

        String expected = "[2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteAtLastPosition() {
        fillList();
        list.delete(5);

        String expected = "[1, 2, 3, 4, 5]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteNegativeIndex() {
        fillList();
        list.delete(-4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteIndexOutOfSize() {
        fillList();
        list.delete(6);
    }

    // E deleteFirst()
    @Test
    public void testDeleteFirst() {
        fillList();
        list.deleteFirst();

        String expected = "[2, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteFirstInEmptyList() {
        list.deleteFirst();
    }

    // E deleteLast()
    @Test
    public void testDeleteLast() {
        fillList();
        list.deleteLast();

        String expected = "[1, 2, 3, 4, 5]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteLastInEmptyList() {
        list.deleteLast();
    }

    // set(int index, E element)
    @Test
    public void testSet() {
        fillList();
        list.set(1, "9");

        String expected = "[1, 9, 3, 4, 5, 6]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOnNegativeIndex() {
        fillList();
        list.set(-1, "10");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOnOutBoundSize() {
        fillList();
        list.set(6, "10");
    }

    // E get(int index)
    @Test
    public void testGet() {
        fillList();

        String expected = "3";
        String actual = list.get(2);
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOnNegativeIndex() {
        fillList();
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOnOutBoundSize() {
        fillList();
        list.get(6);
    }

    */
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
class DynamicArrayList<E> implements List<E> {

    private int size; // count of elements which the array has
    private E[] array;
    private final int COEFFICIENT_INCREASE_SIZE = 2; // the coefficient by which we have to multiply an array to extend the capacity

    /**
     * Default Constructor. Initial capacity is 4.
     */
    public DynamicArrayList() {
        this(4);
    }

    /**
     * @param capacity - initial capacity for an array.
     */
    public DynamicArrayList(int capacity) {
        array = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Extends the real size of the array.
     * O(n) - linearly copying elements.
     */
    private void extendArray() {
        E[] newArray = (E[]) new Object[array.length * COEFFICIENT_INCREASE_SIZE];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * O(1).
     *
     * @return the count of array's elements
     */
    public int size() {
        return size;
    }

    /**
     * Converts array into the string.
     * O(n) - linearly appending elements.
     *
     * @return the list of elements
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            string.append(array[i]).append(", ");
        }
        if (size != 0) {
            string.append(array[size - 1]);
        }
        string.append("]");

        return string.toString();
    }

    /**
     * Check if the list is empty.
     * O(1).
     *
     * @return true - if the size is 0, false - otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add element E at specific position.
     * Shift elements whose indices are greater than the position of element to add.
     * O(n) - linearly shifting elements.
     *
     * @param index   - position of element
     * @param element - an element E to add
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        if (array.length == size) {
            extendArray();
        }
        if (size - index >= 0) System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Add element E at first position.
     * O(n) - linearly shifting elements.
     *
     * @param element - an element E to add
     */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Add element E at last position
     * O(n) - in a worst case linearly extending an array.
     *
     * @param element - an element E to add
     */
    @Override
    public void addLast(E element) {
        add(size, element);
    }

    /**
     * Delete element E if it exists in the list.
     * Shift elements whose indices are greater than the position of element to delete.
     * O(n) - linearly searching and shifting elements.
     *
     * @param element - an element to remove from the list
     * @return true - if this element was in the list, false - otherwise
     */
    @Override
    public boolean delete(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                if (size - 1 >= i) {
                    System.arraycopy(array, i + 1, array, i, size - 1 - i);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Delete element by index.
     * Shift elements whose indices are greater than the position of element to delete.
     * O(n) - linearly shifting elements.
     *
     * @param index - position of an element
     * @return an element E which was removed from the list
     */
    @Override
    public E delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        E elementToDelete = array[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        size--;

        return elementToDelete;
    }

    /**
     * Remove the first element from the list.
     * Shift elements whose indices are greater than the position of element to delete.
     * O(n) - linearly shifting elements.
     *
     * @return an element E which was removed from the list
     */
    @Override
    public E deleteFirst() {
        return delete(0);
    }

    /**
     * Remove the last element from the list.
     * O(1).
     *
     * @return an element E which was removed from the list
     */
    @Override
    public E deleteLast() {
        return delete(size - 1);
    }

    /**
     * Replace element at specific position with new element E.
     * O(1).
     *
     * @param index   - position of an element
     * @param element - new element E to add
     * @return old element E which was replaced on this position
     */
    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        E elementToReplace = array[index];
        array[index] = element;

        return elementToReplace;
    }

    /**
     * Retrieve element at specific position.
     * O(1).
     *
     * @param index - position of an element
     * @return an element E which is placed on this position
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        return array[index];
    }
}

/**
 * Implementation of a List using nodes.
 *
 * @param <E> type of elements which the list contains
 */
class DoublyLinkedList<E> implements List<E> {

    private Node<E> header;
    private Node<E> trailer;
    private int size;

    /**
     * Default constructor
     */
    DoublyLinkedList() {
        header = new Node<>();
        trailer = new Node<>();
        size = 0;
    }

    /**
     * Add the first element to the list.
     * O(1).
     *
     * @param element to add
     */
    private void addFirstElement(E element) {
        Node<E> node = new Node<>();
        node.element = element;

        // Linking header with node.
        header.next = node;
        node.previous = header;

        // Linking trailer with node.
        trailer.previous = node;
        node.next = trailer;

        size++;
    }

    /**
     * Find current node with position = index.
     * O(n) - linearly iterating nodes.
     *
     * @param index - node's position
     * @return the node which position = index
     */
    private Node<E> getNode(int index) {
        Node<E> iterator;

        // Start search from the beginning.
        if (index < (size / 2 + size % 2)) {
            iterator = header.next;
            for (int i = 0; i < index; i++) {
                iterator = iterator.next;
            }
        }

        // Start search from the end.
        else {
            iterator = trailer.previous;
            for (int i = size - 1; i > index; i--) {
                iterator = iterator.previous;
            }
        }

        return iterator;
    }

    /**
     * Check if the list is empty.
     * O(1).
     *
     * @return true - if size = 0, false - otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add element E at specific position.
     * O(n) - linearly iterating nodes.
     *
     * @param index   - position of element
     * @param element - an element E to add
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        if (size == 0) {
            addFirstElement(element);
        } else {
            if (index == 0) {
                addFirst(element);
            } else if (index == size) {
                addLast(element);
            } else {
                Node<E> iterator = getNode(index);

                Node<E> node = new Node<>();
                node.element = element;

                // Linking new node with previous node
                node.previous = iterator.previous;
                iterator.previous.next = node;

                // Linking new node with next node
                node.next = iterator;
                iterator.previous = node;

                size++;
            }
        }
    }

    /**
     * Add element E to the start of the list.
     * O(1).
     *
     * @param element - an element E to add
     */
    @Override
    public void addFirst(E element) {

        if (size == 0) {
            addFirstElement(element);
        } else {
            Node<E> node = new Node<>();
            node.element = element;

            // Linking new node with first node
            node.next = header.next;
            header.next.previous = node;

            // Linking new node with header
            node.previous = header;
            header.next = node;

            size++;
        }
    }

    /**
     * Add element E to the end of the list.
     * O(1).
     *
     * @param element - an element E to add
     */
    @Override
    public void addLast(E element) {

        if (size == 0) {
            addFirstElement(element);
        } else {
            Node<E> node = new Node<>();
            node.element = element;

            // Linking new node with last node
            node.previous = trailer.previous;
            trailer.previous.next = node;

            // Linking new node with trailer
            node.next = trailer;
            trailer.previous = node;

            size++;
        }
    }

    /**
     * Delete element E if it exists in the list.
     * O(n) - linear search of a node to delete.
     *
     * @param element - an element to remove from the list
     * @return true - if this element was in the list, false - otherwise
     */
    @Override
    public boolean delete(E element) {
        Node<E> node = header.next;

        for (int i = 0; i < size; i++) {
            if (node.element.equals(element)) {
                // Linking previous node with next node.
                node.previous.next = node.next;
                // Linking next node with previous node.
                node.next.previous = node.previous;

                size--;
                return true;
            }
            node = node.next;
        }

        return false;
    }

    /**
     * Delete element at specific position.
     * O(n) - linear search of a node to delete.
     *
     * @param index - position of an element
     * @return an element E which was removed from the list
     */
    @Override
    public E delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        if (index == 0) {
            deleteFirst();
        } else if (index == size - 1) {
            deleteLast();
        } else {
            Node<E> node = getNode(index);

            // Linking previous node with next node.
            node.previous.next = node.next;
            // Linking next node with previous node.
            node.next.previous = node.previous;

            size--;
        }

        return null;
    }

    /**
     * Remove the first element from the list.
     * O(1).
     *
     * @return an element E which was removed from the list
     */
    @Override
    public E deleteFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("The list is empty.");
        }

        Node<E> node = header.next;

        // Linking the header with next node.
        header.next = node.next;
        // Linking next node with the header.
        node.next.previous = header;

        size--;
        return node.element;
    }

    /**
     * Remove the last element from the list.
     * O(1).
     *
     * @return an element E which was removed from the list
     */
    @Override
    public E deleteLast() {

        if (size == 0) {
            throw new IndexOutOfBoundsException("The list is empty.");
        }

        Node<E> node = trailer.previous;

        // Linking previous node with the trailer.
        node.previous.next = trailer;
        // Linking the trailer with previous node.
        trailer.previous = node.previous;

        size--;
        return node.element;
    }

    /**
     * Replace element at specific position with new element E.
     * O(n) - linear search of a node to replace.
     *
     * @param index   - position of an element
     * @param element - new element E to add
     * @return old element E which was replaced on this position
     */
    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        Node<E> node = getNode(index);
        E oldValue = node.element;
        node.element = element;
        return oldValue;
    }

    /**
     * Retrieve element at specific position.
     * O(n) - linear search of a node to retrieve.
     *
     * @param index - position of an element
     * @return an element E which is placed on this position
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bound. The list's size: " + size);
        }

        Node<E> node = getNode(index);
        return node.element;
    }

    /**
     * Convert list's elements to string.
     * O(n) - linearly appending elements to string.
     *
     * @return string of elements
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        Node<E> iterator = header.next;
        if (size != 0) {
            for (int i = 0; i < size - 1; i++) {
                string.append(iterator.element).append(", ");
                iterator = iterator.next;
            }

            string.append(iterator.element);
        }

        string.append("]");
        return string.toString();
    }

    /**
     * Node which refers to the next and previous nodes. Also it contains an element's value.
     *
     * @param <E> arbitrary type of an element.
     */
    static private class Node<E> {
        Node<E> next;
        Node<E> previous;
        E element;
    }
}