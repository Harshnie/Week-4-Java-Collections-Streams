package generics;
import java.util.ArrayList;
import java.util.List;


public class OnlineMarketplace {
	
	interface BookCategory{}
	interface ClothingCategory {}
	interface GadgetCategory {}
	
	static class Product<T> {
		private String name;
		private double price;
		private T category;
		
		public Product(String name, double price, T category) {
			this.name = name;
			this.price = price;
			this.category = category;
		
		}
		public String getName() {
			return name;
		}
		public double getPrice() {
			return price;
		}
		public T getCategory() {
			return category;
		}
		public void setPrice(double price) {
			this.price = price;
			
		}
		@Override
		
		public String toString() {
			return name + "(" + category.getClass().getSimpleName() + ") - Rs." + price;
			
		}
	}
	public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double oldPrice = product.getPrice();
        double discountAmount = oldPrice * (percentage / 100.0);
        product.setPrice(oldPrice - discountAmount);
    }

    
    public static void main(String[] args) {
        
        BookCategory fiction = new BookCategory() {};
        ClothingCategory mensWear = new ClothingCategory() {};
        GadgetCategory smartphones = new GadgetCategory() {};

        Product<BookCategory> book1 = new Product<>("Harry Potter", 40.0, fiction);
        Product<ClothingCategory> shirt = new Product<>("Men's Shirt", 25.0, mensWear);
        Product<GadgetCategory> phone = new Product<>("Smartphone", 500.0, smartphones);

        List<Product<?>> catalog = new ArrayList<>();
        catalog.add(book1);
        catalog.add(shirt);
        catalog.add(phone);

        System.out.println(" Catalog Before Discount ");
        for (Product<?> product : catalog) {
            System.out.println(product);
        }

        applyDiscount(book1, 10);    
        applyDiscount(shirt, 20);   
        applyDiscount(phone, 5);     

        System.out.println("\n Catalog After Discount ");
        for (Product<?> product : catalog) {
            System.out.println(product);
        }
    }
}
	
	

