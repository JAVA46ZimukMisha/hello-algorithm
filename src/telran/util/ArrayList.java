package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	private class ArrayListIterator implements Iterator<T> {
		int index = 0;

		@Override
		public boolean hasNext() {

			return array[index] instanceof T;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[index++];
		}

	}

	@Override
	public boolean add(T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (pattern.equals(array[i])) {
				index = i;
				System.arraycopy(array, index + 1, array, index, array.length - index - 1);
				size--;
				i--;
			}
		}
		return index >= 0;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				index = i;
				System.arraycopy(array, index + 1, array, index, array.length - index - 1);
				size--;
				i--;
			}
		}
		return index >= 0;
	}

	@Override
	public boolean contains(Object pattern) {
		for (T num : this) {
			if (num.equals(pattern)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new ArrayListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		if (index >= 0 && index <= size) {
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = obj;
			size++;
			return true;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (index >= 0 && index < size) {
			res = array[index];
			System.arraycopy(array, index + 1, array, index, size - index - 1);
			size--;
		}
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			if (pattern.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for (int i = size - 1; i >= 0; i--) {
			if (pattern.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Integer get(int index) {
		return index >= 0 && index < size ? (Integer) array[index] : null;
	}

}