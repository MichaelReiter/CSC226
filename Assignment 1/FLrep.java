class FLrep extends TreeAndRepresentation {

  private int k;

  FLrep(int m, int[] b) { // given sequence build tree
    super(m, b);
  }

  FLrep(int n, BT t) { // given tree build sequence
    super(n, t);
    M = 2 * N + 1;
    a = new int[M];
    traverse(t, 0);
  }

  private void recordValue(int value) {
    a[k] = value;
    k++;
  }

  void traverse(BT t, int depth) {
    if (t == null) {
      return;
    }
    traverse(t.L, depth+1);
    recordValue(depth);
    traverse(t.R, depth+1);
  }
}