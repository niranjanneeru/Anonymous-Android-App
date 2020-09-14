package com.neeru.anonymous;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AttendanceEndpoint {
    @POST("/")
    Call<List<Attendance>> getAttendance(@Body Credential credential);
}
