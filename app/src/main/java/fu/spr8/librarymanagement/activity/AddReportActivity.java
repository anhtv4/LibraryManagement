package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.BookDatabase;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.db.ReportDatabase;
import fu.spr8.librarymanagement.model.Book;
import fu.spr8.librarymanagement.model.ReaderM;
import fu.spr8.librarymanagement.model.Report;

public class AddReportActivity extends AppCompatActivity {

    EditText book;
    EditText reader;
    EditText quantity;
    EditText dateBorrow;
    EditText bookTitle;
    EditText readerName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        book = findViewById(R.id.et_bookID);
        reader = findViewById(R.id.et_ReaderID);
        quantity = findViewById(R.id.et_quantity);
        dateBorrow = findViewById(R.id.et_date);
        btnSave = findViewById(R.id.btn_Save);
        bookTitle = findViewById(R.id.et_book_title);
        readerName = findViewById(R.id.et_reader_title);

        bookTitle.setEnabled(false);
        readerName.setEnabled(false);

        quantity.setText(String.valueOf(0));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date_now = sdf.format(System.currentTimeMillis());
        dateBorrow.setText(String.valueOf(date_now));

        book.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String book_text = String.valueOf(book.getText());
                BookDatabase bookDatabase = new BookDatabase(getApplicationContext());
                Book book1 = bookDatabase.getBookByID(Integer.parseInt(book_text.equals("")?"0":book_text));
                if(book1 == null){
                    bookTitle.setText("Not match!");
                }else{
                    bookTitle.setText(book1.getTitle());
                }
            }
        });

        reader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String reader_text = String.valueOf(reader.getText());
                ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
                ReaderM readerM = readerDatabase.getReaderByID(Integer.parseInt(reader_text.equals("") ? "0":reader_text));
                if(readerM == null){
                    readerName.setText("Not match!");
                }else{
                    readerName.setText(readerM.getName());
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDatabase reportDatabase = new ReportDatabase(getApplicationContext());
                Report report = new Report();

                String book_text = String.valueOf(book.getText());
                String reader_text = String.valueOf(reader.getText());
                String quantity_text = String.valueOf(quantity.getText());
                String date_text = String.valueOf(dateBorrow.getText());

                if(book_text.equals("") || reader_text.equals("") || quantity_text.equals("") || date_text.equals("")){
                    Utils.showToast(AddReportActivity.this, "Please enter all fields!");
                }else{
                    report.setBookId(Integer.parseInt(book_text));
                    report.setReaderId(Integer.parseInt(reader_text));
                    report.setQuantity(Integer.parseInt(quantity_text));
                    report.setStatus(0);
                    report.setDateBorrow(date_text);
                    report.setDateBack(null);

                    reportDatabase.addReport(report);
                }

            }
        });
    }
}