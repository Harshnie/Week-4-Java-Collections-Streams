package generics;
import java.util.ArrayList;
import java.util.List;


public class SmartWarehouse {
	
	static abstract class WarehouseItem{
		private String name;
		
		public WarehouseItem(String name) {
			this.name = name;
			
		}
		
		public String getName() {
			return name;
			
		}
		
		public abstract String getCategory();
		
		@Override
		public String toString() {
			return getCategory() + ": " + name;
			
		}
		
		
	}
	
	static class Electronics extends WarehouseItem {
		public Electronics(String name) {
			super(name);
		}
		@Override
		public String getCategory() {
			return "Electronics";
			
		}
	}
	static class Groceries extends WarehouseItem{
		public Groceries(String name) {
			super(name);
		}
		@Override
		public String getCategory() {
			return "Groceries";
			
		}
		
	}
	
	static class Furniture extends WarehouseItem{
		public Furniture(String name) {
			super(name);
		}
		
		@Override
		public String getCategory() {
			return "Furniture";
			
		}
		
	}
	static class Storage<T extends WarehouseItem> {
		private List<T> items = new ArrayList<>();
		
		public void addItem(T item) {
			items.add(item);
		}
		
		public List<T> getItems(){
			return items;
		}
	}
	static class WarehouseDisplay {
		public static void displayItems(List<? extends WarehouseItem> items) {
			for (WarehouseItem item : items) {
				System.out.println(item);
			}
		}
	}
	public static void main(String[] args) {
        
        Storage<Electronics> electronicsStorage = new Storage<>();
        Storage<Groceries> groceryStorage = new Storage<>();
        Storage<Furniture> furnitureStorage = new Storage<>();

        electronicsStorage.addItem(new Electronics("Laptop"));
        electronicsStorage.addItem(new Electronics("Smartphone"));

        groceryStorage.addItem(new Groceries("Apples"));
        groceryStorage.addItem(new Groceries("Milk"));

        furnitureStorage.addItem(new Furniture("Chair"));
        furnitureStorage.addItem(new Furniture("Table"));

       
        System.out.println("\n  Electronics ");
        WarehouseDisplay.displayItems(electronicsStorage.getItems());

        System.out.println("\n Groceries ");
        WarehouseDisplay.displayItems(groceryStorage.getItems());

        System.out.println("\n Furniture ");
        WarehouseDisplay.displayItems(furnitureStorage.getItems());
    }
}
	
	


