package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.adapter.BookAdapter;
import fu.spr8.librarymanagement.adapter.ReaderAdapter;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ListReaderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Button btnNew;
    Button btnReload;
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reader);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
        List<ReaderM> readers = readerDatabase.getAllReader();

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ReaderAdapter(readers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListReaderActivity.this, AddReaderActivity.class);
                startActivity(intent);
            }
        });

        btnReload = findViewById(R.id.btnReload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
                List<ReaderM> readers = readerDatabase.getAllReader();

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new ReaderAdapter(readers);
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
                String name = search.getText().toString().trim();
                ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
                List<ReaderM> readers = readerDatabase.searchReaderByName(name);

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new ReaderAdapter(readers);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
}