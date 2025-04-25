import java.util.*;
import java.util.stream.*;

class Sale {
    private String productId;
    private int quantity;
    private double price;

    public Sale(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

class ProductSales {
    private String productId;
    private double totalRevenue;

    public ProductSales(String productId, double totalRevenue) {
        this.productId = productId;
        this.totalRevenue = totalRevenue;
    }

    public String getProductId() { return productId; }
    public double getTotalRevenue() { return totalRevenue; }

    @Override
    public String toString() {
        return "ProductSales{" +
                "productId='" + productId + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}

public class ProductSalesAnalysis {

    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale("P001", 15, 20.0),
                new Sale("P002", 5, 50.0),
                new Sale("P003", 25, 10.0),
                new Sale("P004", 12, 30.0),
                new Sale("P005", 8, 60.0),
                new Sale("P006", 20, 15.0),
                new Sale("P007", 18, 22.0),
                new Sale("P008", 11, 100.0)
        );

        List<Sale> filteredSales = sales.stream()
                .filter(s -> s.getQuantity() > 10)
                .collect(Collectors.toList());

        List<ProductSales> revenueList = filteredSales.stream()
                .map(s -> new ProductSales(s.getProductId(), s.getQuantity() * s.getPrice()))
                .collect(Collectors.toList());

        List<ProductSales> sortedByRevenue = revenueList.stream()
                .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
                .collect(Collectors.toList());

        List<ProductSales> top5 = sortedByRevenue.stream()
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 Products by Total Revenue:");
        top5.forEach(System.out::println);
    }
}

