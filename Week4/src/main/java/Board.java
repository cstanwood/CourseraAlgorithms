public class Board
{
    private int[][] current;
    private int[][] goal;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board( int[][] blocks )
    {
        int length = blocks.length;
        this.current = new int[ length ][ length ];
        // make a deep copy
        for ( int i = 0; i < blocks.length; i++ )
        {
            for ( int j = 0; j < blocks[ i ].length; j++ )
            {
                current[ i ][ j ] = blocks[ i ][ j ];
            }
        }
    }

    // board dimension N
    public int dimension()
    {
        return current.length;
    }


    // number of blocks out of place
    public int hamming()
    {
        return 0;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {

        return 0;
    }

    // is this board the goal board?
    public boolean isGoal()
    {

        return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {

        return null;
    }

    // does this board equal y?
    public boolean equals( Object y )
    {

        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {


        return null;
    }


    // string representation of this board (in the output format specified below)
    public String toString()
    {
        StringBuilder output = new StringBuilder( String.format( "%d\n", dimension() ) );
        for ( int i = 0; i < current.length; i++ )
        {
            for ( int j = 0; j < current.length; j++ )
            {
                output.append( String.format( "%3d", current[ i ][ j ] ) );
            }
            output.append( "\n" );
        }
        return output.toString();
    }

    // unit tests (not graded)
    public static void main( String[] args )
    {

    }
}