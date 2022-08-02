package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {

	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		Node(T obj) {
			this.obj = obj;
		}
	}
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private class LinkedListIterator implements Iterator<T>{
		Node<T> currentNode = head;
		Node<T> beforeNode;
		@Override
		public boolean hasNext() {
			
			return currentNode != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			beforeNode = currentNode;
			currentNode = currentNode.next;
			return beforeNode.obj;
		}
		
	}
	@Override
	public boolean add(T obj) {
		Node newNode = new Node<>(obj);
		if(head==null) {
			head = tail = newNode;
		}else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		Node<T> current = head;
		for(T num: this) {
			if(pattern.equals(num)) {
				removeNode(current);
				size--;
				res = true;
			}
		current = current.next;
		}
		return res;
	}

	private void removeNode(Node<T> removeNode) {
		if(removeNode.equals(head)) {
			removeHead();
		}else if(removeNode.equals(tail)) {
			removeTail();
		}else {
			removeCurrentNode(removeNode);
		}	
	}

	private void removeCurrentNode(Node<T> removeNode) {
		Node<T> afterNode = removeNode.next;
		Node<T> beforeNode = removeNode.prev;
		afterNode.prev = beforeNode;
		beforeNode.next = afterNode;
		removeNode.obj = null;
	}

	private void removeTail() {
		tail=tail.prev;
		tail.next = null;
		
	}

	private void removeHead() {
		head = head.next;
		if(head!=null) {
		 head.prev = null;
		}
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		Node<T> current = head;
		for(T num: this) {
			if(predicate.test(num)) {
				removeNode(current);
				size--;
				res = true;
			}
			current = current.next;
		}
		return res;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
		boolean res = false;
		if(index>=0&&index<=size) {
			res = true;
			if(index==size) {
				add(obj);
			}else if(index==0){
				addHead(obj);
			}else {
				addIndex(index, obj);
			}
		}
		return res;
	}
private void addIndex(int index, T obj) {
		Node<T> newNode = new Node<>(obj);
		Node<T> afterNode = getNodeIndex(index);
		Node<T> beforeNode = afterNode.prev;
		newNode.next = afterNode;
		afterNode.prev = newNode;
		beforeNode.next = newNode;
		newNode.prev = beforeNode;
		size++;
		
	}

private Node<T> getNodeIndex(int index) {
	return index>size/2 ? getNodeRightToLeft(index):getNodeLeftToRight(index);
}

private Node<T> getNodeLeftToRight(int index) {
	Node<T> current = head;
	for(int i = 0; i<index; i++) {
		current = current.next;
	}
	return current;
}

private Node<T> getNodeRightToLeft(int index) {
	Node<T> current = tail;
	for(int i = size-1; i>index; i--) {
		current = current.prev;
	}
	return current;
}

private void addHead(T obj) {
		size++;
		Node<T> newNode = new Node<>(obj);
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
	}

private boolean checkExistingIndex(int index) {
	return index>=0&&index<size;
}

	@Override
	public T remove(int index) {
		if(checkExistingIndex(index)) {
			return index > size / 2 ? removeNodeRightToLeft(index):removeNodeLeftToRight(index);
		}
		return null;
	}

	private T removeNodeLeftToRight(int index) {
		Node<T> current = head;
		for(int i = 0; i<index; i++) {
			current = current.next;
		}
		T res = current.obj;
		removeNode(current);
		size--;
		return res;
	}

	private T removeNodeRightToLeft(int index) {
		Node<T> current = tail;
		for(int i = size-1; i>index; i--) {
			current = current.prev;
		}
		T res = current.obj;
		removeNode(current);
		size--;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		Node<T> current = head;
		for(int i = 0; i<size; i++) {
			if(current.obj.equals(pattern)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		Node<T> current = tail;
		for(int i = size-1; i>=0; i--) {
			if(current.obj.equals(pattern)) {
				return i;
			}
			current = current.prev;
		}
		return -1;
	}

	@Override
	public T get(int index) {
		T res = null;
		if(checkExistingIndex(index)) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
		}
		return res;
	}

}
