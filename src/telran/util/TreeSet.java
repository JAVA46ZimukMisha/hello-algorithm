package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.naming.OperationNotSupportedException;

public class TreeSet<T> implements SortedSet<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> root;
	int size;
	Comparator<T> comp;
	private Node<T> getLeastNodeFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	private boolean isJunction(Node<T> node) {
		boolean res = false;
		if(node.left != null && node.right != null) {
			res = true;
		}
		return res;
	}
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root == null ? null : getLeastNodeFrom(root);
		boolean flNext = false;
		Node<T> prevNode;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prevNode = current;
			updateCurrent();
			flNext = true;
			return res;
		}

		private void updateCurrent() {
			current = current.right != null ? getLeastNodeFrom(current.right) : getGreaterParent(current);
			
		}

		private Node<T> getGreaterParent(Node<T> node) {
			
			while(node.parent != null && node.parent.left != node) {
				node = node.parent;
			}
			return node.parent;
		}

		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
				
				if(isJunction(prevNode)) {
					TreeSet.this.remove(prevNode.obj);
					current = prevNode;
				}else {
					TreeSet.this.remove(prevNode.obj);
				}
				
			flNext = false;
		}
		
	}
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public boolean add(T obj) {
		Node<T> parent = getNodeOrParent(obj);
		boolean res = false;
		int compRes = 0;
		if (parent == null || (compRes = comp.compare(obj, parent.obj)) != 0) {
			//obj doesn't exist
			Node<T> newNode = new Node<>(obj);
			if (parent == null) {
				//added first element that is the root
				root = newNode;
			} else if(compRes > 0) {
				parent.right = newNode;
			} else {
				parent.left = newNode;
			}
			res = true;
			newNode.parent = parent;
			size++;
		}
		return res;
	}

	private Node<T> getNodeOrParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while (current != null) {
			parent = current;
			compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				break;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}
	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		if(!contains(pattern)) {
			return res;
		}
		Node<T> removeNode = getNodeOrParent((T) pattern);
		if(!isJunction(removeNode)) {
			removeNonJunctionNode(removeNode);
			res = true;
		}else{
			removeJunctionNode(removeNode);
			res = true;
		}
		size--;
		return res;
	}

	private void removeJunctionNode(Node<T> removeNode) {
		Node<T> changeRemoveNode = getLeastNodeFrom(removeNode.right);
		removeNode.obj = changeRemoveNode.obj;
		removeNonJunctionNode(changeRemoveNode);
	}
	private void removeNonJunctionNode(Node<T> removeNode) {
		Node<T> parent = removeNode.parent;
		if(parent == null) {
			removeRoot();
		}else if(parent.right == removeNode) {
			parent.right = removeNode(removeNode);
		}else {
			parent.left = removeNode(removeNode);
		}
	}
	private void removeRoot() {
		Node<T> forRemove = getLeastNodeFrom(root);
		root.obj = forRemove.obj;
		removeNode(forRemove);
		
		
	}
	private Node<T> removeNode(Node<T> removeNode) {
		Node<T> res;
		if(removeNode.right != null || removeNode.left != null) {
			res = removeNode.right == null ? removeNode.left : removeNode.right;
		}else {
			res = null;
		}
		return res;
	}
	@Override
	public boolean contains(Object pattern) {
		Node<T> parent = getNodeOrParent((T) pattern);
		boolean res = false;
		int compRes = 0;
		if (parent != null && (compRes = comp.compare((T) pattern, parent.obj)) == 0) {
			res = true;
		}
		return res;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		if (root == null) {
			return null;
		}
		return getLeastNodeFrom(root).obj;
	}

	
	@Override
	public T last() {
		if (root == null) {
			return null;
		}
		return getMostNodeFrom(root).obj;
	}
	private Node<T> getMostNodeFrom(Node<T> node) {
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}

}