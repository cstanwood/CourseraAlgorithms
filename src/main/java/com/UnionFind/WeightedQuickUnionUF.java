package com.UnionFind;

/**
 * Created by cstanwood on 08/09/15.
 */
public class WeightedQuickUnionUF
{
    private int[] id;
    private int[] sz;

    public static void main( String[] args )
    {
//       1-9 2-4 4-0 5-6 6-9 0-7 4-3 5-4 5-8
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF( 10 );
        weightedQuickUnionUF.union( 1, 9 );
        weightedQuickUnionUF.union( 2, 4 );
        weightedQuickUnionUF.union( 4, 0 );
        weightedQuickUnionUF.union( 5, 6 );
        weightedQuickUnionUF.union( 6, 9 );
        weightedQuickUnionUF.union( 0, 7 );
        weightedQuickUnionUF.union( 4, 3 );
        weightedQuickUnionUF.union( 5, 4 );
        weightedQuickUnionUF.union( 5, 8 );
        weightedQuickUnionUF.print();
    }

    public WeightedQuickUnionUF( int N )
    {
        id = new int[ N ];
        sz = new int[ N ];
        for ( int i = 0; i < N; i++ )
        {
            id[ i ] = i;
            sz[ i ] = 1;
        }

//        sz = new int[ N ];
    }

    public void print()
    {
        for ( Integer i : id )
        {
            System.out.print( i + " " );
        }
        System.out.println();
        return;
    }

    private int root( int i )
    {
        while ( i != id[ i ] )
        {
            i = id[ i ];
        }
        return i;
    }

    public boolean connected( int p, int q )
    {
        return root( p ) == root( q );
    }

    public void union( int p, int q )
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
