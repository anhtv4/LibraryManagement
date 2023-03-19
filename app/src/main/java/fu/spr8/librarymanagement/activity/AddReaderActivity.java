package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.ReaderDatabase;
import fu.spr8.librarymanagement.model.ReaderM;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddReaderActivity extends AppCompatActivity {

    EditText name;
    EditText dob;
    EditText address;
    EditText email;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reader);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.et_bookID);
        dob = findViewById(R.id.et_ReaderID);
        address = findViewById(R.id.et_quantity);
        email = findViewById(R.id.et_email);
        btnSave = findViewById(R.id.btn_Save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReaderDatabase readerDatabase = new ReaderDatabase(getApplicationContext());
                ReaderM readerM = new ReaderM();
                String name_text = String.valueOf(name.getText());
                String dob_text = String.valueOf(dob.getText());
                String address_text = String.valueOf(address.getText());
                String email_text = String.valueOf(email.getText());

                if(name_text.equals("") || dob_text.equals("") || address_text.equals("") || email_text.equals("")){
                    Utils.showToast(AddReaderActivity.this, "Please enter all fields!");
                }else{
                    readerM.setName(name_text);
                    readerM.setDob(dob_text);
                    readerM.setAddress(address_text);
                    readerM.setEmail(email_text);

                    readerDatabase.addReader(readerM);
                }

            }
        });
    }
}