// Michael Reiter
// Recursive MinMax algorithms
// V00831568

public class MinMax {

  // Ususal divide and conquer n/2, n/2 split
  public static Pair mmA(int lb, int ub, int[] a) {
    int min, max;

    // odd one out so set min and max to same value
    if (ub == lb) {
      min = a[lb];
      max = a[lb];
    } 
    // base case: compare two values in a pair
    else if (ub - lb + 1 == 2) {
      if (a[lb] < a[ub]) {
        min = a[lb];
        max = a[ub];
      } else {
        min = a[ub];
        max = a[lb];
      }
    } else {
      int midpoint = (lb + ub) / 2;
      Pair pair_l = mmA(lb, midpoint, a);
      Pair pair_r = mmA(midpoint+1, ub, a);

      // compare pairs' mins and maxs to find new min and max
      if (pair_l.alpha < pair_r.alpha) {
        min = pair_l.alpha;
      } else {
        min = pair_r.alpha;
      }

      if (pair_l.omega > pair_r.omega) {
        max = pair_l.omega;
      } else {
        max = pair_r.omega;
      }
    }
    return new Pair(min, max);
  }

  // Refined split to always use ceil(3n/2 - 2) comparisons
  // Splits into even sizes
  public static Pair mmB(int lb, int ub, int[] a) {
    int min, max;

    // odd one out so set min and max to same value
    if (ub == lb) {
      min = a[lb];
      max = a[lb];
    }
    // base case: compare two values in a pair
    else if (ub - lb + 1 == 2) {
      if (a[lb] < a[ub]) {
        min = a[lb];
        max = a[ub];
      } else {
        min = a[ub];
        max = a[lb];
      }
    } else {

      // a loop to determine the optimal split (where at least one side has a power of 2 elements)
      int i = 0;
      int splitpoint = (lb + ub) / 2 - i;
      int bottom = splitpoint - lb + 1;
      int top = ub - splitpoint;
      while (!isPowerOf2(bottom) && !isPowerOf2(top)) {
        i++;
        splitpoint = (lb + ub) / 2 - i;
        bottom = splitpoint - lb + 1;
        top = ub - splitpoint;
      }

      Pair pair_l = mmB(lb, splitpoint, a);
      Pair pair_r = mmB(splitpoint+1, ub, a);

      // compare pairs' mins and maxs to find new min and max
      if (pair_l.alpha < pair_r.alpha) {
        min = pair_l.alpha;
      } else {
        min = pair_r.alpha;
      }

      if (pair_l.omega > pair_r.omega) {
        max = pair_l.omega;
      } else {
        max = pair_r.omega;
      }
    }
    return new Pair(min, max);
  }

  private static boolean isPowerOf2(int n) {
    return ((n & (n-1)) == 0);
  }
}