import static junit.framework.TestCase.assertEquals;

/**
 * Created by cstanwood on 17/09/15.
 */
public class DequeTest
{


    @org.junit.Before
    public void setUp() throws Exception
    {

    }

    @org.junit.After
    public void tearDown() throws Exception
    {

    }

    @org.junit.Test
    public void testIsEmpty() throws Exception
    {
        Deque<String> deque = new Deque<>();
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

    }

    @org.junit.Test
    public void testAddFirst() throws Exception
    {
        String result;

        Deque<String> deque = new Deque();
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
        Deque<String> deque = new Deque();

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
        Deque<String> deque = new Deque<>();

        String result;

        deque.addFirst( "a" );
        deque.addLast( "b" );
        result = deque.removeFirst();
        assertEquals( result, "a" );
        result = deque.removeLast();
        assertEquals( result, "b" );
    }


    @org.junit.Test

    public void testIterator() throws Exception
    {

    }

    @org.junit.Test
    public void testMain() throws Exception
    {

    }
}