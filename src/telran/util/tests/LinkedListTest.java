package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.LinkedList;
import telran.util.List;

public class LinkedListTest extends ListTests {
	LinkedList<Integer> linkedList;
	@Override
	protected Collection<Integer> createCollection() {
		
		return new LinkedList<>();
	}
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp(); // content of the collection is {10, -5, 13, 20, 40, 15} from the setup 
		linkedList = (LinkedList<Integer>)collection; //
	}
	
	@Test
	void reverseTest() {
		Integer[] expected = {15, 40, 20, 13, -5, 10};
		linkedList.reverse();
		assertArrayEquals( expected, linkedList.toArray(new Integer[0]));
		assertTrue(expected==linkedList.toArray(expected));
	}
}