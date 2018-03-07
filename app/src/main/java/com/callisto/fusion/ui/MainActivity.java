package com.callisto.fusion.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.entities.Category;
import com.callisto.fusion.db.entities.FullTextTask;
import com.callisto.fusion.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Initialize private fields for use here
    private MainViewModel ttViewModel;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fill our private fields, ViewModels, and DataRepository Instances
        // This will probably operate on a singleton madel later
        ttViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        //set item as selected to persist highlight
                        item.setChecked(true);
                        //close drawer when item tapped
                        mDrawerLayout.closeDrawers();

                        //add code here to update UI based on item selected

                        return true;
                    }
                });

        // get UI elements
        final TextView textView = findViewById(R.id.textView);
        final EditText addTaskText = findViewById(R.id.addTaskText);

        // attach an observer to database lists
        // in this case, a list of TextTasks
        ttViewModel.getFullTextTasks().observe(this, new Observer<List<FullTextTask>>() {
            @Override
            public void onChanged(@Nullable final List<FullTextTask> fullTextTasks) {

                // Update the UI, in this case, a TextView.
                String tasks = "";
                for (FullTextTask fullTextTask : fullTextTasks) {

                    tasks = tasks.concat("\n"   + fullTextTask.data + ", "
                                                + fullTextTask.categoryName + ", "
                                                + fullTextTask.toString()

                    );

                }

                textView.setText(tasks);

            }
        });

        // find a button on screen
        FloatingActionButton addTask = findViewById(R.id.floatingAddTask);

        // give the button a behavior
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> categoryList = new ArrayList<>();
                categoryList.add("default");

                Date dueDate = new Date();

                Date workDate = new Date();

                DataRepository.getInstance().insertTextTask(addTaskText.getText().toString(), categoryList, dueDate, workDate);
                addTaskText.setText("");

                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }
        });

        addMenuItem();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addMenuItem(){
        NavigationView navView = findViewById(R.id.nav_view);

        final Menu submenu = navView.getMenu().addSubMenu("New Super SubMenu");

        LiveData<List<Category>> categories = DataRepository.getInstance().getAllCategories();
        ttViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                // UI Changes happen here

                for (Category category : categories) {
                    submenu.add(category.name);
                }

            }
        });
//        submenu.add("item1");
//        submenu.add("item1");
//        submenu.add("item1");

        navView.invalidate();
    }
}
