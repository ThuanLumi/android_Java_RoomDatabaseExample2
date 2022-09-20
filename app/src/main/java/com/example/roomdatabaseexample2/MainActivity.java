package com.example.roomdatabaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabaseexample2.database.AppDatabase;
import com.example.roomdatabaseexample2.database.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppDatabase mDb;
    private TextView txt_list;
    private Button button;
    private EditText etName;
    private EditText etDesignation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_list = (TextView) findViewById(R.id.txt_list);
        etName = (EditText) findViewById(R.id.etName);
        etDesignation = (EditText) findViewById(R.id.etDesignation);
        button = (Button) findViewById(R.id.button);
        mDb = AppDatabase.getDatabase(getApplicationContext());
        populateEmployList();
        button.setOnClickListener(this);
    }

    private void populateEmployList() {
        txt_list.setText("");

        /**
         *  Insert and get data using Database Async way
         */
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Get Data
                List<Employee> employeeList = mDb.employeeDao().findAllEmploySync();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Stuff that updates the UI
                        for (Employee employee : employeeList) {
                            txt_list.append(employee.name + " : " + employee.designation);
                            txt_list.append("\n");
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        String name = etName.getText().toString().trim();
        String designation = etDesignation.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(designation)) {
            Toast.makeText(this, "Name/Designation should not be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Employee employee = new Employee();
            employee.name = name;
            employee.designation = designation;

            /**
             *  Insert and get data using Database Async way
             */
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    // Insert Data
                    mDb.employeeDao().insertEmploy(employee);
                }
            });

            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etDesignation.setText("");
            etName.requestFocus();
            populateEmployList();
        }
    }
}