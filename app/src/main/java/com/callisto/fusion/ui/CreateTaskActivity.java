package com.callisto.fusion.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.renderscript.RenderScript;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateTaskActivity extends AppCompatActivity {

    Calendar myCalendar;
    Date workDate = new Date();
    Date dueDate = new Date();
    EditText workDateBox, dueDateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        myCalendar = Calendar.getInstance();
        workDateBox = findViewById(R.id.work_date);
        dueDateBox = findViewById(R.id.due_date);

        final DatePickerDialog.OnDateSetListener dateWork = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                workDate = new Date(myCalendar.getTime().getTime());
                updateLabelWork();
            }
        };

        final DatePickerDialog.OnDateSetListener dateDue = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dueDate = new Date(myCalendar.getTime().getTime());
                updateLabelDue();
            }
        };

        workDateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateTaskActivity.this, dateWork ,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dueDateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateTaskActivity.this, dateDue ,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //create submit entries button on click listener to add all input to database on button press
        FloatingActionButton addTask = findViewById(R.id.submit_task_button);
    }

    protected void addTask(View view) {

        // get text box elements
        TextInputEditText taskTextBox = findViewById(R.id.task_name);
        TextInputEditText taskCategoryBox = findViewById(R.id.category_name);

        TextInputEditText priorityBox = findViewById(R.id.priority);

        String taskText = taskTextBox.getText().toString();
        if(taskText.equals("")){
            return;
        } else {
            String rawCategoryList = taskCategoryBox.getText().toString();
            int priority = 1;
            if (!(priorityBox.equals(""))) {
                priority = priorityBox.getInputType();
            }

            if (!rawCategoryList.contains("default") && !rawCategoryList.trim().isEmpty()) {
                rawCategoryList = rawCategoryList.concat(",default");
            } else if (rawCategoryList.trim().isEmpty()) {
                rawCategoryList = rawCategoryList.concat("default");
            }

            String[] categoryArray = rawCategoryList.split(",");

            for (int i = 0; i < categoryArray.length; i++) {
                categoryArray[i] = categoryArray[i].trim();
            }

            List<String> categoryList = new ArrayList<>(Arrays.asList(categoryArray));

            DataRepository.getInstance().insertTextTask(taskText, categoryList, priority, dueDate, workDate);


            //close keyboard
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            returnToMainActivity(view);
        }
    }

    private void updateLabelWork(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        workDateBox.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabelDue(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dueDateBox.setText(sdf.format(myCalendar.getTime()));
    }
    public void returnToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


