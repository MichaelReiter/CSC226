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
    // FOR YOU TO FILL IN
  }

  public long include(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
  }

  public long exclude(MyEdgeWeightedGraph G) {
    // FOR YOU TO FILL IN
  }

}