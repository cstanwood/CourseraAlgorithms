import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{

	private int  N;          // size of the stack
	//	private Node startSentinel;     // first item in dequeue
	//	private Node endSentinel;     // nextToLast item in dequeue
	private Node first;
	private Node last;

	// helper linked list class
	private class Node
	{
		private Item item;
		private Node previous;
		private Node next;

		Node()
		{
		}

		Node( Item item )
		{
			this.item = item;
		}
	}

	// construct an empty deque
	public Deque()
	{
		first = null;
		last = null;
		N = 0;
	}

	// is the deque empty?
	public boolean isEmpty()
	{
		return size() == 0;
	}

	// return the number of items on the deque
	public int size()
	{
		return N;
	}

	// add the item to the front
	public void addFirst( Item item )
	{
		Node newNode = new Node( item );
		newNode.next = startSentinel.next;
		startSentinel.next = newNode;
		N++;

		// keep end sentinel pointing to the next to last node
		if ( N == 2 )
		{
			endSentinel.next = startSentinel.next;
		}
/*
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
*/
	}

	// add the item to the end
	public void addLast( Item item )
	{
		//		nextToLast.next = new Node();
		//		nextToLast = nextToLast.next;
		//		nextToLast.item = item;
		//		N++;
	}

	// remove and return the item from the front
	public Item removeFirst()
	{
		if ( isEmpty() )
		{
			throw new NoSuchElementException( "Stack underflow" );
		}
		Item item = startSentinel.item;

		Item item = first.item;        // save item to return
		first = first.next;            // delete first node
		N--;
		return item;                   // return the saved item
	}

	// remove and return the item from the end
	public Item removeLast()
	{
		if ( isEmpty() )
		{
			throw new NoSuchElementException( "Stack underflow" );
		}
		Item item = nextToLast.item;        // save item to return
		first = first.next;            // delete first node
		N--;
		return item;                   // return the saved item
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()
	{

		return null;
	}

	// unit testing
	public static void main( String[] args )
	{

	}
}