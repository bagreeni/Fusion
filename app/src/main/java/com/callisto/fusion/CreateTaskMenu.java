package com.callisto.fusion;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class CreateTaskMenu extends AppCompatActivity {

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

//
//        final EditText addTaskText = findViewById(R.id.addTaskText);
//
//        // give the button a behavior
//        addTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ArrayList<String> categoryList = new ArrayList<>();
//                categoryList.add("default");
//
//                Date dueDate = new Date();
//
//                Date workDate = new Date();
//
//                DataRepository.getInstance().insertTextTask(addTaskText.getText().toString(), categoryList, dueDate, workDate);
//                addTaskText.setText("");
//
//             //   finish();
////                if (view != null) {
////                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
////                }
//
//            }
//        });
    }

}
