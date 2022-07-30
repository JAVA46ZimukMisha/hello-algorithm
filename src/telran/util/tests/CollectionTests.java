package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.NegativeNumbersPredicate;

abstract class  CollectionTests {
protected static final int NUMBERS = 100;
protected static final int DEFAULT_SIZE = 5;
protected Collection<Integer> collection;
protected abstract Collection<Integer > createCollection();
Integer[] IntegerObjects = {
		1, 2, 3, 4, 5
};
	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		for(Integer num: IntegerObjects) {
			collection.add(num);
		}
	}
	@Test
	void addTest() {
		assertTrue(collection.add(6));
		assertTrue(collection.add(6));
		for(int i = 0; i<NUMBERS; i++) {
			collection.add((Integer)(int)(Math.random() * Integer.MAX_VALUE));
		}
		assertEquals(collection.size(), DEFAULT_SIZE + 102);
	}
	@Test
	void removeTest() {
		assertTrue(collection.remove(5));
		assertEquals(collection.size(), DEFAULT_SIZE - 1);
		assertFalse(collection.remove(7));
		assertEquals(collection.size(), DEFAULT_SIZE - 1);
	}
	@Test
	void removeIfTest() {
		Predicate<Integer> predicate = new NegativeNumbersPredicate();
		assertFalse(collection.removeIf(predicate));
		assertEquals(collection.size(), DEFAULT_SIZE);
		assertTrue(collection.removeIf(predicate.negate()));
		assertEquals(collection.size(), 0);
	}
	@Test
	void containsTest() {
		assertTrue(collection.contains(4));
		assertFalse(collection.contains(8));
	}
	@Test
	void sizeTest() {
		assertEquals(collection.size(), DEFAULT_SIZE);
	}
	@Test
	void toArrayTest() {
		Integer[] expected1 = {1,2,3,4,5};
		Integer[] expected2 = new Integer[100];
		assertArrayEquals(expected1, collection.toArray(new Integer[0]));
		assertTrue(expected1 == collection.toArray(expected1));
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected1, Arrays.copyOf(expected2, collection.size()));
	}
}