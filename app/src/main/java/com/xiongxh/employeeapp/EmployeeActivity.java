package com.xiongxh.employeeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiongxh.employeeapp.model.Employee;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeActivity extends AppCompatActivity {
    private static final String KEY_EMPLOYEE = "EMPLOYEE";

    @BindView(R.id.item_employee_id)
    TextView mIdView;
    @BindView(R.id.item_employee_name)
    TextView mNameView;
    @BindView(R.id.item_employee_age)
    TextView mAgeView;
    @BindView(R.id.item_employee_wage)
    TextView mWageView;
    @BindView(R.id.item_employee_profile)
    ImageView mProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        ButterKnife.bind(this);

        final Employee employee = (Employee) getIntent().getSerializableExtra(KEY_EMPLOYEE);

        if (employee != null){

            mIdView.setText(employee.getId());
            mNameView.setText(employee.getName());
            mAgeView.setText(employee.getAge());
            mWageView.setText(getString(R.string.dollar) + employee.getSalary());

            setTitle(employee.getName());

            String image = employee.getImage();

            String profile_url = getString(R.string.mistery_profile);

            //Use Brad Pitt's image when the image is "default_profile" in the API
            //Use a mistery image when the image is null
            if (image.equals(getString(R.string.default_image))){
                profile_url = getString(R.string.default_profile);
            }

            //Load image from url
            Glide.with(this)
                    .load(profile_url)
                    .into(mProfileView);
        }

    }

}
