package telran.util;

public class ListValidator {
	public static boolean isCircular(Node head) {
		ArrayList<Node> helper = new ArrayList<>();
		Node current = head;
		do {
			helper.add(current);
			current = current.next;
			for (Node cur : helper) {
				if (cur == current) {
					return true;
				}
			}
		} while (current != null);
		return false;
	}

	public static int indexOfCircular(Node head) {
		if(head == null) {
			return -1;
		}
		ArrayList<Node> helper = new ArrayList<>();
		Node current = head;
		do {
			helper.add(current);
			current = current.next;
			int ind = 0;
			for (Node cur : helper) {
				if (cur == current) {
					return ind;
				}
				ind++;
			}
		} while (current != null);
		return -1;
	}

	public static boolean isCircularON(Node head) {
		if (head == null) {
			return false;
		}
		Node current = head;
		Node currentFast = head;
		while (currentFast.next != null && currentFast.next.next != null) {
			current = current.next;
			currentFast = currentFast.next.next;
			if (currentFast == current) {
				return true;
			}
		}
		return false;
	}

	public static int indexOfCircularON(Node head) {
		if(head == null) {
			return -1;
		}
		Node current = head;
		Node currentFast = head;
		boolean isLoop = false;
		int ind = 0;
		while (currentFast.next != null && currentFast.next.next != null) {
			current = current.next;
			currentFast = currentFast.next.next;
			if (currentFast == current) {
				isLoop = true;
				break;
			}
		}
		if (isLoop) {
			current = head;
			while (current != currentFast) {
				current = current.next;
				currentFast = currentFast.next;
				ind++;
			}
		}
		return isLoop ? ind : -1;
	}
}
