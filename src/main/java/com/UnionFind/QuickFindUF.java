package com.UnionFind;

/**
 * Created by cstanwood on 08/09/15.
 */
public class QuickFindUF
{
    private int[] id;

    public static void main( String[] args )
    {
//        0-5 6-9 5-8 8-9 4-8 6-1
        QuickFindUF quickFindUF = new QuickFindUF( 10 );
        quickFindUF.union( 0, 5 );
        quickFindUF.union( 6, 9 );
        quickFindUF.union( 5, 8 );
        quickFindUF.union( 8, 9 );
        quickFindUF.union( 4, 8 );
        quickFindUF.union( 6, 1 );
        quickFindUF.print();
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

    public QuickFindUF( int N )
    {
        id = new int[ N ];
        for ( int i = 0; i < N; i++ )
        {
            id[ i ] = i;
        }
    }

    public boolean connected( int p, int q )
    {
        return id[ p ] == id[ q ];
    }

    public void union( int p, int q )
    {
        int pid = id[ p ];
        int qid = id[ q ];
        for ( int i = 0; i < id.length; i++ )
        {
            if ( id[ i ] == pid )
            {
                id[ i ] = qid;
            }
        }
    }
}