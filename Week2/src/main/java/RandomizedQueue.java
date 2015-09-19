import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{

    private Item[] a;         // array of items
    private int start;          // first item in array
    private int end;            // last item in array + 1

F    /**
     * Initializes an empty stack.
     */
    public RandomizedQueue()
    {
        a = (Item[]) new Object[ 2 ];
        start = 0;
        end = 0;
    }

    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty()
    {
        return start == end;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
        return end - start;
    }

    // resize the underlying array holding the elements
    @SuppressWarnings( "unchecked" )
    private void resize( int capacity )
    {
        assert capacity >= end - start;
        Item[] temp = (Item[]) new Object[ capacity ];
        int j = 0;
        for ( int i = start; i < end; i++, j++ )
        {
            temp[ j ] = a[ i ];
        }
        a = temp;
        end -= start;
        start = 0;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void enqueue( Item item )
    {
        if ( item == null )
        {
            throw new java.lang.NullPointerException();
        }

        if ( end == a.length )
        {
            resize( 2 * a.length );    // double size of array if necessary
        }
        a[ end++ ] = item;                            // add item
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item dequeue()
    {
        if ( isEmpty() )
        {
            throw new NoSuchElementException( "Stack underflow" );
        }
        Item item = a[ start ];
        a[ start ] = null;                              // to avoid loitering
        start++;
        // shrink size of array if necessary
//        if ( N > 0 && N == a.length / 4 )
        if ( a.length > 2 && end - start == a.length / 4 )
        {
            resize( a.length / 2 );
        }
        return item;
    }

    /**
     * return (but do not remove) a random item
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item sample()
    {
        if ( isEmpty() )
        {
            throw new NoSuchElementException( "Stack underflow" );
        }
        return a[ StdRandom.uniform( start, end ) ];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item>
    {
        private int i ;
        private int[] index;

        public RandomArrayIterator()
        {
            index = new int[ end - start ];
            int j = 0;
            for ( int k = start; k < end; k++ )
            {
                index[ j++ ] = k;
            }

            StdRandom.shuffle( index );
            i = end - start - 1;
        }

        public boolean hasNext()
        {
            return i >= 0;
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
            return a[ index[ i-- ] ];
        }
    }

    /**
     * Unit tests the <tt>Stack</tt> data type.
     */
    public static void main( String[] args )
    {

    }

}