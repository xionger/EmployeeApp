package com.xiongxh.employeeapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.xiongxh.employeeapp.api.ApiService;
import com.xiongxh.employeeapp.api.EmployeeService;
import com.xiongxh.employeeapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesActivity extends AppCompatActivity {
    private static final String KEY_TASK = "TASK";
    private static final String KEY_KEYWORDS = "KEYWORDS";
    private static final String LIST_STATE = "LISTSTATE";

    @BindView(R.id.listView)
    ListView mListView;

    ApiService apiService;
    List<Employee> mEmployees = new ArrayList<Employee>();

    String mTask, mQuery;
    Parcelable mListInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        ButterKnife.bind(this);

        if(savedInstanceState != null) {
            mListInstanceState = savedInstanceState.getParcelable(LIST_STATE);
        }

        //Take the Intent passing information from the MainActivity.
        mTask = getIntent().getExtras().getString(KEY_TASK);
        mQuery = getIntent().getExtras().getString(KEY_KEYWORDS);

        //Set different title basing on the previous task
        if (mTask.equals(getString(R.string.task_search))){
            setTitle(getString(R.string.title_match));
        }else{
            setTitle(getString(R.string.title_all));
        }

        apiService = EmployeeService.getEmployeeService();

        getEmployeesList();

    }

    /*
    Fetching data from the API
     */
    public void getEmployeesList(){
        Call<List<Employee>> call = apiService.fetchEmployeesFromServer();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(response.isSuccessful()){
                    if (mTask.equals(getString(R.string.task_all))) {
                        mEmployees = response.body();
                    }else {
                        mEmployees = filtEmployees(response.body(), mQuery);
                    }
                    mListView.setAdapter(new EmployeeAdapter(EmployeesActivity.this, R.layout.list_employee, mEmployees));

                    if (mListInstanceState != null) {
                        //Restore the position of the listview
                        mListView.onRestoreInstanceState(mListInstanceState);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the position of the listview
        outState.putParcelable(LIST_STATE, mListView.onSaveInstanceState());
    }

    /*
    @param employees, list of employees
    @param query, text that user inputed for searching
    @return list of employees matching the query phrase
     */
    private List<Employee> filtEmployees(List<Employee> employees, String query){
        List<Employee> matchEmployees = new ArrayList<Employee>();

        for (Employee employee : employees){
            String lowName = employee.getName().toLowerCase();
            //True: query is the substring of name, or query matches other columns exactly
            if (lowName.indexOf(query.toLowerCase()) != -1 || employee.getId().equals(query)
                    || employee.getAge().equals(query) || employee.getSalary().equals(query)){
                matchEmployees.add(employee);
            }
        }
        return matchEmployees;
    }
}
