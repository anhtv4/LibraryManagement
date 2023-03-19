package fu.spr8.librarymanagement.model;

public class Report {
    private int id;
    private int bookId;
    private int readerId;
    private int quantity;
    private int status;
    private String dateBorrow;
    private String dateBack;

    public Report() {
    }

    public Report(int id, int bookId, int readerId, int quantity, int status, String dateBorrow, String dateBack) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.quantity = quantity;
        this.status = status;
        this.dateBorrow = dateBorrow;
        this.dateBack = dateBack;
    }

    public Report(int bookId, int readerId, int quantity, int status, String dateBorrow, String dateBack) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.quantity = quantity;
        this.status = status;
        this.dateBorrow = dateBorrow;
        this.dateBack = dateBack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(String dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public String getDateBack() {
        return dateBack;
    }

    public void setDateBack(String dateBack) {
        this.dateBack = dateBack;
    }
}
