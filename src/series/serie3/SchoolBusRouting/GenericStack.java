package series.serie3.SchoolBusRouting;


public interface GenericStack<Item> {
	public void push(Item i);
	public Item pop();
	public boolean isEmpty();
}