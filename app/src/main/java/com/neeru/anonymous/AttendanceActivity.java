package com.neeru.anonymous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceActivity extends AppCompatActivity {

    Credential data;
    ProgressBar loading_spinner;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize Views

        loading_spinner = findViewById(R.id.loading_spinner);
        loading_spinner.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.dummy_data);


        //Intent Handle

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if(username!=null && password!=null){
            data = new Credential(username,password);
        }else {
            finish();
        }
        data.setPercent(90);

        // Network Request

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        AttendanceEndpoint attendanceEndpoint = retrofit.create(AttendanceEndpoint.class);
        Call<List<Attendance>> call = attendanceEndpoint.getAttendance(data);
        call.enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                loading_spinner.setVisibility(View.GONE);
                if(response.code()== HttpURLConnection.HTTP_UNAUTHORIZED){
                    Intent rev_intent = new Intent(AttendanceActivity.this,MainActivity.class);
                    rev_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Pass Invalid Credential
                    startActivity(intent);
                }
                updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "Something Went Wrong"+t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    private void updateData(List<Attendance> Attendances) {
        StringBuilder string = new StringBuilder("");
        for( Attendance a:Attendances){
            if(a.getSubject().equals("Total")){
                continue;
            }
            string.append(a.getSubject()).append(" ").append(a.getAttended()).append(" ").append(a.getTotal()).append(" ").append(a.getDays()).append(" ").append(a.getPercent()).append("\n");
        }
        textView.setText(string);
    }
}