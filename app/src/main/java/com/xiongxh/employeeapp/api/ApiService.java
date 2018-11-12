package com.xiongxh.employeeapp.api;

import com.xiongxh.employeeapp.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("employees")
    Call<List<Employee>> fetchEmployeesFromServer();

    @POST("create")
    Call<Employee> addEmployee(@Body Employee employee);

    @DELETE("delete/{id}")
    Call<Employee> deleteEmployee(@Path("id") String id);
}
