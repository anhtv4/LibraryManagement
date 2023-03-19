package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.model.Book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookDetailActivity extends AppCompatActivity {
    public int bookID;
    EditText id, isbn, title, author, cate, quan;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookID = Integer.parseInt(getIntent().getStringExtra("BookID"));
        BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
        Book book = bookDatabase.getBookByID(bookID);

        id = findViewById(R.id.et_rp_id);
        isbn = findViewById(R.id.et_rp_book);
        title = findViewById(R.id.et_rp_reader);
        author = findViewById(R.id.et_rp_quan);
        cate = findViewById(R.id.et_rp_status);
        quan = findViewById(R.id.et_rp_lend);
        btnUpdate = findViewById(R.id.btnChangeStatus);
        btnDelete = findViewById(R.id.btnDelete);

        id.setEnabled(false);
        id.setText(String.valueOf(book.getId()));
        isbn.setText(book.getIsbn());
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        cate.setText(book.getCategory());
        quan.setText(String.valueOf(book.getQuantity()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isbn_text = isbn.getText().toString();
                String title_text = title.getText().toString();
                String author_text = author.getText().toString();
                String cate_text = cate.getText().toString();
                String quan_text = quan.getText().toString();

                if(isbn_text.equals("") || title_text.equals("") || author_text.equals("") || cate_text.equals("") || quan_text.equals("")){
                    Utils.showToast(BookDetailActivity.this, "Please enter all fields!");
                }else{
                    Book new_book = new Book();
                    new_book.setId(bookID);
                    new_book.setIsbn(isbn_text);
                    new_book.setTitle(title_text);
                    new_book.setAuthor(author_text);
                    new_book.setCategory(cate_text);
                    new_book.setQuantity(Integer.parseInt(quan_text));

                    bookDatabase.updateBook(new_book);

                    Intent intent = new Intent(BookDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDatabase.deleteBook(bookID);
                Intent intent = new Intent(BookDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}