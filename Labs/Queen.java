class Queen {
  static int[] x = new int[128];
  static boolean[] a = new boolean[128];  // row                 (-)
  static boolean[] b = new boolean[128];  // up-right diagonal   (/)
  static boolean[] c = new boolean[128];  // down-right diagonal (\)
  static int count = 2;
  static int n = 8;

  static void printSolution() {
    for (int i = 0; i < n; i++) {
      System.out.print(x[i]);
    }
    System.out.println();
  }

  static void generateSolution(int col) {
    for (int row = 0; row < n; row++) {
      if (a[row] && b[row+col] && c[row-col+n]) {
        x[col] = row;
        a[row] = b[row+col] = c[row-col+n] = false;
        if (col == n-1) {
          printSolution();
        } else {
          generateSolution(col+1);
        }
        a[row] = b[row+col] = c[row-col+n] = true;
      }
    }
    count++;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 2*n + 2; i++) {
      a[i] = b[i] = c[i] = true;
    }
    generateSolution(0);

    System.out.println(count);
  }
}