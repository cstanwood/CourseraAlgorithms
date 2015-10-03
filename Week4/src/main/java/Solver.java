import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver
{

    private SearchNode currentNode;
    private SearchNode currentNodeTwin;
    private boolean solvable = true;

    private class SearchNode
    {
        private Board board;
        int moves = 0;
        SearchNode previousNode;

        SearchNode( Board board, int moves, SearchNode previous )
        {
            this.board = board;
            this.moves = moves;
            this.previousNode = previous;
        }

    }

    private class ByManhattan implements Comparator<SearchNode>
    {
        @Override
        public int compare( SearchNode node1, SearchNode node2 )
        {
            int manhattan1 = node1.board.manhattan() + node1.moves;
            int manhattan2 = node2.board.manhattan() + node2.moves;
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

    // find a solution to the initial board (using the A* algorithm)
    public Solver( Board initial )
    {
        if ( initial == null )
        {
            throw new NullPointerException();
        }

        solve( initial );

    }

    private void solve( Board initial )
    {
//        Comparator<SearchNode> nodeComparator = new ByManhattan();
        MinPQ<SearchNode> nodes = new MinPQ<SearchNode>( new ByManhattan() );
        Board twin = initial.twin();
        MinPQ<SearchNode> nodesTwin = new MinPQ<SearchNode>( new ByManhattan() );

        currentNode = new SearchNode( initial, 0, null );
        currentNodeTwin = new SearchNode( twin, 0, null );
        if ( initial.isGoal() )
        {
            solvable = true;
            return;
        }
        if ( twin.isGoal() )
        {
            solvable = false;
            return;
        }
        Iterable<Board> neighbours = currentNode.board.neighbors();
        for ( Board neighbour : neighbours )
        {
            nodes.insert( ( new SearchNode( neighbour, 1, currentNode ) ) );
        }

        Iterable<Board> neighboursTwin = currentNodeTwin.board.neighbors();
        for ( Board neighbour : neighboursTwin )
        {
            nodesTwin.insert( ( new SearchNode( neighbour, 1, currentNodeTwin ) ) );
        }

        currentNode = nodes.delMin();
        currentNodeTwin = nodesTwin.delMin();

        while ( !currentNode.board.isGoal() && !currentNodeTwin.board.isGoal() )
        {
            neighbours = currentNode.board.neighbors();
            for ( Board neighbour : neighbours )
            {
                if ( !neighbour.equals( currentNode.previousNode.board ) )
                {
                    nodes.insert( new SearchNode( neighbour, currentNode.moves + 1, currentNode ) );
                }
            }
            currentNode = nodes.delMin();

            neighboursTwin = currentNodeTwin.board.neighbors();
            for ( Board neighbour : neighboursTwin )
            {
                if ( !neighbour.equals( currentNodeTwin.previousNode.board ) )
                {
                    nodesTwin.insert( new SearchNode( neighbour, currentNodeTwin.moves + 1, currentNodeTwin ) );
                }
            }
            currentNodeTwin = nodesTwin.delMin();

        }

        if ( currentNode.board.isGoal() )
        {
            solvable = true;
        }
        else
        {
            solvable = false;
        }
        return;
    }

    // is the initial board solvable?
    public boolean isSolvable()
    {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        if ( isSolvable() )
        {
            return currentNode.moves;
        }
        else
        {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if ( !solvable )
        {
            return null;
        }
        Stack<Board> solution = new Stack<>();
        SearchNode searchNode = currentNode;
        solution.push( searchNode.board );
        while ( searchNode.previousNode != null )
        {
            searchNode = searchNode.previousNode;
            solution.push( searchNode.board );
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

        // prshort solution to standard output
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
