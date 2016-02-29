// Adapted from the EdgeWeightedGraph class in algs4

class MyEdgeWeightedGraph {
  private final int V;
  private final int E;
  private Bag<MyEdge> edges;

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<MyEdge> edges() {
    return edges;
  }

  public MyEdgeWeightedGraph(In in) {
    int V = in.readInt();
    if (V < 0) {
      throw new IllegalArgumentException("V can't be negative");
    }
    this.V = V;

    int E = in.readInt();
    if (E < 0) {
      throw new IllegalArgumentException("E can't be negative");
    }
    this.E = E;

    edges = new Bag<MyEdge>();
    for (int i = 0; i < E; i++) {
      int v = in.readInt();
      int w = in.readInt();
      long weight = in.readLong();
      MyEdge e = new MyEdge(v, w, weight);
      edges.add(e);
    }
  }
}