import edu.princeton.cs.algs4.LinkedStack;

import java.util.Arrays;

/**
 * Created by  cstanwood on 23/09/15.
 *
 */
public class BruteCollinearPoints
{
    private Point[] points;
    private LineSegment[] lineSegments;
    private boolean calculationDone;

    // finds all line segments containing 4 points
    public BruteCollinearPoints( Point[] points )
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

        this.points = new Point[ points.length ];
        this.points = Arrays.copyOf( points, points.length );

        Arrays.sort( this.points );
        for ( int i = 0; i < this.points.length - 1; i++ )
        {
            if ( this.points[ i ].equals( this.points[ i + 1 ] ) )
            {
                throw new IllegalArgumentException();
            }
        }

    }

    private void calculateSegments( Point[] points )
    {
        Arrays.sort( points );

        // assume there are no line segments with more than 4 points

        final int N = points.length;
        LinkedStack<LineSegment> lineSegmentStack = new LinkedStack<>();

        for ( int i = 0; i < N - 3; i++ )
        {
            for ( int j = i + 1; j < N - 2; j++ )
            {
                for ( int k = j + 1; k < N - 1; k++ )
                {
                    if ( points[ i ].slopeTo( points[ j ] ) == points[ j ].slopeTo( points[ k ] ) )
                    {
                        for ( int l = k + 1; l < N; l++ )
                        {
                            if ( points[ j ].slopeTo( points[ k ] ) == points[ k ].slopeTo( points[ l ] ) )
                            {
                                lineSegmentStack.push( new LineSegment( points[ i ], points[ l ] ) );
                            }
                        }
                    }
                }
            }
        }

        // need to return an array
        lineSegments = new LineSegment[ lineSegmentStack.size() ];

        int i = 0;
        for ( LineSegment lineSegment : lineSegmentStack )
        {
            lineSegments[ i++ ] = lineSegment;
        }

        calculationDone = true;
    }

    // the number of line segments
    public int numberOfSegments()
    {
        if ( !calculationDone )
        {
            calculateSegments( points );
        }
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        if ( !calculationDone )
        {
            calculateSegments( points );
        }
        return lineSegments;
    }

}
