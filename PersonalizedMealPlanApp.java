package generics;

interface MealPlan {
 void displayMeal();
}

class VegetarianMeal implements MealPlan {
 public void displayMeal() {
     System.out.println("Vegetarian Meal: Grilled Paneer, Salad, Brown Rice");
 }
}

class VeganMeal implements MealPlan {
 public void displayMeal() {
     System.out.println("Vegan Meal: Tofu Stir Fry, Quinoa, Green Smoothie");
 }
}

class KetoMeal implements MealPlan {
 public void displayMeal() {
     System.out.println("Keto Meal: Chicken Breast, Avocado, Cauliflower Rice");
 }
}

class HighProteinMeal implements MealPlan {
 public void displayMeal() {
     System.out.println("High-Protein Meal: Egg Whites, Oats, Protein Shake");
 }
}

class Meal<T extends MealPlan> {
 private T meal;

 public Meal(T meal) {
     this.meal = meal;
 }

 public void serveMeal() {
     meal.displayMeal();
 }
}


class MealPlanner {
 public static <T extends MealPlan> Meal<T> generateMealPlan(T mealType) {
     System.out.println("Generating your personalized meal plan...");
     return new Meal<>(mealType);
 }
}

public class PersonalizedMealPlanApp {
 public static void main(String[] args) {
     // Generate different types of meal plans
     Meal<VegetarianMeal> vegMeal = MealPlanner.generateMealPlan(new VegetarianMeal());
     vegMeal.serveMeal();

     Meal<VeganMeal> veganMeal = MealPlanner.generateMealPlan(new VeganMeal());
     veganMeal.serveMeal();

     Meal<KetoMeal> ketoMeal = MealPlanner.generateMealPlan(new KetoMeal());
     ketoMeal.serveMeal();

     Meal<HighProteinMeal> proteinMeal = MealPlanner.generateMealPlan(new HighProteinMeal());
     proteinMeal.serveMeal();
 }
}

