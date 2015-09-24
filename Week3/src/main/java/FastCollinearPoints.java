import edu.princeton.cs.algs4.LinkedStack;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints
{

	private Point[]       points;
	private LineSegment[] lineSegments;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints( Point[] points )
	{
		this.points = points;
//		Arrays.sort( points );

		final int N = points.length;
		LinkedStack<LineSegment> lineSegmentStack = new LinkedStack<>();

		//		int i = 0;
		int j;

		//		while ( i < N - 3 )
		for ( int i = 0; i < N - 3; i++ )
		{
			//			int lo = i;
			Comparator<Point> bySlope = points[ i ].slopeOrder();
			Arrays.sort( points, i, N ); // first order by points
			Arrays.sort( points, i + 1, N, bySlope ); //sort is stable ...
			j = i + 1;
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
			//			i = j;

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
