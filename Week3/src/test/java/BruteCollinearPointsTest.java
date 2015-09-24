import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by cstanwood on 24/09/15.
 */
public class BruteCollinearPointsTest
{

    @Test
    public void testNumberOfSegments() throws Exception
    {

    }

    @Test
    public void testSegments() throws Exception
    {
        Point p1 = new Point( 0, 0 );
        Point p2 = new Point( 1, 1 );
        Point p3 = new Point( 2, 2 );
        Point p4 = new Point( 3, 3 );

        Point[] points = { p1, p2, p3, p4 };
        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment mySegment = new LineSegment( p1, p4 );
        LineSegment[] bruteSegments = brute.segments();

        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );

    }

    @Test
    public void testNoPoints() throws Exception
    {

    }

    @Test
    public void testOnePoint() throws Exception
    {

    }

    @Test
    public void testTwoPoints() throws Exception
    {

    }

    @Test
    public void testThreePoints() throws Exception
    {

    }

    @Test
    public void testNoSegment() throws Exception
    {

    }

    @Test
    public void testHorizontalSegment() throws Exception
    {

    }


    @Test
    public void testVerticalSegment() throws Exception
    {

    }

    @Test
    public void testPostiveSlope() throws Exception
    {

    }

    @Test
    public void testNegativeSlope() throws Exception
    {

    }

    @Test
    public void testTwoSegments() throws Exception
    {

    }

}