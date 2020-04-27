import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> KeyAndValues = new ArrayList<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return KeyAndValues.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return KeyAndValues.isEmpty();
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : KeyAndValues)
            if (p.getKey().equals(key)) return true;
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (Pair p : KeyAndValues)
            if (p.getValue().equals(value)) return true;
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : KeyAndValues)
            if (p.getKey().equals(key)) return p.getValue();
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        V result = value;
        if (!containsKey(key)) {
            KeyAndValues.add(new Pair(key, value));
            return null;
        }
        for (Pair p : KeyAndValues)
            if (p.getKey().equals(key)) {
                result = p.getValue();
                p.setValue(value);
                break;
            }
        return result;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        V value = null;
        for (int i = 0; i < KeyAndValues.size(); i++) {
            final Pair p = KeyAndValues.get(i);
            if (p.getKey().equals(key)) {
                value = p.getValue();
                KeyAndValues.remove(i);
                return value;
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        KeyAndValues.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : KeyAndValues) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        final List<V> val = new ArrayList<>();
        for (Pair p : KeyAndValues) val.add(p.getValue());
        return val;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>)(Set)new HashSet<>(KeyAndValues);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
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
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}