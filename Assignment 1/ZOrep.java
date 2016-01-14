class ZOrep extends TreeAndRepresentation {

  private int k;

  ZOrep( int m, int[] b ) { // given sequence build tree
    super( m, b );
    N = (M-1)/2;
    k  = -1;
    t = build();
  }

  ZOrep( int n, BT t ) { // given tree build sequence
    // YOU SHOULD ADD SOME CODE HERE.     
    traverse( t );
  }

  BT build() { return( a[++k] == 0 ? null : new BT( build(), build() )); }

  void traverse( BT t ) {
    // FOR YOU TO FILL IN
  }
}