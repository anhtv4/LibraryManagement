package fu.spr8.librarymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fu.spr8.librarymanagement.R;
import fu.spr8.librarymanagement.Utils;
import fu.spr8.librarymanagement.db.AdminDatabase;
import fu.spr8.librarymanagement.model.Admin;

public class LoginActivity extends AppCompatActivity {

    AdminDatabase adminDatabase;
    EditText edUsername;
    EditText edPassword;
    TextView edForgotPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adminDatabase = new AdminDatabase(getApplicationContext());

        adminDatabase.addAdmin(new Admin("adm1", "Viet Anh", "anhtv", "123"));

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edForgotPass = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                Admin admin = adminDatabase.getAdminAccount(username, password);
                if(admin != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Utils.showToast(LoginActivity.this, "Username or password incorrect!");
                }
            }
        });

        edForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}