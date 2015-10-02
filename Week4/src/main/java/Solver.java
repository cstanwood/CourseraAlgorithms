import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver
{

    int moves;
    SearchNode currentNode;

    private class SearchNode
    {
        Board board;
        int moves = 0;
        SearchNode previous;

        SearchNode( Board board, int moves, SearchNode previous )
        {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        Board getBoard()
        {
            return board;
        }

        int getMoves()
        {
            return moves;
        }

        SearchNode getPrevious()
        {
            return previous;
        }

    }

    private class ByManhattan implements Comparator<SearchNode>
    {
        @Override
        public int compare( SearchNode node1, SearchNode node2 )
        {
            int manhattan1 = node1.getBoard().manhattan() + node1.getMoves();
            int manhattan2 = node2.getBoard().manhattan() + node2.getMoves();
            if ( manhattan1 < manhattan2 )
            {
                return -1;
            }
            else if ( manhattan1 > manhattan2 )
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    private MinPQ<SearchNode> nodes;

    // find a solution to the initial board (using the A* algorithm)
    public Solver( Board initial )
    {
        Comparator<SearchNode> nodeComparator = new ByManhattan();
        nodes = new MinPQ<SearchNode>( nodeComparator );

        moves = 0;
        nodes.insert( new SearchNode( initial, moves++, null ) );
        currentNode = nodes.delMin();

        while ( !currentNode.getBoard().isGoal() )
        {
            Iterable<Board> neighbours = currentNode.getBoard().neighbors();
            for ( Board neighbour : neighbours )
            {
                if ( !neighbour.equals( currentNode ) )
                {
                    nodes.insert( new SearchNode( neighbour, moves, currentNode ) );
                }
            }
            currentNode = nodes.delMin();
            moves++;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable()
    {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        Queue<Board> solution = new Queue<Board>();
        SearchNode searchNode = currentNode;
        solution.enqueue( searchNode.board );
        while ( searchNode.previous != null )
        {
            searchNode = searchNode.previous;
            solution.enqueue( searchNode.getBoard() );
        }
        return solution;
    }

    // solve a slider puzzle
    public static void main( String[] args )
    {
        // create initial board from file
        In in = new In( args[ 0 ] );
        int N = in.readInt();
        int[][] blocks = new int[ N ][ N ];
        for ( int i = 0; i < N; i++ )
        {
            for ( int j = 0; j < N; j++ )
            {
                blocks[ i ][ j ] = in.readInt();
            }
        }
        Board initial = new Board( blocks );

        // solve the puzzle
        Solver solver = new Solver( initial );

        // print solution to standard output
        if ( !solver.isSolvable() )
        {
            StdOut.println( "No solution possible" );
        }
        else
        {
            StdOut.println( "Minimum number of moves = " + solver.moves() );
            for ( Board board : solver.solution() )
            {
                StdOut.println( board );
            }
        }
    }

}
