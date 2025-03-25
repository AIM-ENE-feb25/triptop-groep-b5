class Order {
  private int orderId;
  private double amount;

  public Order(int orderId, double amount) {
    this.orderId = orderId;
    this.amount = amount;
  }

  public double calculateTotalPrice() {
    // Simuleert een berekening van de totale prijs
    return amount * 1.21; // Inclusief 21% BTW
  }

  public void saveToDatabase() {
    System.out.println("Saving order " + orderId + " to database.");
  }

  public void printOrder() {
    System.out.println("Printing order " + orderId + " with total: " + calculateTotalPrice());
  }

  public static void main(String[] args) {
    Order order = new Order(101, 100.0);
    order.calculateTotalPrice();
    order.saveToDatabase();
    order.printOrder();
  }
}
