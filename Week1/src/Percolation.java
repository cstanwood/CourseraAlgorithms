public class Percolation
{

    private int[][][] grid;
    private static final int ID = 0;
    private static final int OPEN = 1;
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final int TOP = 0;
    private static int BOTTOM;

    private int[] id;
    private int[] sz;

    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation( int N )
    {
        this.N = N;
        BOTTOM = N + 1;

        int k = 1;

        grid = new int[ N + 1 ][ N + 1 ][ 2 ];

        for ( int i = 1; i <= N; i++ )
        {
            for ( int j = 1; j <= N; j++ )
            {
                grid[ i ][ j ][ ID ] = k++;
                grid[ i ][ j ][ OPEN ] = FALSE;
            }
        }

        initializeTree( N * N + 2 ); // +2 to account for top and bottom
    }

    // open site (row i, column j) if it is not open already
    public void open( int i, int j )
    {
        // open site
        grid[ i ][ j ][ OPEN ] = TRUE;

        // connect to site above
        if ( i == 1 )
        {
            union( grid[ i ][ j ][ ID ], TOP );
        }
        else
        {
            if ( isOpen( i - 1, j ) )
            {
                union( grid[ i ][ j ][ ID ], grid[ i - 1 ][ j ][ ID ] );
            }
        }

        //connect to site below
        if ( i == N )
        {
            union( grid[ i ][ j ][ ID ], BOTTOM );
        }
        else
        {
            if ( isOpen( i + 1, j ) )
            {
                union( grid[ i ][ j ][ ID ], grid[ i + 1 ][ j ][ ID ] );
            }
        }

        //connect to site at left
        if ( j != 1 )
        {
            if ( isOpen( i, j - 1 ) )
            {
                union( grid[ i ][ j ][ ID ], grid[ i ][ j - 1 ][ ID ] );
            }
        }

        //connect to site at right
        if ( j < N )
        {
            if ( isOpen( i, j + 1 ) )
            {
                union( grid[ i ][ j ][ ID ], grid[ i ][ j + 1 ][ ID ] );
            }
        }


    }

    // is site (row i, column j) open?
    public boolean isOpen( int i, int j )
    {
        return grid[ i ][ j ][ OPEN ] == TRUE;
    }

    // is site (row i, column j) full?
    public boolean isFull( int i, int j )
    {
        return connected( TOP, grid[ i ][ j ][ ID ] );
    }

    // does the system percolate?
    public boolean percolates()
    {
        return connected( TOP, BOTTOM );
    }

    // test client (optional)
    public static void main( String[] args )
    {

    }

    private void initializeTree( int N )
    {
        id = new int[ N ];
        sz = new int[ N ];
        for ( int i = 0; i < N; i++ )
        {
            id[ i ] = i;
            sz[ i ] = 1;
        }
    }

/*    public void print()
    {
        for ( Integer i : id )
        {
            System.out.print( i + " " );
        }
        System.out.println();
        return;
    }*/

    private int root( int i )
    {
        while ( i != id[ i ] )
        {
            i = id[ i ];
        }
        return i;
    }

    private boolean connected( int p, int q )
    {
        return root( p ) == root( q );
    }

    private void union( int p, int q )
    {
        int i = root( p );
        int j = root( q );
        if ( i == j )
        {
            return;
        }
        if ( sz[ i ] < sz[ j ] )
        {
            id[ i ] = j;
            sz[ j ] += sz[ i ];
        }
        else
        {
            id[ j ] = i;
            sz[ i ] += sz[ j ];
        }
    }


}