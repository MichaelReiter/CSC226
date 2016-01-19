class FLrep extends TreeAndRepresentation {

  private int k;

  FLrep(int m, int[] b) { // given sequence build tree
    super(m, b);
    N = M;
    t = build(0, M, 0);
  }

  FLrep(int n, BT t) { // given tree build sequence
    super(n, t);
    M = N;
    a = new int[M];
    traverse(t, 0);
  }

  private BT build(int first, int last, int depth) {
    for (int i = first; i < last; i++) {
      if (a[i] == depth) {
        BT left = build(first, i, depth+1);
        BT right = build(i+1, last, depth);
        return new BT(left, right);
      }
    }
    return null;
  }

  void traverse(BT t, int depth) {
    if (t == null) {
      return;
    }
    traverse(t.L, depth+1);
    a[k] = depth;
    k++;
    traverse(t.R, depth);
  }
}