import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {

    private T[] m = (T[])new Object[1];

    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public T[] toArray() {
        final T[] newM = (T[]) new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(final T1[] a) {
        if (a.length < size) return (T1[]) Arrays.copyOf(m, size, a.getClass());

        System.arraycopy(m, 0, a, 0, size);

        if (a.length > size) a[size] = null;

        return a;
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        size = 0;
    }

    @Override
    public T remove(final int index) {
        final T element = m[index];
        if (index != this.size() - 1)
            System.arraycopy(m, index + 1, m, index, this.size() - index - 1);
        size--;
        return element;
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final int index, final T element) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        if (size  == 0 || index == size) {
            add(element);

        } else if (m.length == size) {

            final T[] tempM = m;
            m = (T[]) new Object[this.size() * 2];

            System.arraycopy(tempM, 0, m, 0,  index );
            System.arraycopy(tempM, index, m, index + 1, size() - index);

            set(index, element);
            size++;

        } else {

            final T[] tempM = m;
            System.arraycopy(tempM, 0, m, 0, index + 1);
            System.arraycopy(tempM, index, m, index + 1, size() - index);
            set(index, element);
            size++;

        }
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        m[index] = element;
        return element;
    }

    @Override
    public T get(final int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return m[index];
    }

    private class ElementsIterator implements ListIterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = LAST_IS_NOT_SET;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            // BEGIN (write your solution here)  ElementsIterator
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            return ArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastIndex = index++; // or lastIndex = nextIndex(); index++;
            return ArrayList.this.m[lastIndex];
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)   nextIndex
            if(index == size()) return size();
            index++;
            return index;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)   hasPrevious
            if(index > 0) return true;
            return false;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)   previous
            if(!hasPrevious()) throw new NoSuchElementException();
            lastIndex = index--;
            return ArrayList.this.m[lastIndex];
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)  previousIndex
            if(index == 0) return -1;
            index--;
            return index;
            // END
        }

        @Override
        // . a ^ b . c . d . e . f .
        public void add(final T element) {
            // BEGIN (write your solution here)  add
            if(index == LAST_IS_NOT_SET) ArrayList.this.add(element);
            else {
                ArrayList.this.add(index, element);
                index++;
                lastIndex = LAST_IS_NOT_SET;
            }
            // END
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)  set
            if (lastIndex == LAST_IS_NOT_SET) throw new IllegalStateException();
            ArrayList.this.set(index, element);

            // END
        }

        @Override
        public void remove() {
            secretRemove();
        }


















        /////



















        private void secretRemove() {
            if (lastIndex == LAST_IS_NOT_SET) throw new IllegalStateException();
            ArrayList.this.remove(lastIndex);
            index--;
            lastIndex = LAST_IS_NOT_SET;
        }
    }
}
