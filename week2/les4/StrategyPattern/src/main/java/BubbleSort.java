public class BubbleSort implements ISorteerStrategie {

  @Override
  public void sorteer(String[] woorden) {
    System.out.println("Hallo ik ben BubbleSort");
    for (int grens = woorden.length; grens > 0; grens--) {
      for (int i = 0; i < grens-1; i++) {
        if (woorden[i].compareTo(woorden[i+1]) > 0) {
          String hulp = woorden[i];
          woorden[i] = woorden[i+1];
          woorden[i+1] = hulp;
        }
      }
    }
  }
}
