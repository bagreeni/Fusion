package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.callisto.fusion.db.Task;
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

        db = Room.databaseBuilder(context, FusionDatabase.class, "fusion-database").build();
        dbExec = Executors.newSingleThreadExecutor();

    }

    // database helper method
    public LiveData<List<TextTask>> getTextTasks() {
        return db.textTaskDAO().getAll();
    }

    // handles all insertion procedures, including operating on a worker thread
    public void insertTextTask(String str) {

        final String teststr = str.toString();

        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                // make a Task and insert
                Task task = new Task();

                long id = db.taskDAO().insert(task);

                // make a TextTask and insert AND link to Task
                TextTask textTask = new TextTask();
                textTask.taskID = id;
                textTask.data = teststr;

                db.textTaskDAO().insert(textTask);

            }
        });
    }



}
