// Adapted from the KruskalMST class in algs4

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
    // MinPQ<MyEdge> minPQ = new MinPQ<MyEdge>();
    // for (MyEdge edge : G.edges()) {
    //   minPQ.insert(edge);
    // }

    // Initialize MinPQ in O(E) time instead of O(ElogE)
    MyEdge[] edges = new MyEdge[G.E()];
    int i = 0;
    for (MyEdge edge : G.edges()) {
      edges[i] = edge;
      i++;
    }
    MinPQ<MyEdge> minPQ = new MinPQ<MyEdge>(edges);

    UF unionfind = new UF(G.V());
    while (!minPQ.isEmpty() && mst.size() < G.V() - 1) {
      MyEdge edge = minPQ.delMin();
      int v = edge.minv();
      int w = edge.maxv();
      if (!unionfind.connected(v, w)) {
        unionfind.union(v, w);
        mst.enqueue(edge);
        this.weight += edge.weight();
      }
    }
  }

  public static long include(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
    return -99; // temp
  }

  public static long exclude(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
    return -99; // temp
  }
}