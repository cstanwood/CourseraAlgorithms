import edu.princeton.cs.algs4.StdOut;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by cstanwood on 19/09/15.
 */
public class RandomizedQueueTest
{

    @Test
    public void testIsEmpty() throws Exception
    {

    }

    @Test
    public void testSize() throws Exception
    {

    }

    @Test
    public void testEnqueue() throws Exception
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue();
        for ( Integer i = 0; i < 10; i++ )
        {
            queue.enqueue( i );
        }
        for ( Integer i = 0; i < 10; i++ )
        {
            Assert.assertEquals( queue.dequeue(), i );
        }

        for ( Integer i = 0; i < 10; i++ )
        {
            queue.enqueue( i );
        }
        for ( Integer i = 0; i < 10; i++ )
        {
            Assert.assertEquals( queue.dequeue(), i );
        }

        for ( Integer i = 0; i < 10; i++ )
        {
            queue.enqueue( i );
        }
        for ( Integer i = 0; i < 10; i++ )
        {
            Assert.assertEquals( queue.dequeue(), i );
        }
        for ( Integer i = 0; i < 1000; i++ )
        {
            queue.enqueue( i );
        }
        for ( Integer i = 0; i < 1000; i++ )
        {
            Assert.assertEquals( queue.dequeue(), i );
        }



    }

    @Test
    public void testDequeue() throws Exception
    {

    }

    @Test
    public void testSample() throws Exception
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for ( Integer i = 0; i <= 2; i++ )
        {
            queue.enqueue( i );
        }
        for ( int i = 0; i < 100; i++ )
        {
            StdOut.print( queue.sample() + " " );
        }
        StdOut.println();

        for ( Integer i = 0; i <= 10000; i++ )
        {
            queue.enqueue( i );
        }
        for ( int i = 0; i < 100; i++ )
        {
            StdOut.print( queue.sample() + " " );
        }
    }

    @Test
    public void testIterator() throws Exception
    {
        RandomizedQueue<Integer> integers = new RandomizedQueue<>();

        for ( Integer i = 0; i < 100; i++ )
        {
            integers.enqueue( i );
        }

        for ( int i = 0; i < 50; i++ )
        {
            integers.dequeue();
        }

        for ( Integer i : integers )
        {
            StdOut.print( i + " " );
        }
        StdOut.println();

    }
}