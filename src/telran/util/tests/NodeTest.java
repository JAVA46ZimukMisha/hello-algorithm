package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.ListValidator;
import telran.util.Node;

class NodeTest {
	Node n0 = new Node(0);
	Node n1 = new Node(1);
	Node n2 = new Node(2);
	Node n3 = new Node(3);
	Node n4 = new Node(4);
	Node n5 = new Node(5);
	Node n6 = new Node(6);
	Node n7 = new Node(7);
	Node n8 = new Node(8);
	Node n9 = new Node(9);
	
	
	@BeforeEach
	void setUp() throws Exception {
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n3;
	}

	@Test
	void isCircularTest() {
		assertTrue(ListValidator.isCircular(n0));
	}
	@Test
	void indexOfCircularTest() {
		assertTrue(ListValidator.indexOfCircular(n0) == 3);
	}
	@Test
	void isCircularONTest() {
		assertTrue(ListValidator.isCircularON(n0));
	}
	@Test
	void indexOfCircularONTest() {
		assertTrue(ListValidator.indexOfCircularON(n0) == 3);
	}

	

}
