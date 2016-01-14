class FLrep extends TreeAndRepresentation {

  // VARIABLES, IF ANY NEEDED

  FLrep( int m, int[] b ) { // given sequence build tree
    this.a = b;
    this.M = m;
  }

  FLrep( int n, BT t ) { // given tree build sequence
    this.t = t;
    this.N = n;
  }

  // ANY ADDITIONAL METHODS GO HERE
}