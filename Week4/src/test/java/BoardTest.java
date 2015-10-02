import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cstanwood on 30/09/15.
 */
public class BoardTest
{
    Board simple3x3Board1;
    Board twin3x3Board;
    Board simple3x3Board2;
    Board simple2x2Board;
    Board reverse3x3Board;
    Board hole3x3Board;
    Board neighbour1Board;
    Board neighbour2Board;
    Board neighbour3Board;
    Board neighbour4Board;

    @Before
    public void setUp() throws Exception
    {
        int[][] blocks3x3 = new int[ 3 ][ 3 ];
        int[][] twin3x3 = new int[ 3 ][ 3 ];
        int[][] reverseBlocks = new int[ 3 ][ 3 ];
        int[][] blocks2x2 = new int[ 2 ][ 2 ];
        int[] ints = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        int k = 0;
        int l = 8;
        for ( int i = 0; i < blocks3x3.length; i++ )
        {
            for ( int j = 0; j < blocks3x3.length; j++ )
            {
                blocks3x3[ i ][ j ] = ints[ k ];
                twin3x3[ i ][ j ] = ints[ k++ ];
                reverseBlocks[ i ][ j ] = ints[ l-- ];
            }
        }
        twin3x3[ 0 ][ 0 ] = 2;
        twin3x3[ 0 ][ 1 ] = 1;
        k = 0;
        for ( int i = 0; i < blocks2x2.length; i++ )
        {
            for ( int j = 0; j < blocks2x2.length; j++ )
            {
                blocks2x2[ i ][ j ] = ints[ k++ ];
            }
        }
        blocks2x2[ blocks2x2.length - 1 ][ blocks2x2.length - 1 ] = 0;

        simple3x3Board1 = new Board( blocks3x3 );
        simple3x3Board2 = new Board( blocks3x3 );
        reverse3x3Board = new Board( reverseBlocks );
        simple2x2Board = new Board( blocks2x2 );
        twin3x3Board = new Board( twin3x3 );
        int[][] hole3x3 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
        hole3x3Board = new Board( hole3x3 );
        int[][] neighbour1 = { { 8, 1, 3 }, { 0, 4, 2 }, { 7, 6, 5 } };
        int[][] neighbour2 = { { 8, 1, 3 }, { 4, 2, 0 }, { 7, 6, 5 } };
        int[][] neighbour3 = { { 8, 0, 3 }, { 4, 1, 2 }, { 7, 6, 5 } };
        int[][] neighbour4 = { { 8, 1, 3 }, { 4, 6, 2 }, { 7, 0, 5 } };
        neighbour1Board = new Board( neighbour1 );
        neighbour2Board = new Board( neighbour2 );
        neighbour3Board = new Board( neighbour3 );
        neighbour4Board = new Board( neighbour4 );
    }

    @org.junit.Test
    public void testDimension() throws Exception
    {
        assertEquals( 3, simple3x3Board1.dimension() );
    }

    @org.junit.Test
    public void testHamming() throws Exception
    {
        assertTrue( simple3x3Board1.hamming() == 0 );
        assertTrue( reverse3x3Board.hamming() == 7 );
        assertTrue( hole3x3Board.hamming() == 5 );

    }

    @org.junit.Test
    public void testManhattan() throws Exception
    {
        assertTrue( hole3x3Board.manhattan() == 10 );
        assertTrue( simple3x3Board1.manhattan() == 0 );
        assertTrue( reverse3x3Board.manhattan() == 20 );
    }

    @org.junit.Test
    public void testIsGoal() throws Exception
    {
        assertTrue( simple3x3Board1.isGoal() );
        assertFalse( reverse3x3Board.isGoal() );
    }

    @org.junit.Test
    public void testTwin() throws Exception
    {
        assertFalse( simple3x3Board1.twin().equals( simple3x3Board2 ) );
        assertTrue( simple3x3Board1.twin().equals( twin3x3Board ) );
    }

    @org.junit.Test
    public void testEquals() throws Exception
    {
        assertFalse( simple3x3Board1.equals( null ) );
        assertFalse( simple3x3Board1.equals( new Integer( 1 ) ) );
        assertFalse( simple3x3Board1.equals( simple2x2Board ) );
        assertTrue( simple3x3Board1.equals( simple3x3Board1 ) );
        assertTrue( simple3x3Board1.equals( simple3x3Board2 ) );
    }

    @org.junit.Test
    public void testNeighbors() throws Exception
    {
        Iterable<Board> neighbours = hole3x3Board.neighbors();
        List<Board> expected = new ArrayList<>();
        expected.add( neighbour1Board );
        expected.add( neighbour2Board );
        expected.add( neighbour3Board );
        expected.add( neighbour4Board );

        for ( Board neighbour : neighbours )
        {
            assertTrue( expected.contains( neighbour ) );
        }

    }

    @org.junit.Test
    public void testToString() throws Exception
    {
        StdOut.print( simple3x3Board1.toString() );
        assertEquals( "3\n  1  2  3\n  4  5  6\n  7  8  0\n", simple3x3Board1.toString() );

    }

    @org.junit.Test
    public void testMain() throws Exception
    {

    }
}