package com.callisto.fusion.ui;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        //create submit entries button on click listener to add all input to database on button press
        FloatingActionButton addTask = findViewById(R.id.submit_task_button);
    }

    protected void addTask(View view) {

        // get text box elements
        TextInputEditText taskTextBox = findViewById(R.id.task_name);
        TextInputEditText taskCategoryBox = findViewById(R.id.category_name);

        String taskText = taskTextBox.getText().toString();
        String rawCategoryList = taskCategoryBox.getText().toString();
        if (!rawCategoryList.contains("default")) {
            rawCategoryList = rawCategoryList.concat(",default");
        }

        String[] categoryArray = rawCategoryList.split(",");

        for (int i = 0; i < categoryArray.length; i++) {
            categoryArray[i] = categoryArray[i].trim();
        }

        List<String> categoryList = new ArrayList<>(Arrays.asList(categoryArray));

        DataRepository.getInstance().insertTextTask(taskText, categoryList, new Date(), new Date());

        //close keyboard
        if( view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}
