package series.serie3.SchoolBusRouting;


public class GenericStackList<Item> implements GenericStack<Item> {
	
	private class Node {
		private Item item;
		private Node next;
		private Node(Item item) { 
			this.item = item;
			next = null;
			
		}
	}
	
	private Node head;
		
	public void push(Item item) {
		Node n = new Node(item);
		n.next = head;
		head = n;
	}
	// Assume-se que antes de invocar o m�todo pop � testado se a pilha est� vazia
	public Item pop() {
		Item item = head.item;
		head = head.next;
		return item;
	}
	public boolean isEmpty() { 
		return head == null; 
	}
}