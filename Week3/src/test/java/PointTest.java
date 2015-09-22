import static junit.framework.Assert.*;

/**
 * Created by cstanwood on 22/09/15.
 */
public class PointTest
{

	@org.junit.Test
	public void testCompareTo() throws Exception
	{
		Point p1 = new Point( 1, 2 );
		Point p2 = new Point( 2, 2 );
		assertTrue( p1.compareTo( p2 ) < 0 );
		assertTrue( p2.compareTo( p1 ) > 0 );

		Point p3 = new Point( 1, 2 );
		Point p4 = new Point( 1, 3 );
		assertTrue( p1.compareTo( p2 ) < 0 );
		assertTrue( p2.compareTo( p1 ) > 0 );

		assertEquals( p1.compareTo( p3 ), 0 );
	}

	@org.junit.Test
	public void testSlopeOrder() throws Exception
	{
		Point p1 = new Point( 1, 1 );
		Point p2 = new Point( 1, 1 );
		assertEquals( p1.compareTo( p2 ), 0 );

		Point p3 = new Point( 0, 0 );
		Point p4 = new Point( 0, 0 );
		assertEquals( p3.compareTo( p4 ), 0 );


	}
}