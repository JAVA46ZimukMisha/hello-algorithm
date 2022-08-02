package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.List;

abstract class ListTests extends CollectionTests {

	@Test
	void addIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		assertTrue(list.add(0, 9));
		assertEquals(list.get(0), 9);
		assertTrue(list.add(6, 10));
		assertEquals(list.get(6), 10);
		assertTrue(list.add(5, 7));
		assertEquals(list.get(5), 7);
		for(int i = 0; i<NUMBERS; i++) {
			assertTrue(list.add(2, (Integer)(int)(Math.random() * Integer.MAX_VALUE)));
		}
		assertTrue(list.size() == DEFAULT_SIZE+103);
		assertTrue(list.add(list.size(), 12));
		assertEquals(list.get(list.size()-1), 12);
		int newSize = list.size();
		assertFalse(list.add(-1, 10));
		assertTrue(list.size()==newSize);
	}
	@Test
	void removeIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		assertEquals(list.remove(3), 4);
		assertTrue(list.size()==DEFAULT_SIZE-1);
		assertEquals(list.get(3), 5);
		assertEquals(list.remove(0), 1);
		assertTrue(list.size()==DEFAULT_SIZE-2);
		assertEquals(list.get(0), 2);
		assertEquals(list.remove(list.size()-1), 5);
		assertTrue(list.size()==DEFAULT_SIZE-3);
		assertEquals(list.get(list.size()-1), 3);
		assertEquals(list.remove(1),3);
		assertNull(list.remove(-1));
	}
	@Test
	void indexOfTest() {
		List<Integer> list = (List<Integer>) collection;
		list.add(2);
		assertTrue(list.indexOf(2)==1);
		assertTrue(list.indexOf(155)==-1);
	}
	@Test
	void lastIndexOfTest() {
		List<Integer> list = (List<Integer>) collection;
		list.add(2);
		assertTrue(list.lastIndexOf(2)==list.size()-1);
		assertTrue(list.lastIndexOf(155)==-1);
	}
	@Test
	void getTest() {
		List<Integer> list = (List<Integer>) collection;
		assertEquals(list.get(0), 1);
		assertNull(list.get(-1));
	}
	@Test
	void test() {
		Integer ar[] = new Integer[3];
		System.out.println(Arrays.toString(ar));
	}

}