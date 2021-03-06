import java.util.ArrayList;
import java.util.List;

public class Board
{
    private short[][] blocks;
    private short[][] goal;
    private short moves = 0;
    //	private Board previousBoard;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board( int[][] blocks )
    {
        int length = blocks.length;
        this.blocks = new short[ length ][ length ];
        this.goal = new short[ length ][ length ];
        short k = 1;
        // make a deep copy
        for ( short i = 0; i < blocks.length; i++ )
        {
            for ( short j = 0; j < blocks[ i ].length; j++ )
            {
                this.blocks[ i ][ j ] = (short) blocks[ i ][ j ];
                goal[ i ][ j ] = k++;
            }
        }
        goal[ length - 1 ][ length - 1 ] = 0;
    }

    private int[][] convertToInt( short[][] shortBlocks )
    {
        int[][] intBlocks = new int[ shortBlocks.length ][ shortBlocks.length ];
        for ( int i = 0; i < shortBlocks.length; i++ )
        {
            for ( int j = 0; j < shortBlocks.length; j++ )
            {
                intBlocks[ i ][ j ] = shortBlocks[ i ][ j ];
            }
        }
        return intBlocks;
    }

    // board dimension N
    public int dimension()
    {
        return  blocks.length;
    }


    // number of blocks out of place
    public int hamming()
    {
        short hammingCount = moves;
        for ( short i = 0; i < dimension(); i++ )
        {
            for ( short j = 0; j < dimension(); j++ )
            {
                if ( blocks[ i ][ j ] != goal[ i ][ j ] && blocks[ i ][ j ] != 0 )
                {
                    hammingCount++;
                }
            }
        }

        return hammingCount;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        short manhattan = moves;
        int colGoal;
        int rowGoal;
        for ( short row = 0; row < dimension(); row++ )
        {
            for ( short col = 0; col < dimension(); col++ )
            {
                if ( blocks[ row ][ col ] != 0 )
                {
                    colGoal = ( blocks[ row ][ col ] - 1 ) % dimension();
                    rowGoal = ( blocks[ row ][ col ] - 1 ) / dimension();
                    manhattan += Math.abs( colGoal - col );
                    manhattan += Math.abs( rowGoal - row );
                }
            }
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        for ( short i = 0; i < dimension(); i++ )
        {
            for ( short j = 0; j < dimension(); j++ )
            {
                if ( blocks[ i ][ j ] != goal[ i ][ j ] )
                {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        int[][] twinBlocks = new int[ dimension() ][ dimension() ];
        for ( int i = 0; i < dimension(); i++ )
        {
            for ( int j = 0; j < dimension(); j++ )
            {
                twinBlocks[ i ][ j ] = blocks[ i ][ j ];
            }
        }

        short[] space = findSpace();
        short row;
        if ( space[ 0 ] == 0 )
        {
            row = 1;
        }
        else
        {
            row = 0;
        }

        int temp = twinBlocks[ row ][ 0 ];
        twinBlocks[ row ][ 0 ] = twinBlocks[ row ][ 1 ];
        twinBlocks[ row ][ 1 ] = temp;

        return new Board( twinBlocks );
    }

    // does this board equal y?
    public boolean equals( Object y )
    {
        if ( y == null )
        {
            return false;
        }
        if ( !( y instanceof Board ) )
        {
            return false;
        }
        if ( y == this )
        {
            return true;
        }
        if ( ( (Board) y ).dimension() != this.dimension() )
        {
            return false;
        }
        if ( this.toString().equals( y.toString() ) )
        {
            return true;
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        short[] space = findSpace();
        short row = space[ 0 ];
        short col = space[ 1 ];

        Board left = null;
        Board right = null;
        Board top = null;
        Board bottom = null;

        if ( col != 0 )
        {
            blocks[ row ][ col ] = blocks[ row ][ col - 1 ];
            blocks[ row ][ col - 1 ] = 0;
            left = new Board( convertToInt( blocks ) );
            blocks[ row ][ col - 1 ] = blocks[ row ][ col ];
            blocks[ row ][ col ] = 0;
        }

        if ( col != dimension() - 1 )
        {
            blocks[ row ][ col ] = blocks[ row ][ col + 1 ];
            blocks[ row ][ col + 1 ] = 0;
            right = new Board( convertToInt( blocks ) );
            blocks[ row ][ col + 1 ] = blocks[ row ][ col ];
            blocks[ row ][ col ] = 0;
        }

        if ( row != 0 )
        {
            blocks[ row ][ col ] = blocks[ row - 1 ][ col ];
            blocks[ row - 1 ][ col ] = 0;
            top = new Board( convertToInt( blocks ) );
            blocks[ row - 1 ][ col ] = blocks[ row ][ col ];
            blocks[ row ][ col ] = 0;
        }

        if ( row != dimension() - 1 )
        {
            blocks[ row ][ col ] = blocks[ row + 1 ][ col ];
            blocks[ row + 1 ][ col ] = 0;
            bottom = new Board( convertToInt( blocks ) );
            blocks[ row + 1 ][ col ] = blocks[ row ][ col ];
            blocks[ row ][ col ] = 0;
        }

        List<Board> neighbors = new ArrayList<Board>();
        if ( left != null )
        {
            neighbors.add( left );
        }
        if ( right != null )
        {
            neighbors.add( right );
        }
        if ( top != null )
        {
            neighbors.add( top );
        }
        if ( bottom != null )
        {
            neighbors.add( bottom );
        }
        return neighbors;
    }

    private short[] findSpace()
    {
        for ( short i = 0; i < dimension(); i++ )
        {
            for ( short j = 0; j < dimension(); j++ )
            {
                if ( blocks[ i ][ j ] == 0 )
                {
                    short[] space = new short[ 2 ];
                    space[ 0 ] = i;
                    space[ 1 ] = j;
                    return space;
                }
            }
        }
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString()
    {
        StringBuilder output =
                new StringBuilder( String.format( "%2d\n", dimension()) );
//                new StringBuilder( String.format( "%d\t%d\t%d\n", dimension(), hamming(), manhattan() ) );
        for ( short i = 0; i < blocks.length; i++ )
        {
            for ( short j = 0; j < blocks.length; j++ )
            {
                output.append( String.format( "%3d", blocks[ i ][ j ] ) );
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