abstract class TreeAndRepresentation {
  int N;   // number of nodes in the binary tree t.
  int M;   // number of entries in the sequence representation;
  int[] a; // an sequence representation of a tree.
  BT t;    // a binary tree;
  TreeAndRepresentation( int N, BT t )    { this.t = t; this.N = N; }  // subclass should set a,M.
  TreeAndRepresentation( int M, int[] a ) { this.a = a; this.M = M; } // subclass should set t,N.
}

class BT {
  BT L; BT R;
  BT( BT l, BT r ) { L = l; R = r; }
}

public class TestAss1 {

  public static void main ( String[] args ) {
    int M = StdIn.readInt();
    int a[] = new int[M+1];
    int b[];

    for (int i=0; i<M; ++i) {
      a[i] = StdIn.readInt();
    }

    boolean testZO = false;

    if (testZO) {
      ZOrep zo1 = new ZOrep( M, a );
      ZOrep zo2 = new ZOrep( zo1.N, zo1.t );
      b = zo2.a;
    } else {
      FLrep fl1 = new FLrep( M, a );
      FLrep fl2 = new FLrep( fl1.N, fl1.t );
      b = fl2.a;
    }

    // the arrays should be the same.
    for (int i=0; i<M; ++i) System.out.print( a[i] );  System.out.println();
    for (int i=0; i<M; ++i) System.out.print( b[i] );  System.out.println();
  }
}