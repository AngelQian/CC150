//package ch1.arraysandstrings;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * Created by AngelQian on 5/30/16.
// */
//public class ImplementHashTable {
//
//    public static void main(String[] args) {
//        System.out.println("Hello World 1!");
//        HashTable<String, String> ht = new HashTable<>(100);
//        ht.put("helen","hello world");
//        String str = ht.get("helen");
//        System.out.print(str);
//    }
//
//}
//
//class HashTable<K, V> implements Map<K, V>, Cloneable, Serializable {
//    private int TB_SIZE;
//    private int size;
//    private LinkedHashNode[] table;
//
//    public HashTable(int TS) {
//        this.TB_SIZE = TS;
//        this.size = 0;
//        this.table = new LinkedHashNode[TS];
//        for (int i = 0; i < TS; i++) {
//            table[i] = null;
//        }
//    }
//
//    @Override
//    public int size() {
//        return this.size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return this.size == 0;
//    }
//
//    @Override
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    final LinkedHashNode<K, V> getNode(int hash, Object key) {
//        LinkedHashNode<K, V>[] tab;
//        LinkedHashNode<K, V> first, e;
//        int n;
//        K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash && // always check first node
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
////                if (first instanceof LinkedHashNode)
////                    return ((LinkedHashNode<K, V>) first).getTreeNode(hash, key);
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    @Override
//    public boolean containsValue(Object value) {
//        LinkedHashNode<K, V>[] tab;
//        V v;
//        if ((tab = table) != null && size > 0) {
//            for (int i = 0; i < tab.length; ++i) {
//                for (LinkedHashNode<K, V> e = tab[i]; e != null; e = e.next) {
//                    if ((v = e.value) == value ||
//                            (value != null && value.equals(v)))
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public V get(Object key) {
//        LinkedHashNode<K, V> nd;
//        return (nd = getNode(hash(key), key)) != null ? nd.value : null;
//    }
//
//    @Override
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        LinkedHashNode<K, V>[] tab;
//        LinkedHashNode<K, V> p;
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            LinkedHashNode<K, V> e;
//            K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
////            else if (p instanceof TreeNode)
////                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
////                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
////                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
////                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
////        ++modCount;
//        if (++size > threshold)
//            resize();
////        afterNodeInsertion(evict);
//        return null;
//    }
//    LinkedHashNode<K,V> newNode(int hash, K key, V value, LinkedHashNode<K,V> next) {
//        return new LinkedHashNode<>(hash, key, value, next);
//    }
//    final LinkedHashNode<K, V>[] resize() {
//        LinkedHashNode<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                    oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold
//        } else if (oldThr > 0) // initial capacity was placed in threshold
//            newCap = oldThr;
//        else {               // zero initial threshold signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
//                    (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        LinkedHashNode<K, V>[] newTab = (LinkedHashNode<K, V>[]) new LinkedHashNode[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                LinkedHashNode<K, V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
////                    else if (e instanceof TreeNode)
////                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//                    else { // preserve order
//                        LinkedHashNode<K, V> loHead = null, loTail = null;
//                        LinkedHashNode<K, V> hiHead = null, hiTail = null;
//                        LinkedHashNode<K, V> next;
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            } else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    @Override
//    public V remove(Object key) {
////        LinkedHashNode<K,V> nd = getNode(hash(key), key);
//        LinkedHashNode<K, V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }
//
//    final LinkedHashNode<K, V> removeNode(int hash, Object key, Object value,
//                                          boolean matchValue, boolean movable) {
//        LinkedHashNode<K, V>[] tab;
//        LinkedHashNode<K, V> p;
//        int n, index;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (p = tab[index = (n - 1) & hash]) != null) {
//            LinkedHashNode<K, V> node = null, e;
//            K k;
//            V v;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                node = p;
//            else if ((e = p.next) != null) {
////                if (p instanceof TreeNode)
////                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
////                else {
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key ||
//                                    (key != null && key.equals(k)))) {
//                        node = e;
//                        break;
//                    }
//                    p = e;
//                } while ((e = e.next) != null);
////                }
//            }
//            if (node != null && (!matchValue || (v = node.value) == value ||
//                    (value != null && value.equals(v)))) {
////                if (node instanceof TreeNode)
////                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
////                else
//                if (node == p)
//                    tab[index] = node.next;
//                else
//                    p.next = node.next;
//                --size;
////                afterNodeRemoval(node);
//                return node;
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public void clear() {
//        LinkedHashNode<K, V>[] tab = table;
//        if (tab != null && size > 0) {
//            size = 0;
//            for (int i = 0; i < tab.length; ++i)
//                tab[i] = null;
//        }
//    }
//
//    @Override
//    public Set<K> keySet() {
//        return null;
//    }
//
//    @Override
//    public Collection<V> values() {
//        return null;
//    }
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }
//
//    @Override
//    public void putAll(Map<? extends K, ? extends V> m) {
//
//    }
//
//    class LinkedHashNode<K, V> implements Entry<K, V> {
//        final int hash;
//        final K key;
//        V value;
//        LinkedHashNode<K, V> next;
//
//        LinkedHashNode(int hash, K key, V value, LinkedHashNode<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey() {
//            return key;
//        }
//
//        public final V getValue() {
//            return value;
//        }
//
//        public final String toString() {
//            return key + "=" + value;
//        }
//
////        public final int hashCode() {
////            return Objects.hashCode(key) ^ Objects.hashCode(value);
////        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//}
//
