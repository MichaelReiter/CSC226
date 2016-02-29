class MyKruskal {
  private long weight;                              // weight of MST
  private Queue<MyEdge> mst = new Queue<MyEdge>();  // edges in MST

  public long weight() {
    return weight;
  }

  public Iterable<MyEdge> edges() {
    return mst;
  }

  public MyKruskal(MyEdgeWeightedGraph G) {
    MinPQ<MyEdge> pq = new MinPQ<MyEdge>();
    for (MyEdge e : G.edges()) {
      pq.insert(e);
    }

    UF uf = new UF(G.V());
    while (!pq.isEmpty() && mst.size() < G.V() - 1) {
      MyEdge e = pq.delMin();
      int v = e.minv();
      int w = e.maxv();
      if (!uf.connected(v, w)) {
        uf.union(v, w);
        mst.enqueue(e);
        weight += e.weight();
      }
    }
  }

  public static long include(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
    return 0; // temp
  }

  public static long exclude(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
    return 0; // temp
  }
}