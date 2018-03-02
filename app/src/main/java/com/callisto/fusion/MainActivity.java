package com.callisto.fusion;

import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] taskArray = new String[100];
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(taskArray[0]);
        final EditText addTaskText = (EditText) findViewById(R.id.addTaskText);

        FloatingActionButton addTask = (FloatingActionButton) findViewById(R.id.floatingAddTask);
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                taskArray[i] = addTaskText.getText().toString();
                i++;
                textView.setText( textView.getText() + "   " + addTaskText.getText().toString());

                addTaskText.setText("Add Task");

            }
                                       });


    }
}
