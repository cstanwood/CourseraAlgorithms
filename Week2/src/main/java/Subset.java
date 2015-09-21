import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset
{
	public static void main( String[] args )
	{
        String in = StdIn.readLine();
        String[]strings = in.split( " " );

        int k = Integer.parseInt( args[ 0 ] );

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        
        for ( String s : strings )
        {
            queue.enqueue( s );
        }

        int i = 0;
        for ( String s : queue )
        {
            if (i++ >= k)
            {
                break;
            }
            StdOut.println( s );
        }
    }
}