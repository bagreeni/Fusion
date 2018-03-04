package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.callisto.fusion.db.Task;
import com.callisto.fusion.db.TaskText;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by voxaelfox on 3/4/18.
 */

public class DataRepository {

    private FusionDatabase db;
    private Executor dbExec;

    public DataRepository(Context context) {

        db = Room.databaseBuilder(context, FusionDatabase.class, "fusion-database").build();
        dbExec = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<TaskText>> getTextTasks() {
        return db.taskTextDAO().getAll();
    }

    public void insertTextTask(String str) {

        final String teststr = str.toString();


        dbExec.execute(new Runnable() {
            @Override
            public void run() {

                Task task = new Task();

                long id = db.taskDAO().insert(task);

                TaskText textTask = new TaskText();
                textTask.taskID = id;
                textTask.data = teststr;

                Log.d("Tag?", "WHAT THE FUCK IS HAPPENING HERE" + id);

                db.taskTextDAO().insert(textTask);

            }
        });
    }



}
