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

    // finds all line segments containing 4 points
    public BruteCollinearPoints( Point[] points )
    {
        this.points = points;
        Arrays.sort( points );
    }

    // the number of line segments
    public int numberOfSegments()
    {

        return 0;
    }

    // the line segments
    public LineSegment[] segments()
    {
        // assume there are no line segments with more than 4 points

        final int N = points.length;
        LinkedStack<LineSegment> lineSegments = new LinkedStack<>();

        for ( int i = 0; i < N - 3; i ++ )
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
                                lineSegments.push( new LineSegment( points[ i ], points[ l ] ) );
                            }
                        }
                    }
                }
            }
        }

        // need to return an array
        LineSegment[] lineSegmentArray = new LineSegment[ lineSegments.size() ];
//        return lineSegments.toArray( lineSegmentArray  );

        int i = 0;
        for ( LineSegment lineSegment : lineSegments )
        {
            lineSegmentArray[i++] = lineSegment;
        }
        return lineSegmentArray;
    }

}
