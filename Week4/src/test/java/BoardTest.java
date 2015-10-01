import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

	@Before
	public void setUp() throws Exception
	{
		int[][] blocks3x3 = new int[ 3 ][ 3 ];
		int[][] twin3x3 = new int[ 3 ][ 3 ];
		int[][] reverseBlocks = new int[ 3 ][ 3 ];
		int[][] blocks2x2 = new int[ 2 ][ 2 ];
		int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 0};
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
		int[][] hole3x3 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
		hole3x3Board = new Board( hole3x3 );
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