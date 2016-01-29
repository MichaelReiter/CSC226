import java.lang.Math;

public class MinMax {

  static int comparisons = 0;

  // Ususal divide and conquer n/2, n/2 split
  public static Pair mmA(int lb, int ub, int[] a) {
    int min, max;

    // odd one out so set min and max to same value
    if (ub == lb) {
      min = a[lb];
      max = a[ub];
    } else if (ub - lb == 1) {
      if (a[lb] < a[ub]) {
        min = a[lb];
        max = a[ub];
      } else {
        min = a[ub];
        max = a[lb];
      }
      comparisons++;
      return new Pair(min, max);
    } else {
      int midpoint = (lb + ub) / 2;
      Pair pair_l = mmA(lb, midpoint, a);
      Pair pair_r = mmA(midpoint+1, ub, a);

      if (pair_l.alpha < pair_r.alpha) {
        min = pair_l.alpha;
      } else {
        min = pair_r.alpha;
      }
      comparisons++;

      if (pair_l.omega > pair_r.omega) {
        max = pair_l.omega;
      } else {
        max = pair_r.omega;
      }
      comparisons++;
    }
    return new Pair(min, max);
  }

  // Refined split to always use ceil(3n/2 - 2) comparisons
  // Try to split into even sizes
  public static Pair mmB(int lb, int ub, int[] a) {

    return null;
  }

  public static void main(String[] args) {
    int[] test = {1,2,3,4,5,6,7,8,9,10};

    Pair pair = mmA(0, test.length-1, test);
    System.out.println(pair);

    System.out.println("Optimal comparisons:\t" + (int)Math.ceil(1.5*test.length - 2));
    System.out.println("Actual comparisons:\t" + comparisons);
  }
}