package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.model.ReaderM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReaderDetailActivity extends AppCompatActivity {
    public int readerID;
    EditText id, name, dob, address, email;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        readerID = Integer.parseInt(getIntent().getStringExtra("ReaderID"));
        ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
        ReaderM readerM = readerDatabase.getReaderByID(readerID);

        id = findViewById(R.id.et_rp_id);
        name = findViewById(R.id.et_rp_book);
        dob = findViewById(R.id.et_rp_reader);
        address = findViewById(R.id.et_rp_quan);
        email = findViewById(R.id.et_rp_status);
        btnUpdate = findViewById(R.id.btnChangeStatus);
        btnDelete = findViewById(R.id.btnDelete);

        id.setEnabled(false);
        id.setText(String.valueOf(readerM.getId()));
        name.setText(readerM.getName());
        dob.setText(readerM.getDob());
        address.setText(readerM.getAddress());
        email.setText(readerM.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_text = name.getText().toString();
                String dob_text = dob.getText().toString();
                String address_text = address.getText().toString();
                String email_text = email.getText().toString();

                if(name_text.equals("") || dob_text.equals("") || address_text.equals("") || email_text.equals("")){
                    Utils.showToast(ReaderDetailActivity.this, "Please enter all fields!");
                }else{
                    ReaderM new_reader = new ReaderM();
                    new_reader.setId(readerID);
                    new_reader.setName(name_text);
                    new_reader.setDob(dob_text);
                    new_reader.setAddress(address_text);
                    new_reader.setEmail(email_text);

                    readerDatabase.updateReader(new_reader);

                    Intent intent = new Intent(ReaderDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readerDatabase.deleteReader(readerID);
                Intent intent = new Intent(ReaderDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}