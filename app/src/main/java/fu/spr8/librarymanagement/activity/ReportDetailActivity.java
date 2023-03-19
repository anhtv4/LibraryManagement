package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.db.ReportDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;
import fu.spr8.librarymanagement.model.Report;

public class ReportDetailActivity extends AppCompatActivity {

    EditText id, book, reader, quantity, status, dateLend, dateReturn;
    Button btnStatus;
    int reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reportId = Integer.parseInt(getIntent().getStringExtra("ReportID"));
        ReportDatabase reportDatabase = new ReportDatabase(getApplicationContext());
        BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
        ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
        Report report = reportDatabase.getReportByID(reportId);
        Book book1 = bookDatabase.getBookByID(report.getBookId());
        ReaderM readerM = readerDatabase.getReaderByID(report.getReaderId());

        id = findViewById(R.id.et_rp_id);
        book = findViewById(R.id.et_rp_book);
        reader = findViewById(R.id.et_rp_reader);
        quantity = findViewById(R.id.et_rp_quan);
        status = findViewById(R.id.et_rp_status);
        dateLend = findViewById(R.id.et_rp_lend);
        dateReturn = findViewById(R.id.et_rp_back);
        btnStatus = findViewById(R.id.btnChangeStatus);

        id.setEnabled(false);
        book.setEnabled(false);
        reader.setEnabled(false);
        quantity.setEnabled(false);
        status.setEnabled(false);
        dateLend.setEnabled(false);
        dateReturn.setEnabled(false);

        id.setText(String.valueOf(report.getId()));
        book.setText(book1.getTitle());
        reader.setText(readerM.getName());
        quantity.setText(String.valueOf(report.getQuantity()));
        status.setText(String.valueOf(report.getStatus()));
        dateLend.setText(report.getDateBorrow());
        dateReturn.setText(report.getDateBack());

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int new_status = report.getStatus() == 0 ? 1 : 0;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date_now = sdf.format(System.currentTimeMillis());

                report.setStatus(new_status);
                report.setDateBack(date_now);
                reportDatabase.changeStatus(report);

                Intent intent = new Intent(ReportDetailActivity.this, ListReportActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}