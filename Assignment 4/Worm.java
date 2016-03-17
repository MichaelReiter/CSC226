import java.util.HashMap;

public class Worm {
  private Wormhole[] wormholes;
  private double[][] distTo;
  private HashMap<String, Integer> planetHash = new HashMap<String, Integer>();
  private double[][] optimalDistTo;
  private int[][] holesBetween;
  Planet[] planetArray;

  public Worm(In in) {
    int V = in.readInt();
    distTo = new double[V][V];
    planetArray = new Planet[V];

    // Read planets from input
    for (int v = 0; v < V; v++) {
      String name = in.readString();
      int x = in.readInt();
      int y = in.readInt();
      int z = in.readInt();
      Planet planet = new Planet(name, x, y, z);
      planetArray[v] = planet;
      planetHash.put(name, v);
    }

    // Initialize distTo
    for (int v = 0; v < V; v++) {
      for (int w = 0; w < V; w++) {
        if (v == w) {
          // Distance to itself is 0
          distTo[v][w] = 0;
        } else {
          // Compute Euclidean distance
          int vX = planetArray[v].x;
          int vY = planetArray[v].y;
          int vZ = planetArray[v].z;
          
          int wX = planetArray[w].x;
          int wY = planetArray[w].y;
          int wZ = planetArray[w].z;

          long distance = Math.round(Math.sqrt( Math.pow(vX-wX, 2) + Math.pow(vY-wY, 2) + Math.pow(vZ-wZ, 2) ));
          distTo[v][w] = distance;
        }
      }
    }

    // Floyd-Warshall Algorithm
    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          distTo[i][j] = Math.min(distTo[i][j], distTo[i][k] + distTo[k][j]);
        }
      }
    }

    // Copy distTo into optimalDistTo
    optimalDistTo = new double[V][V];
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        optimalDistTo[i][j] = distTo[i][j];
      }
    }

    // Initialize wormhole array
    int numberOfWormholes = in.readInt();
    wormholes = new Wormhole[numberOfWormholes];
    for (int i = 0; i < numberOfWormholes; i++) {
      int entrance = planetHash.get(in.readString());
      int exit = planetHash.get(in.readString());
      Wormhole w = new Wormhole(entrance, exit);
      wormholes[i] = w;
    }

    holesBetween = new int[V][V];

    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (holeExistsBetween(i, j)) {
          optimalDistTo[i][j] = 0;
          holesBetween[i][j]++;
        }
      }
    }

    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (optimalDistTo[i][k] + optimalDistTo[k][j] < optimalDistTo[i][j]) {
            optimalDistTo[i][j] = optimalDistTo[i][k] + optimalDistTo[k][j];
            holesBetween[i][j] = holesBetween[i][k] + holesBetween[k][j];
          }
        }
      }
    }
  }

  private boolean holeExistsBetween(int entrance, int exit) {
    for (int i = 0; i < wormholes.length; i++) {
      if (wormholes[i].entrance == entrance && wormholes[i].exit == exit) {
        return true;
      }
    }
    return false;
  }

  public double dist(String origP, String destP) {
    int origPIndex = planetHash.get(origP);
    int destPIndex = planetHash.get(destP);

    return optimalDistTo[origPIndex][destPIndex];
  }

  public int worms(String origP, String destP) {
    int currentPlanet = planetHash.get(origP);
    int destinationPlanet = planetHash.get(destP);

    return holesBetween[currentPlanet][destinationPlanet];
  }

  public String query(String origP, String destP) {
    double distance = dist(origP, destP);
    int worms = worms(origP, destP);
    return "The distance from " + origP + " to " + destP + " is " + (int)distance + " parsecs using " + worms + " wormholes.";
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

class Planet {
  public String name;
  public int x, y, z;

  Planet(String name, int x, int y, int z) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

class Wormhole {
  public int entrance, exit;

  Wormhole(int entrance, int exit) {
    this.entrance = entrance;
    this.exit = exit;
  }
}