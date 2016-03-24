class Queen {
  static int[] x = new int[128];
  static boolean[] a = new boolean[128];  // row                  (-)
  static boolean[] b = new boolean[128];  // up-right diagonal    (/)
  static boolean[] c = new boolean[128];  // down-right diagonal  (\)

  static void printSolution() {
    for (int i = 0; i < 8; i++) {
      System.out.println(x[i]);
      System.out.println();
    }
  }

  static void generateSolution(int col) {
    for (int row = 0; row < 8; row++) {
      if (a[row] && b[row+col] && c[row-col+8]) {
        x[col] = row;
        a[row] = b[row+col] = c[row-col+8] = false;
        if (col == 8-1) {
          printSolution();
        } else {
          generateSolution(col+1);
        }
        a[row] = b[row+col] = c[row-col+8] = true;
      }
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 2*8 + 2; i++) {
      a[i] = b[i] = c[i] = true;
    }
    generateSolution(0);
  }
}