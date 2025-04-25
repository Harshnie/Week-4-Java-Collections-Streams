import java.util.*;
import java.util.stream.Collectors;

class Book {
    String title;
    String author;
    String genre;
    double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }
}

class BookRecommendation {
    private String title;
    private double rating;

    public BookRecommendation(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "BookRecommendation{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                '}';
    }
}

public class BookRecommendationSystem {

    public static List<List<BookRecommendation>> getTopBookRecommendations(List<Book> books) {
        List<BookRecommendation> recommendations = books.stream()
                .filter(b -> b.genre.equalsIgnoreCase("Science Fiction") && b.rating > 4.0)
                .map(b -> new BookRecommendation(b.title, b.rating))
                .sorted(Comparator.comparingDouble(BookRecommendation::getRating).reversed())
                .limit(10)
                .collect(Collectors.toList());

        // Pagination: 5 books per page
        List<List<BookRecommendation>> pages = new ArrayList<>();
        for (int i = 0; i < recommendations.size(); i += 5) {
            pages.add(recommendations.subList(i, Math.min(i + 5, recommendations.size())));
        }

        return pages;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Dune", "Frank Herbert", "Science Fiction", 4.6),
                new Book("Neuromancer", "William Gibson", "Science Fiction", 4.2),
                new Book("Foundation", "Isaac Asimov", "Science Fiction", 4.1),
                new Book("Snow Crash", "Neal Stephenson", "Science Fiction", 4.3),
                new Book("Hyperion", "Dan Simmons", "Science Fiction", 4.5),
                new Book("The Martian", "Andy Weir", "Science Fiction", 4.4),
                new Book("Ready Player One", "Ernest Cline", "Science Fiction", 4.0),
                new Book("Ender's Game", "Orson Scott Card", "Science Fiction", 4.6),
                new Book("The Left Hand of Darkness", "Ursula K. Le Guin", "Science Fiction", 4.2),
                new Book("Starship Troopers", "Robert A. Heinlein", "Science Fiction", 4.1),
                new Book("Altered Carbon", "Richard K. Morgan", "Science Fiction", 4.3),
                new Book("Brave New World", "Aldous Huxley", "Dystopian", 4.0)
        );

        List<List<BookRecommendation>> pages = getTopBookRecommendations(books);

        // Display paginated results
        int pageNum = 1;
        for (List<BookRecommendation> page : pages) {
            System.out.println("Page " + pageNum++);
            page.forEach(System.out::println);
            System.out.println();
        }
    }
}
