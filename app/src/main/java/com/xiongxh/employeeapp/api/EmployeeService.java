package com.xiongxh.employeeapp.api;

import com.xiongxh.employeeapp.BuildConfig;
import com.xiongxh.employeeapp.remote.RetrofitClient;

public class EmployeeService {
    private EmployeeService(){}
    //private static final String URL = "http://dummy.restapiexample.com/api/v1/";

    public static ApiService getEmployeeService(){
        return RetrofitClient.getClient(BuildConfig.BASEURL).create(ApiService.class);
    }
}
