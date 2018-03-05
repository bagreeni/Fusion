package com.callisto.fusion.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.TaskText;
import com.callisto.fusion.viewmodel.TextTaskViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Initialize private fields for use here
    private TextTaskViewModel ttViewModel;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fill our private fields, ViewModels, and DataRepository Instances
        // This will probably operate on a singleton madel later
        dataRepository = new DataRepository(getApplicationContext());
        ttViewModel = ViewModelProviders.of(this).get(TextTaskViewModel.class);

        // get UI elements
        final TextView textView = findViewById(R.id.textView);
        final EditText addTaskText = findViewById(R.id.addTaskText);

        // attach an observer to database lists
        // in this case, a list of TextTasks
        ttViewModel.getTextTasks(dataRepository).observe(this, new Observer<List<TaskText>>() {
            @Override
            public void onChanged(@Nullable final List<TaskText> newList) {
                // Update the UI, in this case, a TextView.
                String tasks = "";
                for (TaskText ttask : newList) {
                    tasks = tasks.concat("\n" + ttask.data);
                }

                textView.setText(tasks);

            }
        });

        // find a button on screen
        FloatingActionButton addTask = findViewById(R.id.floatingAddTask);

        // give the button a behavior
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                dataRepository.insertTextTask(addTaskText.getText().toString());
                addTaskText.setText("Add Task");

            }});


    }
}
