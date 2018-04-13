package com.callisto.fusion.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.entities.Category;
import com.callisto.fusion.db.entities.FullTextTask;
import com.callisto.fusion.viewmodel.MainViewModel;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Initialize private fields for use here
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainViewModel ttViewModel;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    // starts on default category
    private String catMask = "default";

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
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        //set item as selected to persist highlight
                        navigationView.setCheckedItem(item.getItemId());
                        //close drawer when item tapped
                        mDrawerLayout.closeDrawers();

                        catMask = item.getTitle().toString().toLowerCase();
                        Log.d("SELECT CAT", catMask);
                        if (catMask.equals("all categories")) {
                            ttViewModel.updateFullTextTasks("default");
                            Log.d("CATCHECK", "default!");
                        } else {
                            ttViewModel.updateFullTextTasks(catMask);
                            Log.d("CATCHECK", "other! - " + catMask);
                        }

                        //add code here to update UI based on item selected

                        return true;
                    }
                });

        mRecyclerView = findViewById(R.id.taskRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Swipe Controls
        SwipeController swipeController = new SwipeController();
        ItemTouchHelper ith = new ItemTouchHelper(swipeController);
        ith.attachToRecyclerView(mRecyclerView);

        // specify an adapter (see also next example)


        // get UI elements
        final TextView textView = findViewById(R.id.textView);

        // attach an observer to database lists
        // in this case, a list of TextTasks
        ttViewModel.getFullTextTasks(catMask).observe(this, new Observer<List<FullTextTask>>() {
            @Override
            public void onChanged(@Nullable final List<FullTextTask> fullTextTasks) {

                mAdapter = new RecyclerViewAdapter(fullTextTasks);

                mRecyclerView.setAdapter(mAdapter);

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
        final NavigationView navigationView = findViewById(R.id.nav_view);

        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the settings action

        } else if (id == R.id.all_category){
            // Handle the settings action
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void addMenuItem(){
        final NavigationView navView = findViewById(R.id.nav_view);

        final Menu menu = navView.getMenu();

        ttViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                // UI Changes happen here

                menu.clear();

                navView.getMenu().findItem(R.id.all_category);
                navView.inflateMenu(R.menu.top_nav_drawer);

                //public abstract MenuItem add(R.id.action_settings);
               // navView.getMenu().add(R.id.action_settings);
                for ( Category category : categories) {
                    if(!category.getName().equals("default")) {
                        String name = category.getName().substring(0, 1).toUpperCase().concat(category.getName().substring(1));
                        menu.add(name);
                    }
                }


                //add settings to bottom of nav drawer menu
                navView.getMenu().findItem(R.id.action_settings);
                navView.inflateMenu(R.menu.activity_nav_drawer_view);

            }
        });
        navView.invalidate();


    }

    public void openTaskEntryView(View view){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }

    public void deleteTask(View view){
        String taskText = (((TextView)((View)view.getParent()).findViewById(R.id.taskTitle)).getText().toString());
        DataRepository.getInstance().deleteTextTask(taskText);

        ttViewModel.updateFullTextTasks(catMask);
    }

    public void colorChooser(){

    }

}
