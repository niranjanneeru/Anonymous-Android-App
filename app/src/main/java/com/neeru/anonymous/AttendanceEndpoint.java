package com.neeru.anonymous;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AttendanceEndpoint {
    @POST("/")
    Call<ArrayList<Attendance>> getAttendance(@Body Credential credential);
}
