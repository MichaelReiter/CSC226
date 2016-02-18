public class a3Tester {

  public static void main(String[] args) {
    In in = new In(args[0]);
    MyEdgeWeightedGraph G = new MyEdgeWeightedGraph(in);

    MyKruskal mst = new MyKruskal(G);
    System.out.println(mst.include(G));
    System.out.println(mst.exclude(G));

    // Expected output:
    // 1197
    // 1293
  }

}