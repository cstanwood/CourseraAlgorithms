import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{

	private int  N;          // size of the stack
	private Node first;
	private Node last;

	// helper linked list class
	private class Node
	{
		private Item item;
		private Node previous = null;
		private Node next     = null;

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
		if ( item == null )
		{
			throw new NullPointerException();
		}

		Node newNode = new Node( item );
		if ( isEmpty() )
		{
			first = newNode;
			last = newNode;
		}
		else
		{
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}

		N++;

	}

	// add the item to the end
	public void addLast( Item item )
	{
		if ( item == null )
		{
			throw new NullPointerException();
		}

		Node newNode = new Node( item );
		if ( isEmpty() )
		{
			first = newNode;
			last = newNode;
		}
		else
		{
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
		}

		N++;
	}

	// remove and return the item from the front
	public Item removeFirst()
	{
		if ( isEmpty() )
		{
			throw new NoSuchElementException( "Stack underflow" );
		}

		Item item = first.item;        // save item to return
		if ( first.next != null )
		{
			Node second = first.next;
			first.next = null;
			second.previous = null;
			first = second;
		}
		else
		{
			first = null;
			last = null;
		}

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
		Item item = last.item;        // save item to return
		if ( last.previous != null )
		{
			last = last.previous;
			last.next.previous = null;
			last.next = null;
		}
		else
		{
			last = null;
			first = null;
		}
		N--;
		return item;                   // return the saved item
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;

		public boolean hasNext()
		{
			return current != null;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public Item next()
		{
			if ( !hasNext() )
			{
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// unit testing
	public static void main( String[] args )
	{

	}
}