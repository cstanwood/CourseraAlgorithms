import edu.princeton.cs.algs4.LinkedStack;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints
{

    private Point[] points;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints( Point[] points )
    {
        if ( points == null )
        {
            throw new NullPointerException();
        }
        for ( Point point : points )
        {
            if ( point == null )
            {
                throw new NullPointerException();
            }
        }

        Arrays.sort( points );
        for ( int i = 0; i < points.length - 1; i++ )
        {
            if ( points[ i ].equals( points[ i + 1 ] ) )
            {
                throw new IllegalArgumentException();
            }
        }


        this.points = points;

        final int N = points.length;
        LinkedStack<LineSegment> lineSegmentStack = new LinkedStack<>();

        int j;

        for ( int i = 0; i < N - 3; i++ )
        {
            Arrays.sort( points, i, N ); // first order by points
            Comparator<Point> bySlope = points[ i ].slopeOrder();
            Arrays.sort( points, i + 1, N, bySlope ); //sort is stable ...

            // for debugging
            double[] slopes = new double[ N - i - 1 ];
            for ( int z = i; z < slopes.length; z++ )
            {
                slopes[ z ] = points[ i ].slopeTo( points[ z + 1 ] );
            }

            j = i + 1;

            while ( j < N - 1 )
            {
                // find first point with matching slope
                while ( j < N - 1 && points[ i ].slopeTo( points[ j ] ) != points[ j ].slopeTo( points[ j + 1 ] ) )
                {
                    j++;
                }
                int k = j;

                // get all the points with that slope. They are in order!
                while ( j < N - 1 && points[ i ].slopeTo( points[ j ] ) == points[ j ].slopeTo( points[ j + 1 ] ) )
                {
                    j++;
                }

                if ( j - k + 2 >= 4 ) // got a segment
                {
                    lineSegmentStack.push( new LineSegment( points[ i ], points[ j ] ) );
                }
            }


        }

        // need to return an array
        lineSegments = new LineSegment[ lineSegmentStack.size() ];
        //        return lineSegmentStack.toArray( lineSegmentArray  );

        int k = 0;
        for ( LineSegment lineSegment : lineSegmentStack )
        {
            lineSegments[ k++ ] = lineSegment;
        }

    }

    // the number of line segments
    public int numberOfSegments()
    {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return lineSegments;
    }
}
