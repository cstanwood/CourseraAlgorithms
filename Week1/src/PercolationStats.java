import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{

    private final double mean;
    private final double stddev;
    private final double lo;
    private final double hi;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats( int N, int T )
    {
        if ( N <= 0 || T <= 0 )
        {
            throw new IllegalArgumentException();
        }

        double[] results = new double[ T ];

        for ( int t = 0; t < T; t++ )
        {
            Percolation percolation = new Percolation( N );
            while ( !percolation.percolates() )
            {
                int i = StdRandom.uniform( 1, N + 1 );
                int j = StdRandom.uniform( 1, N + 1 );
                if ( !percolation.isOpen( i, j ) )
                {
                    percolation.open( i, j );
                    results[ t ]++;
                }
            }
            results[ t ] = results[ t ] / Math.pow( N, 2 );
        }
        mean = StdStats.mean( results );
        stddev = StdStats.stddev( results );
        lo = mean - 1.96 * stddev / Math.sqrt( ( T ) );
        hi = mean + 1.96 * stddev / Math.sqrt( ( T ) );
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {

        return lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return hi;
    }

    // test client (described below)
    public static void main( String[] args )
    {
        PercolationStats percolationStats =
                new PercolationStats( Integer.parseInt( args[ 0 ] ), Integer.parseInt( args[ 1 ] ) );
        StdOut.println( "mean                    = " + Double.toString( percolationStats.mean ) );
        StdOut.println( "stddev                  = " + Double.toString( percolationStats.stddev ) );
        StdOut.println( "95% confidence interval = " + Double.toString( percolationStats.lo ) + ", " + Double
                .toString( percolationStats.hi ) );

    }
}