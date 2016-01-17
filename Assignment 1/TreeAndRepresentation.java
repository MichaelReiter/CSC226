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