package fu.spr8.librarymanagement.model;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int quantity;

    public Book() {
    }

    public Book(String isbn, String title, String author, String category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
    }

    public Book(int id, String isbn, String title, String author, String category, int quantity) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
