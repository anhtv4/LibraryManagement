package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fu.spr8.librarymanagement.R;

public class MainActivity extends AppCompatActivity {
    Button btnBookManagement;
    Button btnReaderManagement;

    Button btnReportManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBookManagement = findViewById(R.id.btnBook);

        btnBookManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManagement.class);
                startActivity(intent);
            }
        });

        btnReaderManagement = findViewById(R.id.btnReaderManagement);

        btnReaderManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListReaderActivity.class);
                startActivity(intent);
            }
        });

        btnReportManagement = findViewById(R.id.btnReport);

        btnReportManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListReportActivity.class);
                startActivity(intent);
            }
        });
    }
}