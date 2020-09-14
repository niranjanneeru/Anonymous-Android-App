package com.neeru.anonymous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText userNameEditText,passwordEditText;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Initialization

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submit = findViewById(R.id.attendance);

        // Check and Navigate to the next View

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()){
                    userNameEditText.setHint("Credentials Can't be empty");
                    passwordEditText.setHint("Credentials Can't be empty");
                }else {
                    Intent intent = new Intent(MainActivity.this,AttendanceActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }
            }
        });

//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://127.0.0.1:8000/").addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//
//        Attendance attendance = retrofit.create(Attendance.class);

    }
}