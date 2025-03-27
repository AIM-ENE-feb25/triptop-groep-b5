public class Application {
  public static void main(String[] args) {
    Woordenlijst woordenlijst = new Woordenlijst();
    woordenlijst.print();
    woordenlijst.setSorteerStrategie(new SelectionSort());
    woordenlijst.sorteer();
    woordenlijst.print();
    woordenlijst.setSorteerStrategie(new BubbleSort());
    woordenlijst.sorteer();
    woordenlijst.print();
  }
}
