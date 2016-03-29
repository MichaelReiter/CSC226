public class PrintMaze {
  
  private static void renderTop(boolean b, boolean s) {
    System.out.print(s ? " *" : "  ");
    System.out.print(b ? " |" : "  ");
  }

  private static void renderBot(boolean b) {
    System.out.print(b ? "---+" : "   +");
  }

  public static void displayMaze(Maze m) {
    renderBot(false);

    for (int c = 1; c <= m.getCols(); c++) {
      renderBot(true);
    }

    System.out.println();

    for (int r = 1; r <= m.getRows(); r++) {
      renderTop(true, false);

      for (int c = 1; c <= m.getCols(); c++) {
        renderTop(m.ok(r, c, 2), m.ok(r, c, 4));
      }
      
      System.out.println();
      renderBot(false);

      for (int c = 1; c <= m.getCols(); c++) {
        renderBot(m.ok(r, c, 1));
      }
      System.out.println();
    }
  }
}