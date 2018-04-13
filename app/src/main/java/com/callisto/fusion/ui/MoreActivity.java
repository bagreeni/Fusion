package com.callisto.fusion.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.entities.FullTextTask;

import org.w3c.dom.Text;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        FullTextTask ftt = (FullTextTask)intent.getParcelableExtra("TASKDATA");

        ((TextView)findViewById(R.id.editText)).setText(ftt.getData());
        ((TextView)findViewById(R.id.editCategories)).setText(ftt.getCategoryList());
        ((TextView)findViewById(R.id.editDueDate)).setText(ftt.getDueDate().toString());
        ((TextView)findViewById(R.id.editWorkDate)).setText(ftt.getWorkDate().toString());
       //((TextView)findViewById(R.id.editPriority)).setText(ftt.get);

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

    }

}
