class ZOrep extends TreeAndRepresentation {

  private int k;

  ZOrep(int m, int[] b) { // given sequence build tree
    super(m, b);
    N = (M-1)/2;
    k = -1;
    t = build();
  }
  
  ZOrep(int n, BT t) { // given tree build sequence
    super(n, t);
    M = 2 * N + 1;
    a = new int[M];
    k = 0;
    traverse(t);
  }

  BT build() { return( a[++k] == 0 ? null : new BT( build(), build() )); }

  private void recordValue(int value) {
    a[k] = value;
    k++;
  }

  void traverse(BT t) {
    if (t != null) {
      recordValue(1);
    } else {
      recordValue(0);
      return;
    }
    traverse(t.L);
    traverse(t.R);
  }
}