
import java.util.ArrayList;
import java.util.LinkedList;

class Entry<K,V> {
    Entry(K k, V v) { key = k; value = v; }
    K key; V value;
};

public class HashTable<K,V> implements Map<K,V> {

    ArrayList<LinkedList<Entry<K,V>>> table;

    public HashTable() {
        int m = 20;
        table = new ArrayList<>(m);
        for (int i = 0; i != m; ++i)
            table.add(new LinkedList<>());
    }

    public boolean contains(K key) { return findEntry(key) != null; }

    public V get(K key) throws Exception {
        Entry<K,V> e = findEntry(key);
        if (e == null)
            throw new Exception("there is no entry with key " + key.toString());
        else
            return e.value;
    }

    /*
     * TODO
     *
     * The put() method should associate the value with the key, so that
     * subsequent invocations of get() on the same key should return the value.
     */
    public void put(K key, V value) {

        Entry<K,V> e = findEntry(key);
        Entry<K,V> entry = new Entry<>(key, value);
        int m = table.size();
        if(e == null){
            table.get(key.hashCode()%m).add(entry);
        }else{
            findEntry(key).value = value;
        }
    }

    /*
     * TODO
     *
     * The remove() method removes the entry whose key matches the key parameter
     * from the hashtable, if such an entry exists.
     */
    public void remove(K key) {
        Entry<K,V> e = findEntry(key);
        int m = table.size();
        if(e != null){
            table.get(key.hashCode()%m).remove(e);
        }

    }

    /**********************
     * TODO
     *
     * The findEntry() helper function returns an entry whose key matches the key parameter,
     * or else returns null if there is not such an entry in the table.
     */

    protected Entry<K,V> findEntry(K key) {

        int m = table.size();
        for(Entry<K,V> entry : table.get(key.hashCode()%m)){
            if(entry.key.equals(key)){
                return entry;
            }
        }return null;
    }

}