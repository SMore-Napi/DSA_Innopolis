package week3;

import java.util.*;

public class CodeforcesMap {

    public static void main(String[] args) {

        /**
         * Roman Soldatov
         * B19-02
         **/

        problemB();

    }

    static void problemA() {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        ListMap<String, Integer> map = new ListMap<>();

        // put words in a map
        for (int i = 0; i < n; i++) {
            String word = scanner.next();
            Integer value = map.get(word);
            // in case it is a new word
            if (value == null) {
                map.put(word, 1);

            } // in case this word is already in a map
            else {
                map.put(word, value + 1);
            }
        }

        List<MyEntry<String, Integer>> entries = map.getEntries();
        // sorting
        entries.sort(Comparator
                .comparing((MyEntry<String, Integer> entry1) -> entry1.value).reversed()
                .thenComparing((MyEntry<String, Integer> entry) -> entry.key));


        // printing
        for (MyEntry<String, Integer> entry : entries) {
            System.out.println(entry.key + " " + entry.value);
        }

    }

    static void problemB() {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        // put the first sentence in a map
        for (int i = 0; i < n; i++) {
            String word = scanner.next();
            map1.put(word, 1);
        }


        // put the first sentence in a map
        int m = scanner.nextInt();
        ArrayList<String> list = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            String word = scanner.next();

            // put unique words from the second sentence which are not in the first sentence
            Integer value = map1.get(word);
            if (value == null){
                value = map2.get(word);
                if (value == null){
                    list.add(word);
                    map2.put(word, 1);
                }
            }
        }


        // Output
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }


    }

}

class ListMap<K, V> implements MyMap<K, V> {

    private ArrayList<MyEntry<K, V>> entries;
    private int size;

    public ListMap() {
        entries = new ArrayList<>();
        size = 0;
    }


    @Override
    public V get(K key) {
        for (MyEntry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).key.equals(key)) {
                V oldValue = entries.get(i).value;
                entries.get(i).value = value;
                return oldValue;
            }
        }

        entries.add(new MyEntry<>(key, value));
        size++;
        return null;
    }

    @Override
    public List<MyEntry<K, V>> getEntries() {
        return entries;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();

        for (MyEntry<K, V> entry : entries) {
            list.add(entry.value);
        }
        return list;
    }

    @Override
    public List<K> keySet() {
        List<K> list = new ArrayList<>();

        for (MyEntry<K, V> entry : entries) {
            list.add(entry.key);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).key.equals(key)) {
                V value = entries.get(i).value;
                entries.remove(i);
                size--;
                return value;
            }
        }

        return null;
    }

}

class CoolestHashMap<K, V> implements MyMap<K, V> {

    private ArrayList<MyEntry<K, V>>[] chains;
    private final int ADDRESS_SPACE = 256;
    private int size;

    public CoolestHashMap() {
        this.chains = new ArrayList[ADDRESS_SPACE];
        size = 0;

        for (int i = 0; i < chains.length; i++) {
            chains[i] = new ArrayList<>();
        }
    }

    // hash function + compressing
    private int indexOfKey(K key) {
        return key.hashCode() % ADDRESS_SPACE;
    }

    @Override
    public V get(K key) {

        int index = indexOfKey(key);
        ArrayList<MyEntry<K, V>> list = chains[index];

        for (MyEntry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = indexOfKey(key);
        ArrayList<MyEntry<K, V>> list = chains[index];

        // Changing the value in case the element with this key exists
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                V oldValue = list.get(i).value;
                list.get(i).value = value; // Changing the value of this key
                return oldValue; // return old value of this key
            }
        }

        // Add new entry in case the element with this key doesn't exist
        list.add(new MyEntry<>(key, value));
        size++;

        return null;
    }

    @Override
    public List<MyEntry<K, V>> getEntries() {

        List<MyEntry<K, V>> list = new ArrayList<>();

        // each list from a chain is added to the list
        for (ArrayList<MyEntry<K, V>> chain : chains) {
            list.addAll(chain);
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();

        // Values of each chain are added to the list
        for (ArrayList<MyEntry<K, V>> chain : chains) {
            // Loop for adding entry values of a chain list
            for (MyEntry<K, V> entry : chain) {
                list.add(entry.value);
            }
        }
        return list;
    }

    @Override
    public List<K> keySet() {
        List<K> list = new ArrayList<>();

        // Keys of each chain are added to the list
        for (ArrayList<MyEntry<K, V>> chain : chains) {
            // Loop for adding entry keys of a chain list
            for (MyEntry<K, V> entry : chain) {
                list.add(entry.key);
            }
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // I've implemented this method just in case.
    @Override
    public V remove(K key) {
        int index = indexOfKey(key);
        ArrayList<MyEntry<K, V>> list = chains[index];
        // Finding an entry with the same key
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                // Deleting an entry with this key
                V value = list.get(i).value;
                list.remove(i);
                size--;
                return value;
            }
        }

        return null;
    }
}

class MyEntry<K, V> {
    public K key;
    public V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

interface MyMap<K, V> {
    V get(K key);

    V put(K key, V value);

    List<MyEntry<K, V>> getEntries();

    int size();

    List<V> values();

    List<K> keySet();

    boolean isEmpty();

    V remove(K key);

}