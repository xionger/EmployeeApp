package com.xiongxh.employeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_TASK = "TASK";
    private static final String KEY_KEYWORDS = "KEYWORDS";

    @BindView(R.id.btn_get_employees)
    Button btnGetEmployees;
    @BindView(R.id.btn_search_employees)
    Button btnSearchEmployees;
    @BindView(R.id.edt_search)
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnGetEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass fetch all task to the EmployeesActivity
                Intent intent = new Intent(getApplicationContext(), EmployeesActivity.class);
                intent.putExtra("TASK", getString(R.string.task_all));
                intent.putExtra("KEYWORDS", "");
                startActivity(intent);
            }
        });

        btnSearchEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editSearch.getText().toString().trim();

                if (query == null || query.length() == 0){
                    //Toast error when user inputs empty text.
                    Toast.makeText(getBaseContext(), R.string.search_error,Toast.LENGTH_LONG).show();

                }else {
                    // Pass search task and searching phrase to the EmployeesActivity
                    Intent intent = new Intent(getApplicationContext(), EmployeesActivity.class);
                    intent.putExtra(KEY_TASK, getString(R.string.task_search));
                    intent.putExtra(KEY_KEYWORDS, query);
                    startActivity(intent);
                }
            }
        });

    }

}
