package com.callisto.fusion.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.entities.FullTextTask;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MoreActivity extends AppCompatActivity {

    FullTextTask ftt;
    Calendar myCalendar;
    Date workDate;
    Date dueDate;
    EditText workDateBox, dueDateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
         ftt = (FullTextTask)intent.getParcelableExtra("TASKDATA");

        ((TextView)findViewById(R.id.editText)).setText(ftt.getData());
        ((TextView)findViewById(R.id.editCategories)).setText(ftt.getCategoryList());
        ((TextView)findViewById(R.id.editDueDate)).setText(ftt.getDueDate().toString());
        ((TextView)findViewById(R.id.editWorkDate)).setText(ftt.getWorkDate().toString());
        ((TextView)findViewById(R.id.editPriority)).setText(ftt.getPriority());

        myCalendar = Calendar.getInstance();
        workDateBox = findViewById(R.id.editWorkDate);
        dueDateBox = findViewById(R.id.editDueDate);

        final DatePickerDialog.OnDateSetListener dateWork = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                workDate = myCalendar.getTime();
                updateLabelWork();
            }
        };

        final DatePickerDialog.OnDateSetListener dateDue = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dueDate = myCalendar.getTime();
                updateLabelDue();
            }
        };

        workDateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MoreActivity.this, dateWork ,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dueDateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MoreActivity.this, dateDue ,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void editTask(View v){
        String[] array = ((TextView) ((View) v.getParent()).findViewById(R.id.editCategories)).getText().toString().split(",");
        List list = Arrays.asList(array);
        int priority = Integer.valueOf(((TextView)findViewById(R.id.editPriority)).getText().toString());

        DataRepository.getInstance().editTextTask(ftt.getTaskID(),((TextView)findViewById(R.id.editText)).getText().toString(),list, workDate, dueDate, priority);
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

}
