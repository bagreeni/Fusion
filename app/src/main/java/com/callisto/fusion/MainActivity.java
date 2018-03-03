package com.callisto.fusion;

import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FusionDatabase db = Room.databaseBuilder(getApplicationContext(),
                FusionDatabase.class, "fusion-db").build();

        final TextView textView = findViewById(R.id.textView);
        final EditText addTaskText = findViewById(R.id.addTaskText);

        FloatingActionButton addTask = findViewById(R.id.floatingAddTask);
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                long insertedTaskID = db.taskDAO().insert(new Task());
                TaskText texttask = new TaskText();
                texttask.taskID = insertedTaskID;
                texttask.data = addTaskText.getText().toString();

                db.taskTextDAO().insert(new TaskText());

                String tasks = "";
                for (TaskText ttask : db.taskTextDAO().getAll()) {
                    tasks.concat("\n" + ttask.data);
                }

                textView.setText(tasks);

                addTaskText.setText("Add Task");

            }});


    }
}
