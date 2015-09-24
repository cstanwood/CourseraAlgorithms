import java.util.Arrays;
import java.util.Comparator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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

        Point[] points = { p4, p1, p2 };
        Arrays.sort( points );
        assertEquals( p1, points[ 0 ] );
        assertEquals( p2, points[ 1 ] );
        assertEquals( p4, points[ 2 ] );

    }

	@org.junit.Test
	public void testSlopeTo() throws Exception
	{
		Point p1 = new Point( 1, 1 );
		Point p2 = new Point( 1, 1 );
        assertEquals( Double.NEGATIVE_INFINITY, p1.slopeTo( p2 ) );

		Point p3 = new Point( 0, 0 );
		Point p4 = new Point( 0, 0 );
        assertEquals( Double.NEGATIVE_INFINITY, p3.slopeTo( p4 ) );

        Point p5 = new Point( 1, 2 );
        assertEquals( Double.POSITIVE_INFINITY, p1.slopeTo( p5 ) );

        Point p6 = new Point( 2, 1 );
        assertEquals( +0.0, p1.slopeTo( p6 ) );

        assertEquals( 1.0, p1.slopeTo( p3 ) );

        assertEquals( 1.0, p3.slopeTo( p1 ) );

        assertEquals( 2.0, p3.slopeTo( p5 ) );

        assertEquals( 0.5, p3.slopeTo( p6 ) );

	}

    @org.junit.Test
    public void testSlopeOrder() throws Exception
    {
/*        Point p1 = new Point( 0, 0 );

        Point p2 = new Point( 1, 1 );
        Point p3 = new Point( 2, 1 );
        Point p4 = new Point( 1, 2 );
        Point p5 = new Point( 1, 1 );

        Comparator<Point> slopeOrder = p1.slopeOrder();
        Point[] points = { p2, p3, p4, p5 };

        Arrays.sort( points, slopeOrder );

        assertEquals( p3, points[ 0 ] );
        assertEquals( p2, points[ 1 ] );
        assertEquals( p5, points[ 2 ] );
        assertEquals( p4, points[ 3 ] );*/

		Point q0 = new Point( -2, -4 );
		Point q1 = new Point( -2, 1 );
		Point q2 = new Point( 2, -4 );
		Point q3 = new Point( 3, -3 );
		Point q4 = new Point( -4, 2 );
		Point q5 = new Point( -2, -2 );
		Point q6 = new Point( -2, 5 );
		Point q7 = new Point( 1, 4 );
		Point[] morePoints = {q0, q1, q2, q3, q4, q5, q6, q7};
		Arrays.sort( morePoints );
		Comparator<Point> slopeOrder = q0.slopeOrder();
		Arrays.sort( morePoints, 1, 7, slopeOrder );

		assertEquals( q0, morePoints[ 0 ] );
		assertEquals( q4, morePoints[ 1 ] );
		assertEquals( q2, morePoints[ 2 ] );
		assertEquals( q3, morePoints[ 3 ] );
		assertEquals( q7, morePoints[ 4 ] );
		assertEquals( q5, morePoints[ 5 ] );
		assertEquals( q1, morePoints[ 6 ] );
		assertEquals( q6, morePoints[ 7 ] );





    }
}