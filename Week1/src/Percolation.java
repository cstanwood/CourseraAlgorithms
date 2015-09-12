import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    //    Corner cases.  By convention, the row and column indices i and j are integers between 1 and N, where (1, 1)
    // is the upper-left site: Throw a java.lang.IndexOutOfBoundsException if any argument to open(), isOpen(), or
    // isFull() is outside its prescribed range. The constructor should throw a java.lang.IllegalArgumentException if
    // N ≤ 0.
    private int[][] grid;

    private static final int OPEN = 1;

    private static final int TOP = 0;
    private static int BOTTOM;

    private int N;

    private WeightedQuickUnionUF weightedQuickUnionUF;

    // create N-by-N grid, with all sites blocked
    public Percolation( int N )
    {
        if ( N <= 0 )
        {
            throw new IllegalArgumentException();
        }

        this.N = N;

        grid = new int[ N + 1 ][ N + 1 ];//[ 2 ];

        BOTTOM = N * N + 1;

        weightedQuickUnionUF = new WeightedQuickUnionUF( N * N + 2 );
    }

    // open site (row i, column j) if it is not open already
    public void open( int i, int j )
    {
        if ( i < 1 || i > N || j < 1 || j > N)
        {
            throw new IndexOutOfBoundsException();
        }

        // open site
        grid[ i ][ j ] = OPEN;

        // connect to site above
        if ( i == 1 )
        {
            weightedQuickUnionUF.union( getId( i,j ), TOP );
        }
        else
        {
            if ( isOpen( i - 1, j ) )
            {
                weightedQuickUnionUF.union( getId( i, j ), getId( i - 1, j ) );
            }
        }

        //connect to site below
        if ( i == N )
        {
            weightedQuickUnionUF.union( getId( i, j ), BOTTOM );
        }
        else
        {
            if ( isOpen( i + 1, j ) )
            {
                weightedQuickUnionUF.union( getId( i, j ), getId( i + 1, j ) );
            }
        }

        //connect to site at left
        if ( j != 1 )
        {
            if ( isOpen( i, j - 1 ) )
            {
                weightedQuickUnionUF.union( getId( i, j ), getId( i, j - 1 ) );
            }
        }

        //connect to site at right
        if ( j < N )
        {
            if ( isOpen( i, j + 1 ) )
            {
                weightedQuickUnionUF.union( getId( i, j ), getId( i, j + 1 ) );
            }
        }
    }

    private int getId( int i, int j )
    {
        return N * ( i - 1 ) + j;
    }

    // is site (row i, column j) open?
    public boolean isOpen( int i, int j )
    {

        if ( i < 1 || i > N || j < 1 || j > N)
        {
            throw new IndexOutOfBoundsException();
        }

        return grid[ i ][ j ] == OPEN;
    }

    // is site (row i, column j) full?
    public boolean isFull( int i, int j )
    {

        if ( i < 1 || i > N || j < 1 || j > N)
        {
            throw new IndexOutOfBoundsException();
        }

        return weightedQuickUnionUF.connected( TOP, getId( i, j ) );
    }

    // does the system percolate?
    public boolean percolates()
    {
        return weightedQuickUnionUF.connected( TOP, BOTTOM );
    }

    // test client (optional)
    public static void main( String[] args )
    {

    }

}