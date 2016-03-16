import java.util.HashMap;

public class Worm {
  private int[] holesTo;
  private double[][] distTo;
  private HashMap<String, Integer> planetHash = new HashMap<String, Integer>();
  private double[][] optimalDistTo;
  private int[][] holesTaken;
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

    // Copy distTo into optimalDistTo before being overwritten
    optimalDistTo = new double[V][V];
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        optimalDistTo[i][j] = distTo[i][j];
      }
    }

    // Initialize holesTo
    holesTo = new int[V];
    for (int i = 0; i < V; i++) {
      holesTo[i] = -1;
    }
    int numberOfWormholes = in.readInt();
    for (int i = 0; i < numberOfWormholes; i++) {
      String entrance = in.readString();
      String exit = in.readString();
      holesTo[planetHash.get(entrance)] = planetHash.get(exit);
    }

    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (holesTo[i] == j) {
          optimalDistTo[i][j] = 0;
        }
      }
    }

    holesTaken = new int[V][V];

    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (optimalDistTo[i][k] + optimalDistTo[k][j] < optimalDistTo[i][j]) {
            optimalDistTo[i][j] = optimalDistTo[i][k] + optimalDistTo[k][j];
            holesTaken[i][j] = holesTaken[i][k] + holesTaken[k][j] + 1;
          }
        }
      }
    }
  }

  private boolean directWormholePathExists(int pIn, int pOut) {
    if (pIn == pOut) {
      return false;
    }
    int currentPlanet = pIn;
    while (holesTo[currentPlanet] != -1) {
      if (currentPlanet == pOut) {
        return true;
      } else {
        currentPlanet = holesTo[currentPlanet];
      }
    }
    return false;
  }

  private void planetToWormhole(int pIn, int pOut) {
    double cost = distTo[pIn][pOut];
    for (int i = 0; i < planetArray.length; i++) {
      int p = planetHash.get(planetArray[i].name);
      if (directWormholePathExists(p, pOut)) {
        if (distTo[pIn][p] < cost) {
          optimalDistTo[pIn][pOut] = distTo[pIn][p];
        }
      }
    }
  }

  private double wormholeToPlanet(int pIn, int pOut) {
    if (pIn == pOut) {
      return -1;
    }
    int currentPlanet = pIn;
    double distance = distTo[pIn][pOut];
    while(holesTo[currentPlanet] != -1) {
      if (distTo[currentPlanet][pOut] < distance) {
        distance = distTo[currentPlanet][pOut];
      } else {
        currentPlanet = holesTo[currentPlanet];
      }
    }
    return distance;
  }

  public double dist(String origP, String destP) {
    int origPIndex = planetHash.get(origP);
    int destPIndex = planetHash.get(destP);

    return optimalDistTo[origPIndex][destPIndex];
  }

  public int worms(String origP, String destP) {
    int currentPlanet = planetHash.get(origP);
    int destinationPlanet = planetHash.get(destP);

    return holesTaken[currentPlanet][destinationPlanet];
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
  int x, y, z;

  Planet(String name, int x, int y, int z) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}