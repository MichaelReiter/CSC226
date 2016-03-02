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
    int includeSum = 0;

    for (int i = 0; i < G.E(); i++) {
      Queue<MyEdge> mst = new Queue<MyEdge>();
      MyEdge[] edges = new MyEdge[G.E()-1];

      int j = 0;
      int index = 0;
      MyEdge include = new MyEdge(0,0,0); //temporary values to be overwritten
      for (MyEdge edge : G.edges()) {
        if (j == i) {
          include = edge;
        } else {
          edges[index] = edge;
          index++;
        }
        j++;
      }
      MinPQ<MyEdge> minPQ = new MinPQ<MyEdge>(edges);

      int iterationWeight = 0;
      UF unionfind = new UF(G.V());
      int v1 = include.minv();
      int v2 = include.maxv();
      unionfind.union(v1, v2);
      mst.enqueue(include);
      iterationWeight += include.weight();

      while (!minPQ.isEmpty() && mst.size() < G.V() - 1) {
        MyEdge edge = minPQ.delMin();
        int v = edge.minv();
        int w = edge.maxv();
        if (!unionfind.connected(v, w)) {
          unionfind.union(v, w);
          mst.enqueue(edge);
          iterationWeight += edge.weight();
        }
      }

      includeSum += iterationWeight;
    }
    
    return includeSum;
  }

  public static long exclude(MyEdgeWeightedGraph G) {
    int excludeSum = 0;

    for (int i = 0; i < G.E(); i++) {
      Queue<MyEdge> mst = new Queue<MyEdge>();
      MyEdge[] edges = new MyEdge[G.E()-1];

      int j = 0;
      int index = 0;
      MyEdge include = new MyEdge(0,0,0); //temporary values to be overwritten
      for (MyEdge edge : G.edges()) {
        if (j != i) {
          edges[index] = edge;
          index++;
        }
        j++;
      }
      MinPQ<MyEdge> minPQ = new MinPQ<MyEdge>(edges);

      int iterationWeight = 0;
      UF unionfind = new UF(G.V());
      while (!minPQ.isEmpty() && mst.size() < G.V() - 1) {
        MyEdge edge = minPQ.delMin();
        int v = edge.minv();
        int w = edge.maxv();
        if (!unionfind.connected(v, w)) {
          unionfind.union(v, w);
          mst.enqueue(edge);
          iterationWeight += edge.weight();
        }
      }

      if (mst.size() != G.V()-1) {
        return -99;
      }

      excludeSum += iterationWeight;
    }
    return excludeSum;
  }
}