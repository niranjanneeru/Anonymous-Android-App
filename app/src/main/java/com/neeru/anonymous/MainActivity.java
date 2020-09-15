package com.neeru.anonymous;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userNameEditText, passwordEditText;
    private Button submit;
    private Toolbar toolbar;
    private TextInputLayout user, pass;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Initialization
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submit = findViewById(R.id.attendance);
        user = findViewById(R.id.textField1);
        pass = findViewById(R.id.textField2);

        // Intent Check
        Intent newIntent = getIntent();
        if (newIntent != null) {
            String message = newIntent.getStringExtra("Pass");
            if (message != null) {
                Snackbar.make(findViewById(R.id.main_parent), "Invalid Credentials", Snackbar.LENGTH_LONG).show();
            }
        }

        // Check and Navigate to the next View

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    user.setHint("Credentials Can't be empty");
                    pass.setHint("Credentials Can't be empty");
                    Snackbar.make(findViewById(R.id.main_parent), "Credentials Can't be empty", Snackbar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            user.setHint("ETLAB Username");
                            pass.setHint("ETLAB Password");
                        }
                    }).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });
    }
}