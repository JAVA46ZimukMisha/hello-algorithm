package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static telran.recursion.LineRecursion.*
;

import java.security.InvalidParameterException;class RecursionTest {
int count = 0;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
	}
	@Test
	void powTest() {
		assertEquals(1000, pow(10, 3));
		assertEquals(-1000, pow(-10, 3));
		assertEquals(100,pow(-10, 2));
		assertEquals(1,pow(-10, 0));
		try {
		pow(-10, -2);
		}catch(InvalidParameterException e) {
		}
	}
	@Test
	void sumTest() {
		int ar[] = {1, 2, 3, 4};
		assertEquals(10, sum(ar));
	}
	@Test
	void squareTest() {
		assertEquals(36, square(6));
		assertEquals(0, square(0));
		assertEquals(100, square(-10));
	}
	
	@Test
	void isSubStrTest() {
		assertTrue(isSubstring("babushka", "ushk"));
		assertFalse(isSubstring("babushka", "pap"));
	}
//	private void f() {
//		
//		if(Math.random() < 0.99) {
//			f();
//			count++;
//		}
//		
//		
//	}

}
