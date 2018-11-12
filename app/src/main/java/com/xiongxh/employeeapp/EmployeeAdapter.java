package com.xiongxh.employeeapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xiongxh.employeeapp.model.Employee;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private static final String KEY_EMPLOYEE = "EMPLOYEE";

    private Context mContext;
    private List<Employee> mEmployees;

    public EmployeeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Employee> employees) {
        super(context, resource, employees);
        this.mContext = context;
        this.mEmployees = employees;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_employee, parent, false);

        TextView employeeIdView = (TextView) rowView.findViewById(R.id.tv_employee_id);
        TextView nameView = (TextView) rowView.findViewById(R.id.tv_employee_name);
        TextView ageView = (TextView) rowView.findViewById(R.id.tv_employee_age);
        TextView wageView = (TextView) rowView.findViewById(R.id.tv_employee_wage);

        final String employeeId = mEmployees.get(pos).getId();
        final String name = mEmployees.get(pos).getName();
        final String age = mEmployees.get(pos).getAge();
        final String wage = mEmployees.get(pos).getSalary();
        final String image = mEmployees.get(pos).getImage();

        employeeIdView.setText(employeeId);
        nameView.setText(name);
        ageView.setText(age);
        wageView.setText(mContext.getString(R.string.dollar) + wage);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start EmployeeActivity and pass employee data
                Employee employee = new Employee(employeeId, name, age, wage, image);
                Intent intent = new Intent(mContext, EmployeeActivity.class);
                intent.putExtra(KEY_EMPLOYEE, employee);

                mContext.startActivity(intent);
            }
        });

        return rowView;
    }
}
