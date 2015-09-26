import java.util.*;

public class FastCollinearPoints
{

    private Point[] points;
    private LineSegment[] lineSegments;
    private boolean calculationDone;

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

    private class PotentialSegment implements Comparable<PotentialSegment>
    {
        private Point p1;
        private Point p2;
        private double slope;

        PotentialSegment( Point p1, Point p2 )
        {
            this.p1 = p1;
            this.p2 = p2;
            slope = p1.slopeTo( p2 );
        }

        public Point getP1()
        {
            return p1;
        }

        public Point getP2()
        {
            return p2;
        }

        @Override
        public boolean equals( Object o )
        {
            if ( this == o )
            {
                return true;
            }
            if ( o == null || getClass() != o.getClass() )
            {
                return false;
            }

            PotentialSegment that = (PotentialSegment) o;

            if ( Double.compare( that.slope, slope ) != 0 )
            {
                return false;
            }
            return p2.equals( that.p2 );

        }

        @Override
        public int hashCode()
        {
            int result;
            long temp;
            result = p2.hashCode();
            temp = Double.doubleToLongBits( slope );
            result = 31 * result + (int) ( temp ^ ( temp >>> 32 ) );
            return result;
        }

        @Override
        public int compareTo( PotentialSegment that )
        {
            if (this.p2.compareTo( that.p2 )== 0)
            {
                if ( this.slope < that.slope )
                {
                    return -1;
                }
                else if ( this.slope > that.slope )
                {
                    return +1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                return this.p2.compareTo( that.p2 );
            }
        }
    }

    private void calculateSegments( Point[] points )
    {
        final int N = points.length;
//        LinkedStack<LineSegment> lineSegmentStack = new LinkedStack<>();
//        ArrayList<PotentialSegment> segmentTracker = new ArrayList<>();
        Set<PotentialSegment> segmentTracker = new TreeSet<PotentialSegment>();

        int j;

        for ( int i = 0; i < N - 3; i++ )
        {
//            Point[] tempPoints = Arrays.copyOf( points, points.length );
//            Point temp = tempPoints[ 0 ];
//            tempPoints[ 0 ] = tempPoints[ i ];
//            tempPoints[ i ] = temp;

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
                    segmentTracker.add( new PotentialSegment( points[ i ], points[ j ] ) );
//                    lineSegmentStack.push( new LineSegment( tempPoints[ i ], tempPoints[ j ] ) );
//                    Point[] allPotentialPoints =
                }
            }
        }

        PotentialSegment[] potentialSegments = new PotentialSegment[ segmentTracker.size() ];
        segmentTracker.toArray( potentialSegments );

        lineSegments = new LineSegment[ potentialSegments.length ];
        for ( int i = 0; i < potentialSegments.length; i++ )
        {
            lineSegments[ i ] = new LineSegment( potentialSegments[ i ].p1, potentialSegments[ i ].p2 );
        }

/*
        // need to return an array
        lineSegments = new LineSegment[ lineSegmentStack.size() ];
        //        return lineSegmentStack.toArray( lineSegmentArray  );

        int k = 0;
        for ( LineSegment lineSegment : lineSegmentStack )
        {
            lineSegments[ k++ ] = lineSegment;
        }
*/

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
