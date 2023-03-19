package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.adapter.BookAdapter;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.DatabaseHelper;
import fu.spr8.librarymanagement.model.Book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class BookManagement extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Button btnNew;
    Button btnReload;
    EditText search;
    Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_management);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
        List<Book> books = bookDatabase.getAllBook();

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new BookAdapter(books);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookManagement.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        btnReload = findViewById(R.id.btnReload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
                List<Book> books = bookDatabase.getAllBook();

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new BookAdapter(books);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = findViewById(R.id.etSearch);
                String title = search.getText().toString().trim();
                BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
                List<Book> books = bookDatabase.searchBookByTitle(title);

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new BookAdapter(books);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
}