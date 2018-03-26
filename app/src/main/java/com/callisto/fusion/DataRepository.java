package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import com.callisto.fusion.db.entities.Category;
import com.callisto.fusion.db.FusionDatabase;
import com.callisto.fusion.db.entities.FullTextTask;
import com.callisto.fusion.db.entities.Task;
import com.callisto.fusion.db.entities.TaskCategory;
import com.callisto.fusion.db.entities.TextTask;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by voxaelfox on 3/4/18.
 */

public class DataRepository {

    // references to the database and en Executor thread
    private static FusionDatabase db = null;
    private static DataRepository dr = null;
    private static Executor dbExec = null;

    private DataRepository() {
        initializeData();
    }

    public static DataRepository getInstance() {
        if (db == null) {
            db = Room.databaseBuilder(FusionApplication.getAppContext(), FusionDatabase.class, "fusion-database").fallbackToDestructiveMigration().build();
        }
        if (dbExec == null) {
            dbExec = Executors.newSingleThreadExecutor();
        }
        if (dr == null) {
            dr = new DataRepository();
        }
        return dr;
    }

    // database helper methods
    public LiveData<List<TextTask>> getAllTextTasks() {
        return db.textTaskDAO().getAllTextTasks();
    }

    public LiveData<List<FullTextTask>> getAllFullTextTasks() {
        return db.textTaskDAO().getAllFullTextTasks();
    }

    public LiveData<List<Category>> getAllCategories() {
        return db.categoryDAO().getAllCategories();
    }

    // handles all insertion procedures, including operating on a worker thread
    public void insertTextTask(final String data, final List<String> categoryNames, final int priority, final Date dueDate, final Date workDate) {

        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                // make a Task and insert
                Task task = new Task();
                task.setDueDate(dueDate);
                task.setDueDate(workDate);
                task.setPriority(priority);

                new Date();

                long taskID = db.taskDAO().insertTask(task);

                // create a TaskCategory to link to newly created Task
                TaskCategory taskCategory = new TaskCategory();
                taskCategory.taskID = taskID;

                // for each category in the given list:
                // check if category exists, create if not
                // to get categoryID for TaskCategory link
                // create entry in TaskCategory

                for (String categoryName : categoryNames) {

                    long categoryID;
                    if (db.categoryDAO().getCategoryMatchCount(categoryName) == 0) {
                        Category category = new Category();
                        category.setName(categoryName);

                        categoryID = db.categoryDAO().insertCategory(category);
                    } else {
                        categoryID = db.categoryDAO().getCategoryID(categoryName);
                    }

                    // attach category link
                    taskCategory.categoryID = categoryID;

                    // insert new TaskCategory link into db
                    db.taskCategoryDAO().insertTaskCatagory(taskCategory);

                }

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

    public void deleteTextTask(final String taskText) {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                TextTask textTask = db.textTaskDAO().getTextTaskFromData(taskText);

                long taskID = db.textTaskDAO().getTaskIDFromData(taskText);
                Task task = db.taskDAO().getTaskFromID(taskID);

                List<TaskCategory> taskCatList = db.taskCategoryDAO().getTaskCategoriesFromTaskID(taskID);

                // begin removal

                for (TaskCategory taskCategory : taskCatList) {
                    db.taskCategoryDAO().deleteTaskCategory(taskCategory);
                }
                db.textTaskDAO().deleteTextTask(textTask);
                db.taskDAO().deleteTask(task);

            }
        });
    }

    public void insertCategory(final String categoryName) {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                    Category category = new Category();
                    category.setName(categoryName);

                    db.categoryDAO().insertCategory(category);

            }
        });
    }

    // TODO make this
    /*
    public void deleteTextTask(final String textTaskName) {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {
                long id = db.textTaskDAO().getTextTask()
                db.textTaskDAO().deleteTextTask()
                category.setName(categoryName);

                db.categoryDAO().insertCategory(category);

            }
        });
    }
    */

    private void initializeData() {
        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                if(db.categoryDAO().getCategoryMatchCount("default") == 0) {
                    Category defCat = new Category();
                    defCat.setName("default");
                    defCat.setCategoryID(1);

                    db.categoryDAO().insertCategory(defCat);
                }

            }
        });
    }

}
