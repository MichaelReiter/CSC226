public class A3Tester {
  public static void main(String[] args) {
    In in = new In(args[0]);
    MyEdgeWeightedGraph G = new MyEdgeWeightedGraph(in);

    MyKruskal mst = new MyKruskal(G);
    System.out.println(mst.weight());
    System.out.println(MyKruskal.include(G)); // 1197
    System.out.println(MyKruskal.exclude(G)); // 1293
  }
}