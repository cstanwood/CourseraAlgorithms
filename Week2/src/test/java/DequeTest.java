import junit.framework.Assert;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Christopher Stanwood
 * On 17/09/15.
 */
public class DequeTest
{

    @org.junit.Test
    public void testIsEmpty() throws Exception
    {
        Deque<String> deque = new Deque<String>();
        assertEquals( deque.isEmpty(), true );
        deque.addFirst( "Hi" );
        assertEquals( deque.isEmpty(), false );
        deque.removeFirst();
        assertEquals( deque.isEmpty(), true );
        deque.addFirst( "Hi" );
        deque.addFirst( "Boo" );
        assertEquals( deque.isEmpty(), false );
        deque.removeFirst();
        assertEquals( deque.isEmpty(), false );
        deque.removeFirst();
        assertEquals( deque.isEmpty(), true );

    }

    @org.junit.Test
    public void testSize() throws Exception
    {
		Deque<String> strings = new Deque<String>();

		Assert.assertEquals( strings.size(), 0 );
		strings.addLast( "a" );
		Assert.assertEquals( strings.size(), 1 );
		strings.addLast( "b" );
		Assert.assertEquals( strings.size(), 2 );
		strings.removeFirst();
		Assert.assertEquals( strings.size(), 1 );
		strings.addLast( "c" );
		Assert.assertEquals( strings.size(), 2 );
		strings.removeFirst();
		Assert.assertEquals( strings.size(), 1 );
		strings.removeLast();
		Assert.assertEquals( strings.size(), 0 );
		strings.addFirst( "d" );
		Assert.assertEquals( strings.size(), 1 );
		strings.removeFirst();
		Assert.assertEquals( strings.size(), 0 );

    }

    @org.junit.Test
    public void testAddFirst() throws Exception
    {
        String result;

        Deque<String> deque = new Deque<String>();
        deque.addFirst( "a" );
        deque.addFirst( "b" );
        deque.addFirst( "c" );
        result = deque.removeFirst();
        assertEquals( result, "c" );
        result = deque.removeFirst();
        assertEquals( result, "b" );
        result = deque.removeFirst();
        assertEquals( result, "a" );

        deque.addFirst( "a" );
        deque.addFirst( "b" );
        deque.addFirst( "c" );
        result = deque.removeFirst();
        assertEquals( result, "c" );
        result = deque.removeFirst();
        assertEquals( result, "b" );
        result = deque.removeFirst();
        assertEquals( result, "a" );

    }

    @org.junit.Test
    public void testAddLast() throws Exception
    {
        String result;
        Deque<String> deque = new Deque<String>();

        deque.addLast( "a" );
        deque.addLast( "b" );
        deque.addLast( "c" );
        result = deque.removeLast();
        assertEquals( result, "c" );
        result = deque.removeLast();
        assertEquals( result, "b" );
        result = deque.removeLast();
        assertEquals( result, "a" );

        deque.addLast( "a" );
        deque.addLast( "b" );
        deque.addLast( "c" );
        result = deque.removeLast();
        assertEquals( result, "c" );
        result = deque.removeLast();
        assertEquals( result, "b" );
        result = deque.removeLast();
        assertEquals( result, "a" );

    }

    @org.junit.Test
    public void testMultipleAddAndRemove() throws Exception
    {
        Deque<String> deque = new Deque<String>();

        String result;

        deque.addFirst( "a" );
        deque.addLast( "b" );
        result = deque.removeFirst();
        assertEquals( result, "a" );
        result = deque.removeLast();
        assertEquals( result, "b" );

        deque.addLast( "a" );
        deque.addFirst( "b" );
        result = deque.removeFirst();
        assertEquals( result, "b" );
        result = deque.removeLast();
        assertEquals( result, "a" );

        deque.addFirst( "a" );
        deque.addLast( "b" );
		result = deque.removeLast();
		assertEquals( result, "b" );
		result = deque.removeFirst();
		assertEquals( result, "a" );

		deque.addLast( "a" );
		deque.addFirst( "b" );
		result = deque.removeLast();
		assertEquals( result, "a" );
		result = deque.removeFirst();
		assertEquals( result, "b" );
    }


    @org.junit.Test

    public void testIterator() throws Exception
	{
		Deque<Integer> integers = new Deque<Integer>();

		for ( int i = 0; i < 10; i++ )
		{
			integers.addLast( new Integer( i ) );
		}

		int i = 0;
		for ( Integer j : integers )
		{
			assertEquals( new Integer( i++ ), j );
		}
	}

}