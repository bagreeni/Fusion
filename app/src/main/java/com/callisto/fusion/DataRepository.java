package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.callisto.fusion.db.Category;
import com.callisto.fusion.db.Task;
import com.callisto.fusion.db.TaskCategory;
import com.callisto.fusion.db.TextTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by voxaelfox on 3/4/18.
 */

public class DataRepository {

    // references to the database and en Executor thread
    private FusionDatabase db;
    private Executor dbExec;

    public DataRepository(Context context) {

        db = Room.databaseBuilder(context, FusionDatabase.class, "fusion-database").fallbackToDestructiveMigration().build();
        dbExec = Executors.newSingleThreadExecutor();

        initializeData();

    }

    // database helper method
    public LiveData<List<TextTask>> getTextTasks() {
        return db.textTaskDAO().getAllTextTasks();
    }

    public LiveData<List<Category>> getAllCategories() {
        return db.categoryDAO().getAllCategories();
    }

    public List<Category> getAllCategoriesStatic() {
        return db.categoryDAO().getAllCategoriesStatic();
    }

    // handles all insertion procedures, including operating on a worker thread
    public void insertTextTask(final String data, final String categoryName) {

        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                // make a Task and insert
                Task task = new Task();

                long taskID = db.taskDAO().insert(task);

                // create a TaskCategory to link to newly created Task
                TaskCategory taskCategory = new TaskCategory();
                taskCategory.taskID = taskID;

                // check if category exists, create if not
                // to get categoryID for TaskCategory link
                long categoryID;
                if (db.categoryDAO().getCategoryMatchCount(categoryName) == 0) {
                    Category category = new Category();
                    category.name = categoryName;

                    categoryID = db.categoryDAO().insertCategory(category);
                } else {
                    categoryID = db.categoryDAO().getCategoryID(categoryName);
                }

                // attach category link
                taskCategory.categoryID = categoryID;

                // insert new TaskCategory link into db
                db.taskCategoryDAO().insertTaskCatagory(taskCategory);

                // make a TextTask and insert AND link to Task
                TextTask textTask = new TextTask();
                textTask.taskID = taskID;
                textTask.data = data;

                // insert TextTask to db
                db.textTaskDAO().insertTextTask(textTask);

                // procedure finished!

            }
        });
    }

    public void insertCategory(final String categoryName) {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                    Category category = new Category();
                    category.name = categoryName;

                    db.categoryDAO().insertCategory(category);

            }
        });
    }

    private void initializeData() {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                if(db.categoryDAO().getCategoryMatchCount("default") == 0) {
                    Category defCat = new Category();
                    defCat.name = "default";

                    db.categoryDAO().insertCategory(defCat);
                }

            }
        });
    }

}
