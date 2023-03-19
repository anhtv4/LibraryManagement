package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.model.Book;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    EditText isbn;
    EditText title;
    EditText author;
    EditText category;
    EditText quantity;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isbn = findViewById(R.id.et_bookID);
        title = findViewById(R.id.et_ReaderID);
        author = findViewById(R.id.et_quantity);
        category = findViewById(R.id.et_email);
        quantity = findViewById(R.id.et_quan);
        btnSave = findViewById(R.id.btn_Save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
                Book book = new Book();
                String isbn_text = String.valueOf(isbn.getText());
                String title_text = String.valueOf(title.getText());
                String author_text = String.valueOf(author.getText());
                String category_text = String.valueOf(category.getText());
                String quantity_text = String.valueOf(quantity.getText());

                if(isbn_text.equals("") || title_text.equals("") || author_text.equals("") || category_text.equals("") || quantity_text.equals("")){
                    Utils.showToast(AddBookActivity.this, "Please enter all fields!");
                }else{
                    book.setIsbn(isbn_text);
                    book.setTitle(title_text);
                    book.setAuthor(author_text);
                    book.setCategory(category_text);
                    book.setQuantity(Integer.parseInt(quantity_text));

                    bookDatabase.addBook(book);
                }

            }
        });
    }
}