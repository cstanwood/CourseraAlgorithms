import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{

    private int N;          // size of the stack
    //	private Node startSentinel;     // first item in dequeue
    //	private Node endSentinel;     // nextToLast item in dequeue
    private Node first;
    private Node last;

    // helper linked list class
    private class Node
    {
        private Item item;
        private Node previous = null;
        private Node next = null;

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
        Node oldfirst = first;
        first = new Node( item );
        first.next = oldfirst;
        if ( oldfirst != null )
        {
            oldfirst.previous = first;
        }
        N++;

    }

    // add the item to the end
    public void addLast( Item item )
    {
        Node oldLast = last;
        last = new Node( item );
        last.previous = oldLast;
        if ( oldLast != null )
        {
            oldLast.next = last;
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
        return null;
    }

    // unit testing
    public static void main( String[] args )
    {

    }
}