import java.util.*;
import java.util.HashSet;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair<K, V>> KeyAndValues = new ArrayList<>();

    @Override
    public int size() {
        return KeyAndValues.size();
    }

    @Override
    public boolean isEmpty() {
        return KeyAndValues.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Pair<K, V> p : KeyAndValues)
            if (p.getKey().equals(key)) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Pair<K, V> p : KeyAndValues)
            if (p.getValue().equals(value)) return true;
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) return null;

        for (Pair<K, V> p : KeyAndValues)
            if (p.getKey().equals(key)) return p.getValue();
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        // BEGIN (write your solution here)
        for(Pair<K, V> p : KeyAndValues)
            if(p.getKey().equals(key)) return p.getValue();
        return defaultValue;
        // END
    }

    @Override
    public V put(K key, V value) {
        V result = value;
        if (!containsKey(key)) {
            KeyAndValues.add(new Pair<>(key, value));
            return null;
        }
        for (Pair<K, V> p : KeyAndValues)
            if (p.getKey().equals(key)) {
                result = p.getValue();
                p.setValue(value);
                break;
            }
        return result;
    }

    @Override
    public V remove(Object key) {
        V value;
        for (int i = 0; i < KeyAndValues.size(); i++) {
            final Pair<K, V> p = KeyAndValues.get(i);
            if (p.getKey().equals(key)) {
                value = p.getValue();
                KeyAndValues.remove(i);
                return value;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        KeyAndValues.clear();
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<>();
        for (Pair<K, V> p : KeyAndValues) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        final List<V> val = new ArrayList<>();
        for (Pair<K, V> p : KeyAndValues) val.add(p.getValue());
        return val;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(KeyAndValues);
    }

    private static class Pair<K, V> implements Map.Entry<K, V> {

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