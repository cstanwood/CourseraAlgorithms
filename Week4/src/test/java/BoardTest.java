import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Created by cstanwood on 30/09/15.
 */
public class BoardTest
{
    Board simple3x3Board;

    @Before
    public void setUp() throws Exception
    {
        int[][] blocks = new int[3][3];
        int[] ints = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        int k = 0;
        for ( int i = 0; i < blocks.length; i++ )
        {
            for ( int j = 0; j < blocks.length; j++ )
            {
                blocks[ i ][ j ] = ints[ k++ ];
            }
        }
        simple3x3Board = new Board( blocks );
    }

    @org.junit.Test
    public void testDimension() throws Exception
    {
        assertEquals( 3, simple3x3Board.dimension() );
    }

    @org.junit.Test
    public void testHamming() throws Exception
    {

    }

    @org.junit.Test
    public void testManhattan() throws Exception
    {

    }

    @org.junit.Test
    public void testIsGoal() throws Exception
    {

    }

    @org.junit.Test
    public void testTwin() throws Exception
    {

    }

    @org.junit.Test
    public void testEquals() throws Exception
    {

    }

    @org.junit.Test
    public void testNeighbors() throws Exception
    {

    }

    @org.junit.Test
    public void testToString() throws Exception
    {
        StdOut.print( simple3x3Board.toString() );
        assertEquals( "3\n  1  2  3\n  4  5  6\n  7  8  0\n", simple3x3Board.toString() );

    }

    @org.junit.Test
    public void testMain() throws Exception
    {

    }
}