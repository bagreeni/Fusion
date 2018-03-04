package com.callisto.fusion;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Console;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    // TODO: Comment this whole damn thing, all files, everything, Jesus Christ it is hard to understand
    // I am sorry Brook, this is an atrocity to understand.

    private TextTaskViewModel ttViewModel;
    private DataRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataRepository(getApplicationContext());
        ttViewModel = ViewModelProviders.of(this).get(TextTaskViewModel.class);

        final TextView textView = findViewById(R.id.textView);
        final EditText addTaskText = findViewById(R.id.addTaskText);

        final int num = 1;

        ttViewModel.getTextTasks(db).observe(this, new Observer<List<TaskText>>() {
            @Override
            public void onChanged(@Nullable final List<TaskText> newList) {
                // Update the UI, in this case, a TextView.
                String tasks = "";
                for (TaskText ttask : newList) {
                    tasks = tasks.concat("\n" + ttask.data);
                }

                textView.setText(num + tasks);

            }
        });

        FloatingActionButton addTask = findViewById(R.id.floatingAddTask);

        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                db.insertTextTask(addTaskText.getText().toString());

                addTaskText.setText("Add Task");

            }});


    }
}
