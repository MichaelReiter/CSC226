public class TestAss1 {

  public static void main( String[] args ) {
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