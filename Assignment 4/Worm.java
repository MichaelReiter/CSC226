public class Worm {

  // PUT YOUR CLASS VARIABLE HERE

  public Worm(In in) { 
    // Create a new problem instance.
  }

  public double dist(String origP, String destP) {
    // return the distance from origP to destP
    return 1; //temp
  }

  public int worms(String origP, String destP) { 
    // least number of wormholes in any shortest path from origP to destP
    return 1; //temp
  }

  public String query(String origP, String destP) {
    // Output the "The distance from ... wormholes." string.
    return "temp";
  }

  public static void main(String[] args) {
    // You can test your program with something like this.
    In in = new In(args[0]);
    int T = in.readInt();
    for (int t = 1; t <= T; t++) {
      System.out.println("Case " + t + ":");
      Worm w = new Worm(in);
      int Q = in.readInt();
      for (int i = 0; i < Q; i++) {
        String p1s = in.readString();
        String p2s = in.readString();
        System.out.println(w.query(p1s, p2s));
      }
    }
  }
}
