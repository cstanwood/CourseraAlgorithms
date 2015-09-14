import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    // Corner cases.  By convention, the row and column indices i and j are integers between 1 and N, where (1, 1)
    // is the upper-left site: Throw a java.lang.IndexOutOfBoundsException if any argument to open(), isOpen(), or
    // isFull() is outside its prescribed range. The constructor should throw a java.lang.IllegalArgumentException if
    // N ≤ 0.
    private boolean[][] grid;

    private final int TOP = 0;

    //    private boolean[] bottom;
    private int bottom;

    private int N;

    private WeightedQuickUnionUF percolationUF;
    private WeightedQuickUnionUF fullUF;

    // create N-by-N grid, with all sites blocked
    public Percolation( int N )
    {
        if ( N <= 0 )
        {
            throw new IllegalArgumentException();
        }

        this.N = N;

        grid = new boolean[ N + 1 ][ N + 1 ];

//        bottom = new boolean[ N / 2 + N % 2 ];
        bottom = N * N + 1;

        percolationUF = new WeightedQuickUnionUF( N * N + 2 );
        fullUF = new WeightedQuickUnionUF( N * N + 1 );
    }

    // open site (row i, column j) if it is not open already
    public void open( int i, int j )
    {
        if ( i < 1 || i > N || j < 1 || j > N )
        {
            throw new IndexOutOfBoundsException();
        }

        // open site
        grid[ i ][ j ] = true;

        // connect to site above
        if ( i == 1 )
        {
            percolationUF.union( getId( i, j ), TOP );
            fullUF.union( getId( i, j ), TOP );
        }
        else
        {
            if ( isOpen( i - 1, j ) )
            {
                percolationUF.union( getId( i, j ), getId( i - 1, j ) );
                fullUF.union( getId( i, j ), getId( i - 1, j ) );
            }
        }

//        boolean exitAlreadyOpen = false;

        //connect to site at left
        if ( j != 1 )
        {
            if ( isOpen( i, j - 1 ) )
            {
//                exitAlreadyOpen = true;
                percolationUF.union( getId( i, j ), getId( i, j - 1 ) );
                fullUF.union( getId( i, j ), getId( i, j - 1 ) );
            }
        }

        //connect to site at right
        if ( j < N )
        {
            if ( isOpen( i, j + 1 ) )
            {
//                exitAlreadyOpen = true;
                percolationUF.union( getId( i, j ), getId( i, j + 1 ) );
                fullUF.union( getId( i, j ), getId( i, j + 1 ) );
            }
        }

        //connect to site below
        if ( i == N )
        {
//            if ( !exitAlreadyOpen )
//            {
            percolationUF.union( getId( i, j ), bottom );
//            }
        }
        else
        {
            if ( isOpen( i + 1, j ) )
            {
                percolationUF.union( getId( i, j ), getId( i + 1, j ) );
                fullUF.union( getId( i, j ), getId( i + 1, j ) );
            }
        }
    }

/*    private int getNextBottom()
    {
        int b;

        //noinspection StatementWithEmptyBody
        for ( b = 0; b < bottom.length && bottom[ b ]; b++ )
        {
            ;
        }
        bottom[ b ] = true;
        return getId( N, N ) + b + 1;
    }*/

    private int getId( int i, int j )
    {
        return N * ( i - 1 ) + j;
    }

    // is site (row i, column j) open?
    public boolean isOpen( int i, int j )
    {

        if ( i < 1 || i > N || j < 1 || j > N )
        {
            throw new IndexOutOfBoundsException();
        }

        return grid[ i ][ j ];
    }

    // is site (row i, column j) full?
    public boolean isFull( int i, int j )
    {

        if ( i < 1 || i > N || j < 1 || j > N )
        {
            throw new IndexOutOfBoundsException();
        }

        return fullUF.connected( TOP, getId( i, j ) );
    }

    // does the system percolate?
    public boolean percolates()
    {
        return percolationUF.connected( TOP, bottom );

/*
        for ( int b = 0; b < bottom.length && bottom[ b ]; b++ )
        {
            if ( percolationUF.connected( TOP, getId( N, N ) + b + 1 ) )
            {
                if ( percolationUF.connected( TOP, getId( N, N ) + b + 1 ) )
                {
                    return true;
                }
            }
        }
        return false;
*/
    }

    // test client (optional)
    public static void main( String[] args )
    {

    }

}