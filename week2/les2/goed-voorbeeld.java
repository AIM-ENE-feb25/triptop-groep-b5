class Order {
  private int orderId;
  private double amount;

  public Order(int orderId, double amount) {
    this.orderId = orderId;
    this.amount = amount;
  }

  public int getOrderId() {
    return orderId;
  }

  public double getAmount() {
    return amount;
  }
}

class OrderCalculator {
  public double calculateTotalPrice(Order order) {
    return order.getAmount() * 1.21; // Inclusief 21% BTW
  }
}

class OrderRepository {
  public void saveToDatabase(Order order) {
    System.out.println("Saving order " + order.getOrderId() + " to database.");
  }
}

class OrderPrinter {
  public void printOrder(Order order, double totalPrice) {
    System.out.println("Printing order " + order.getOrderId() + " with total: " + totalPrice);
  }
}

public class Main {
  public static void main(String[] args) {
    Order order = new Order(101, 100.0);
    OrderCalculator calculator = new OrderCalculator();
    OrderRepository repository = new OrderRepository();
    OrderPrinter printer = new OrderPrinter();

    double totalPrice = calculator.calculateTotalPrice(order);
    repository.saveToDatabase(order);
    printer.printOrder(order, totalPrice);
  }
}
