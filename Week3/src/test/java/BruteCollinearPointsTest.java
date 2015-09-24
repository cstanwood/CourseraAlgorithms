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

        assertTrue( bruteSegments.length == 1 );

        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );

    }

    @Test
    public void testNoPoints() throws Exception
    {
        Point[] points = new Point[ 0 ];

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        assertTrue( bruteSegments.length == 0 );
    }

    @Test
    public void testOnePoint() throws Exception
    {
        Point p1 = new Point( 1, 1 );
        Point[] points = { p1 };

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        assertTrue( bruteSegments.length == 0 );

    }

    @Test
    public void testTwoPoints() throws Exception
    {
        Point p1 = new Point( 1, 1 );
        Point p2 = new Point( 2, 2 );
        Point[] points = { p1, p2 };

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        assertTrue( bruteSegments.length == 0 );
    }

    @Test
    public void testThreePoints() throws Exception
    {
        Point p1 = new Point( 1, 1 );
        Point p2 = new Point( 2, 2 );
        Point p3 = new Point( 3, 3 );
        Point[] points = { p1, p2, p3 };
        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        assertTrue( bruteSegments.length == 0 );
    }

    @Test
    public void testNoSegment() throws Exception
    {
        Point p1 = new Point( 1, 1 );
        Point p2 = new Point( -1, 1 );
        Point p3 = new Point( 1, -1 );
        Point p4 = new Point( -1, -1 );
        Point[] points = { p1, p2, p3, p4 };
        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        assertTrue( bruteSegments.length == 0 );

    }

    @Test
    public void testHorizontalSegment() throws Exception
    {
        Point p1 = new Point( -3, 2 );
        Point p2 = new Point( -1, 2 );
        Point p3 = new Point( -1, -1 );
        Point p4 = new Point( 2, 4 );
        Point p5 = new Point( 3, 2 );
        Point p6 = new Point( 4, -2 );
        Point p7 = new Point( 6, 2 );
        Point[] points = { p1, p2, p3, p4, p5, p6, p7 };

        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        LineSegment mySegment = new LineSegment( p1, p7 );
        assertTrue( bruteSegments.length == 1 );
        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );
    }


    @Test
    public void testVerticalSegment() throws Exception
    {
        Point p1 = new Point( -4, 2 );
        Point p2 = new Point( -2, 5 );
        Point p3 = new Point( -2, 1 );
        Point p4 = new Point( -2, -2 );
        Point p5 = new Point( -2, -4 );
        Point p6 = new Point( 1, 4 );
        Point p7 = new Point( 2, -4 );
        Point p8 = new Point( 3, -3 );
        Point[] points = { p1, p2, p3, p4, p5, p6, p7, p8 };

        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        LineSegment mySegment = new LineSegment( p5, p2 );
        assertTrue( bruteSegments.length == 1 );
        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );
    }

    @Test
    public void testPostiveSlope() throws Exception
    {
        Point p1 = new Point( -2, -4 );
        Point p2 = new Point( -1, 1 );
        Point p3 = new Point( -1, 4 );
        Point p4 = new Point( 0, 0 );
        Point p5 = new Point( 1, 2 );
        Point p6 = new Point( 3, 6 );
        Point p7 = new Point( 3, -2 );
        Point p8 = new Point( 5, -3 );
        Point[] points = { p1, p2, p3, p4, p5, p6, p7, p8 };

        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        LineSegment mySegment = new LineSegment( p1, p6 );
        assertTrue( bruteSegments.length == 1 );
        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );
    }

    @Test
    public void testNegativeSlope() throws Exception
    {
        Point p1 = new Point( -4, 2 );
        Point p2 = new Point( -3, -2 );
        Point p3 = new Point( -2, 4 );
        Point p4 = new Point( -1, 1 );
        Point p5 = new Point( -1, -3 );
        Point p6 = new Point( 0, -2 );
        Point p7 = new Point( 1, -5 );
        Point p8 = new Point( 2, 4 );
        Point p9 = new Point( 2, 1 );
        Point p10 = new Point( 4, -1 );
        Point[] points = { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10 };

        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        LineSegment mySegment = new LineSegment( p7, p3 );
        assertTrue( bruteSegments.length == 1 );
        assertTrue( mySegment.equals( bruteSegments[ 0 ] ) );

    }

    @Test
    public void testFiveSegments() throws Exception
    {
        Point p1 = new Point( -3, 0 );
        Point p2 = new Point( -3, -1 );
        Point p3 = new Point( -2, 4 );
        Point p4 = new Point( -2, 3 );
        Point p5 = new Point( -2, 1 );
        Point p6 = new Point( -2, -3 );
        Point p7 = new Point( -1, 4 );
//        Point p8 = new Point( -1, 0 );
        Point p9 = new Point( 0, 5 );
//        Point p10 = new Point( 0, 3 );
        Point p11 = new Point( 0, 1 );
        Point p12 = new Point( 0, 0 );
        Point p13 = new Point( 0, -1 );
        Point p14 = new Point( 1, 6 );
        Point p15 = new Point( 1, 5 );
        Point p16 = new Point( 1, 0 );
        Point p17 = new Point( 2, 3 );
        Point p18 = new Point( 2, -1 );
        Point p19 = new Point( 2, -3 );
        Point p20 = new Point( 3, 1 );
        Point p21 = new Point( 3, 0 );
        Point p22 = new Point( 4, -2 );
        Point p23 = new Point( 6, 2 );

        Point[] points = { p1,
                           p2,
                           p3,
                           p4,
                           p5,
                           p6,
                           p7,
//                           p8,
                           p9,
//                           p10,
                           p11,
                           p12,
                           p13,
                           p14,
                           p15,
                           p16,
                           p17,
                           p18,
                           p19,
                           p20,
                           p21,
                           p22,
                           p23 };

        StdRandom.shuffle( points );

        BruteCollinearPoints brute = new BruteCollinearPoints( points );

        LineSegment[] bruteSegments = brute.segments();

        LineSegment[] mySegments = new LineSegment[ 7 ];
        mySegments[ 0 ] = new LineSegment( p4, p14 );
        mySegments[ 1 ] = new LineSegment( p1, p21 );
        mySegments[ 2 ] = new LineSegment( p18, p4 );
        mySegments[ 3 ] = new LineSegment( p13, p9 );
        mySegments[ 4 ] = new LineSegment( p2, p23 );
        mySegments[ 5 ] = new LineSegment( p22, p5 );
        mySegments[ 6 ] = new LineSegment( p6, p3 );

        assertTrue( bruteSegments.length == 7 );
        for ( int i = 0; i < 7; i++ )
        {
            assertTrue( mySegments[ i ].equals( bruteSegments[ i ] ) );
        }

    }

}