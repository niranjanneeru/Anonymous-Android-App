package com.neeru.anonymous;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceActivity extends AppCompatActivity {

    Credential data;
    ProgressBar loading_spinner;
    ArrayList<Attendance> attendances;
    AttendanceRecyclerAdpater adapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize Views
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loading_spinner = findViewById(R.id.loading_spinner);
        attendances = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rec_view);
        adapter = new AttendanceRecyclerAdpater(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Intent Handle

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if (username != null && password != null) {
            data = new Credential(username, password);
        } else {
            finish();
        }
        data.setPercent(90);

        // Network Request
        makeNetworkRequest();
    }

    private void makeNetworkRequest() {
        loading_spinner.setVisibility(View.VISIBLE);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://anonymousgbuapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        AttendanceEndpoint attendanceEndpoint = retrofit.create(AttendanceEndpoint.class);
        Call<ArrayList<Attendance>> call = attendanceEndpoint.getAttendance(data);
        call.enqueue(new Callback<ArrayList<Attendance>>() {
            @Override
            public void onResponse(Call<ArrayList<Attendance>> call, Response<ArrayList<Attendance>> response) {
                loading_spinner.setVisibility(View.GONE);
                if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    Intent rev_intent = new Intent(AttendanceActivity.this, MainActivity.class);
                    rev_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    rev_intent.putExtra("Pass", "Invalid Credential");
                    startActivity(rev_intent);
                } else {
                    updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Attendance>> call, Throwable t) {
                Intent rev_intent = new Intent(AttendanceActivity.this, MainActivity.class);
                rev_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                rev_intent.putExtra("Pass", "Try Again");
                startActivity(rev_intent);
            }
        });
    }

    private void updateData(ArrayList<Attendance> data) {
        data.remove(0);
        adapter.setContents(data);
        adapter.notifyDataSetChanged();
    }
}