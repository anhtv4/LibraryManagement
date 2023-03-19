package fu.spr8.librarymanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.model.Book;

public class BookDatabase {
    static final String TABLE_NAME = "Book";
    private static final String COL_1 = "id";
    private static final String COL_2 = "isbn";
    private static final String COL_3 = "title";
    private static final String COL_4 = "author";
    private static final String COL_5 = "category";
    private static final String COL_6 = "quantity";
    public static SQLiteDatabase db;
    private static DatabaseHelper dbHelper;
    private Context context;
    static final String TABLE_CREATE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            TABLE_NAME, COL_1, COL_2, COL_3, COL_4, COL_5, COL_6);

    public BookDatabase(Context context1) {
        context = context1;
        dbHelper = new DatabaseHelper(context);
    }

    public void addBook(Book book) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, book.getIsbn());
        values.put(COL_3, book.getTitle());
        values.put(COL_4, book.getAuthor());
        values.put(COL_5, book.getCategory());
        values.put(COL_6, book.getQuantity());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Add book successfully!");

    }

    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            books.add(book);
            cursor.moveToNext();
        }
        return books;
    }

    public void updateBook(Book book) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (book.getIsbn() != null) values.put(COL_2, book.getIsbn());
        if (book.getTitle() != null) values.put(COL_3, book.getTitle());
        if (book.getAuthor() != null) values.put(COL_4, book.getAuthor());
        if (book.getCategory() != null) values.put(COL_5, book.getCategory());
        values.put(COL_6, book.getQuantity());

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(book.getId())});
        db.close();
        Utils.showToast(context.getApplicationContext(), "Book update successfully!");
    }

    public void deleteBook(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
        Utils.showToast(this.context.getApplicationContext(), "Deleted book successfully!");
    }

    public Book getBookByID(int id) {
        db = dbHelper.getReadableDatabase();
        Book book = null;
        Cursor cursor = db.query(TABLE_NAME, null, COL_1 + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
        }
        return book;
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = null;
        if (title.equals("")) {
            query = "SELECT * FROM " + TABLE_NAME;
        } else {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " LIKE '%" + title + "%'";
        }

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
                books.add(book);
                cursor.moveToNext();
            }
        } else {
            Utils.showToast(this.context.getApplicationContext(), "No matching books found!");
        }

        return books;
    }

    public void updateQuantityBook(int id, int quantity) {
        int new_quan = ((this.getBookByID(id)).getQuantity()) - quantity;
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_6, new_quan);

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
