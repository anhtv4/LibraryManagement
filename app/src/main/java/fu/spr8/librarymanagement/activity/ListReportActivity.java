package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.adapter.BookAdapter;
import fu.spr8.librarymanagement.adapter.ReportAdapter;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReportDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.Report;

public class ListReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Button btnNew;
    Button btnReload;
    EditText search;
    Button btnStatus;

    int current_status = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ReportDatabase reportDatabase = new ReportDatabase(getApplicationContext());
        List<Report> reports = reportDatabase.getAllReport();

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ReportAdapter(reports);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListReportActivity.this, AddReportActivity.class);
                startActivity(intent);
            }
        });

        btnReload = findViewById(R.id.btnReload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDatabase reportDatabase = new ReportDatabase(getApplicationContext());
                List<Report> reports = reportDatabase.getAllReport();

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new ReportAdapter(reports);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btnStatus = findViewById(R.id.btnStatus);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDatabase reportDatabase = new ReportDatabase(getApplicationContext());
                List<Report> reports = reportDatabase.searchReportByStatus(current_status);

                recyclerView = findViewById(R.id.recyclerView);

                adapter = new ReportAdapter(reports);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                current_status = current_status == 0 ? 1 : current_status == 1 ? 2 : 0;
            }
        });
    }
}